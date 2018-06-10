package com.bysj.controller;

import com.bysj.socket.MyWebSocket;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    MyWebSocket myWebSocket = new MyWebSocket();

//    @RequestMapping("/sessionwebsocket")
//    public String senddMessage() throws IOException {
//       myWebSocket.sendMessage("3231231");
//       return "cccz";
//    }

}
