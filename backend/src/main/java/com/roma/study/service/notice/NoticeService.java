package com.roma.study.service.notice;

import com.roma.study.dto.notice.NoticeDTO;
import com.roma.study.environment.response.ResponseCode;
import com.roma.study.model.notice.Notice;
import com.roma.study.repository.notice.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;


    public Notice create(NoticeDTO noticeDTO) {
        log.debug("create: {}", noticeDTO);
        Notice notice = Notice.builder()
                .title(noticeDTO.title())
                .content(noticeDTO.content())
                .build();
                noticeRepository.save(notice);
        return notice;
    }

    @Transactional
    public Notice update(NoticeDTO noticeDTO){
        Notice notice = noticeRepository.findById(noticeDTO.seq()).orElseThrow(() -> new ServiceException(ResponseCode.NO_DATA_FOUND.code()));
        log.debug("find notice Entity : {}", notice);
        notice.modifyTitleAndContent(noticeDTO.title(), noticeDTO.content());
        log.debug("modify notice Entity : {}", notice);
        noticeRepository.save(notice);
        return notice;
    }

    @Transactional
    public NoticeDTO get(Long seq){
        Notice notice = noticeRepository.findById(seq).orElseThrow(() -> new ServiceException(ResponseCode.NO_DATA_FOUND.code()));

        NoticeDTO noticeDTO = NoticeDTO.builder()
                .seq(notice.getSeq())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
        return noticeDTO;
    }

    public List<NoticeDTO> list() {
        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        for(Notice notice : noticeList){
            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .seq(notice.getSeq())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .build();
            noticeDTOList.add(noticeDTO);
        }
        return noticeDTOList;
    }

    @Transactional
    public String delete(Long seq){
        noticeRepository.deleteById(seq);
        return ResponseCode.SUCCESS.code();
    }
}

