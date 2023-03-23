package com.roma.study.mapper.notice;

import com.roma.study.dto.notice.NoticeDTO;
import com.roma.study.mapper.GenericMapper;
import com.roma.study.model.notice.Notice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticeMapper extends GenericMapper<NoticeDTO, Notice> {
}
