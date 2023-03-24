package com.roma.study.environment.config;

import com.roma.study.resource.HelloResource;
import com.roma.study.resource.notice.NoticeResource;
import jakarta.servlet.ServletConfig;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletConfigAware;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig implements ServletConfigAware{

    private ServletConfig servletConfig;    //ServletContext의 객체 기능, 서블릿에 대한 초기화 작업가능

    public JerseyConfig(){
        register(HelloResource.class);
        register(NoticeResource.class);

        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
}
