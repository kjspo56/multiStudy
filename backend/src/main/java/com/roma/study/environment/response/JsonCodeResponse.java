package com.roma.study.environment.response;

import com.roma.study.environment.utils.BeanUtils;
import org.springframework.context.support.MessageSourceAccessor;

import java.sql.Timestamp;

public class JsonCodeResponse extends JsonResponse{

    private MessageSourceAccessor accessor;

    public JsonCodeResponse(String errorCode) {
        accessor = BeanUtils.getBean(MSG_BEAN_NAME);
        resultCode = errorCode;
        resultMsg = accessor.getMessage(resultCode);
        data = "";
        currentTimestamp = new Timestamp(System.currentTimeMillis());
    }

    public JsonCodeResponse(String errorCode, String msg) {
        resultCode = errorCode;
        resultMsg = msg;
        data = "";
        currentTimestamp = new Timestamp(System.currentTimeMillis());
    }
}
