package com.bysj.schedul;

import com.bysj.socket.MyWebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import sun.misc.Contended;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Contended
public class MyScheduled {
    @Scheduled(fixedRate = 3000)
    public void timer(CopyOnWriteArraySet<MyWebSocket> webSocketSet,String message){
        System.out.println("1");
        for(MyWebSocket item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
