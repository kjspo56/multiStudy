package com.roma.study.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Path("/test")
@RequiredArgsConstructor
@Slf4j
public class HelloResource {

    /**
     * Consumes : 클라리언트가 서버에게 보내는 데이터 타입 명시
     * Produces : 서버가 클라이언트에게 반환하는 데이터 타입 명시
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public String hello(){
        System.out.println("hello world!!");
        return "hello world";
    }
}
