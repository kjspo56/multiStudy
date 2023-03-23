package com.roma.study.environment.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class JsonResponse implements Serializable {

    private static final long serialVersionUID = -8144848968803798111L;
    @Getter(onMethod_ = @JsonIgnore)
    protected final String MSG_BEAN_NAME = "messageSourceAccessor";

    protected String resultCode;
    protected String resultMsg;
    protected Object data;
    protected Timestamp currentTimestamp;

    public static JsonResponse create(Object obj) {
        return new JsonBasicResponse(obj);
    }

    public static JsonResponse create(Object obj, String msg) {
        return new JsonBasicResponse(obj, msg);
    }

    public static JsonResponse create(String errorCode) {
        return new JsonCodeResponse(errorCode);
    }

    public static JsonResponse create(String resultCode, String msg) {
        return new JsonCodeResponse(resultCode, msg);
    }
}
