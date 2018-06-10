package com.bysj.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/***
 *
 * Created by cz1996 on 2018/4/17.
 *
 ***/


@Configuration
public class MyWebSocketConf {
    /**
     * 提供配置自己的websocekt类即请求路径
     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        MyWebSocket myHandler = new MyWebSocket();
//        registry.addHandler(MyWebSocket(),"/websocket").addInterceptors(new WebSocketInterceptor());
//    }

    /**
     * 向spring容器注册javabean由spring容器来管理
     * @return
     */
//    @Bean
//    public WebSocketHandler MyWebSocket(){
//        return new MyWebSocket();
//    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
