package com.roma.study.environment.response;

import com.roma.study.environment.utils.BeanUtils;
import org.springframework.context.support.MessageSourceAccessor;

import java.sql.Timestamp;

public class JsonBasicResponse extends JsonResponse {
    private MessageSourceAccessor accessor;

    public JsonBasicResponse(Object obj) {
        accessor = BeanUtils.getBean(MSG_BEAN_NAME);
        resultCode = ResponseCode.SUCCESS.code();
        resultMsg = accessor.getMessage(resultCode);
        data = obj;
        currentTimestamp = new Timestamp(System.currentTimeMillis());
    }

    public JsonBasicResponse(Object obj, String msg) {
        accessor = BeanUtils.getBean(MSG_BEAN_NAME);
        resultCode = ResponseCode.SUCCESS.code();
        resultMsg = msg;
        data = obj;
        currentTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
