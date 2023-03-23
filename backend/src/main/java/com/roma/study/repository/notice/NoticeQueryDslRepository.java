package com.roma.study.repository.notice;

import com.roma.study.dto.notice.NoticeDTO;
import com.roma.study.dto.page.PageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoticeQueryDslRepository {
    List<NoticeDTO> findAllByDynamicQueryDsl(PageDTO pageDTO);
}