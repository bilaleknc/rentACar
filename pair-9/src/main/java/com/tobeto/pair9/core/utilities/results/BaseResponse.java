package com.tobeto.pair9.core.utilities.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Data
public class BaseResponse <T>{

    @JsonProperty("isSuccess")
    private boolean success;

    private String message ="Success";

    private T data;

    public BaseResponse(boolean success){
        this.success = success;
    }

    public BaseResponse(boolean success, String message){
        this(success);
        this.message = message;
    }

    public BaseResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
}
