package com.bysj.socket;

public class WebSocketResponse {
    private String responseMessage;

    public WebSocketResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}
