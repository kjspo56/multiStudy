package com.roma.study.dto.notice;

import com.roma.study.model.notice.Notice;
import lombok.Builder;
import org.hibernate.service.spi.ServiceException;

import java.util.Objects;

@Builder
public record NoticeDTO(Long seq, String title, String content) {

    //생성자를 선언하여 특정 필드가 없는 경우의 케이스로 사용 가능
    public NoticeDTO(Long seq, String title){
        this(seq, title, null);
    }

    public NoticeDTO{
        if(Objects.isNull(title)){
            throw new ServiceException("title is null");
        }
    }

    public Notice toEntity(){
        return Notice.builder()
                .seq(seq)
                .title(title)
                .content(content)
                .build();
    }
}
