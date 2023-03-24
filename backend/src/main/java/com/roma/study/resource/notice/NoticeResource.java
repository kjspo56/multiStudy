package com.roma.study.resource.notice;

import com.roma.study.dto.notice.NoticeDTO;
import com.roma.study.service.notice.NoticeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Path("/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeResource {

    private final NoticeService noticeService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(NoticeDTO noticeDTO) {
        return Response.status(Response.Status.OK)
                .build();
    }


}
