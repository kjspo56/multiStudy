package com.roma.study.environment.utils;

import com.roma.study.environment.config.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class BeanUtils {

    public static <T> T getBean(String beanName) {
//        log.debug("getBean: beanName={}", beanName);
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }
}
