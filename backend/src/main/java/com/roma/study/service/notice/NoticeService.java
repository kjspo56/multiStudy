package com.roma.study.service.notice;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.roma.study.dto.notice.NoticeDTO;
import com.roma.study.dto.page.PageDTO;
import com.roma.study.environment.querydsl.QueryDslPredicateBuilder;
import com.roma.study.environment.response.JsonResponse;
import com.roma.study.environment.response.ResponseCode;
import com.roma.study.environment.specification.SearchSpecification;
import com.roma.study.mapper.notice.NoticeMapper;
import com.roma.study.model.notice.Notice;
import com.roma.study.repository.notice.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    /**
     * DTO -> Entity 방식
     * 1. Entity 빌더를 통해
     * 2. Entity 기능마다(CRUD) 생성하는 DTO 클래스에 dto -> entity 변환 메소드를 추가하여 사용하는 케이스
     *
     * @param noticeDTO
     * @return
     */
    public JsonResponse create(NoticeDTO noticeDTO) {
        log.debug("create: {}", noticeDTO);

        Notice notice = noticeMapper.toEntity(noticeDTO);

        var number = Math.random() * 10;
        if (number > 5) {
            notice = noticeDTO.toEntity();
        }

        noticeRepository.save(notice);

        return JsonResponse.create(noticeMapper.toDto(notice));
    }

    /**
     * 해당 메소드는 에러 처리를 위한 샘플코드.
     * 1. 정의된 코드를 리턴하여 message 처리하는 방식
     * 2. 정의된 코드를 리턴하나 메세지를 서비스에서 직접 지정하는 방식.
     * - 일반적인 서비스에서 처리는 1번의 방식을 권장함(다국어 처리)
     * {
     * "resultCode": "999",
     * "resultMsg": "이건 서비스에서 정의한 메시지지", resultMsg 직접 처리.
     * "data": "",
     * "currentTimestamp": "2023-02-09T04:46:32.037+00:00"
     * }
     *
     * @param noticeDTO
     * @return
     */
    @Transactional
    public JsonResponse update(NoticeDTO noticeDTO) {
        log.debug("noticeDTO : {}", noticeDTO);

        Notice notice = noticeRepository.findById(noticeDTO.seq()).orElseThrow(() -> new ServiceException(ResponseCode.NO_DATA_FOUND.code()));
        log.debug("find notice Entity : {}", notice);
        /**
         * @Setter 메소드를 제외한 경우의 Entity 수정
         * 도메인에 업데이트할 항목을 정의하여 무분별한 set 메소드를 사용하지 않고 구현
         * - setter 메소드가 있을경우 set을 통한 엔티티 변경 후 변경감지로 트랙잭션이 종료되면 update 문 실행됨.
         * - 드문 케이스이긴 하겠지만 Service 외 다른 케이스에서 Entity 생성하여 setter 사용이 가능하면 운영시 update 가 어느 시점에서 발생했는지 디버그가 힘들어진 케이스 발생
         */
        notice.modifyTitleAndContent(noticeDTO.title(), noticeDTO.content());
        log.debug("modify notice Entity : {}", notice);

        var number = Math.random() * 10;
        if (number >= 5) {
            throw new ServiceException(ResponseCode.OTHERS.code());
        } else if (number < 2) {
            return JsonResponse.create(ResponseCode.OTHERS.code(), "이건 서비스에서 정의한 메시지지");
        }

        noticeRepository.save(notice);

        return JsonResponse.create(noticeMapper.toDto(notice));
    }

    /**
     * r 랜덤 함수 값에 의해 분기, 해당 분기는 메세지 처리를 위한 2가지 방법을 보여주기 위함.
     * 1. response body 만 return
     * 2. response body + msg 처리
     * {
     * "resultCode": "1",
     * "resultMsg": "메세지 직접 정의~!!", 2번 방식에 의해 resultMsg 가 직접 정의된 케이스
     * "data": {
     * "regDate": "2023-02-09T03:37:34.249+00:00",
     * ...
     * },
     * "currentTimestamp": "2023-02-09T04:45:22.159+00:00"
     * }
     *
     * @param seq
     * @return
     */
    public JsonResponse get(Long seq) {

//      Record Data Class 클래스 예제.
        NoticeDTO noticeDTO = new NoticeDTO(seq, "title");
        log.debug("get : {}", noticeDTO);

        Notice notice = noticeRepository.findById(seq).orElseThrow(() -> new ServiceException(ResponseCode.NO_DATA_FOUND.code()));

//      jdk 타입 추론 var 사용해봄, 무분별한 사용은 X
        var r = Math.random() * 100;
        log.debug("notice service get {} : random {}", seq, r);
//      sample return case 일반적인 data 리턴과, msg 추가 케이스
        if (r > 50) {
            return JsonResponse.create(noticeMapper.toDto(notice));
        } else {
            return JsonResponse.create(noticeMapper.toDto(notice), "메세지 직접 정의~!!");
        }
    }

    /**
     * paging sample, requestBody{page,size,searchOption} 을 가지고 Criteria 를 통한 dynamic 검색/Paging
     * Page: 일반적인 게시판 형태의 페이징에서 사용(count 쿼리를 포함하는 페이징 카운트 쿼리)
     * Slice: 더보기 형태의 페이징에서 사용(totalPages, totalElements 항목이 없음)
     *
     * @param pageDTO
     * @return
     */
    public JsonResponse list(PageDTO pageDTO) {
        Specification<Notice> specification = SearchSpecification.searchWith(pageDTO.getSearchOption());
        var number = Math.random() * 10;
        if (number >= 5) {
            log.debug("query execute slice");
            Slice<Notice> list = noticeRepository.findSliceBy(pageDTO.getPageRequest());
            return JsonResponse.create(list.stream().map(noticeMapper::toDto).collect(Collectors.toList()));
        } else if (number < 2) {
            log.debug("query execute criteria predicate : {}", specification);
            Page<Notice> list = noticeRepository.findAll(specification, pageDTO.getPageRequest());
            return JsonResponse.create(list.stream().map(noticeMapper::toDto).collect(Collectors.toList()));
        } else {
//            List<NoticeDTO> list = noticeRepository.findAllByDynamicQueryDsl(pageDTO);
            QueryDslPredicateBuilder builder = new QueryDslPredicateBuilder().with(pageDTO.getSearchOption());
            BooleanExpression expression = builder.build(Notice.class, "notice");
            log.debug("query execute querydsl predicate : {}", expression);
            Page<Notice> list = noticeRepository.findAll(expression, pageDTO.getPageRequest());
            return JsonResponse.create(list.stream().map(noticeMapper::toDto).collect(Collectors.toList()));
        }
    }
}
