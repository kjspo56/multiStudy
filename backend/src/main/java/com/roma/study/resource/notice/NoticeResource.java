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
                .entity(noticeService.create(noticeDTO))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(NoticeDTO noticeDTO){
        return Response.status(Response.Status.OK)
                .entity(noticeService.update(noticeDTO))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{seq}")
    public Response get(@PathParam("seq") Long seq) {
        return Response.status(Response.Status.OK)
                .entity(noticeService.get(seq))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response list(){
        return Response.status(Response.Status.OK)
                .entity(noticeService.list())
                .build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{seq}")
    public Response delete(@PathParam("seq") Long seq){
        return Response.status(Response.Status.OK)
                .entity(noticeService.delete(seq))
                .build();
    }

}
