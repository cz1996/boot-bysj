package com.bysj.socket;

import com.bysj.model.DeviceInfo;
import com.bysj.schedul.MyThread;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cccz1996 on 2018/5/19
 */

@ServerEndpoint("/deviceInfoWebsocket/{username}")
@Component
public class DeviceInfoWebsocket {

    //concurrent包的线程安全Set，用来存放每个客户端对应的DeviceInfoWebsocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static Map<String,DeviceInfoWebsocket> webSocketSet = new HashMap<String, DeviceInfoWebsocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    public Session session;

    //当前设备数据参数
    public static DeviceInfo deviceInfo;

    //服务器计时数据
    private int count = 1;

    //当前登录用户名,前台发送,现阶段是一个double随机数
    public String username = "";

    //生成随机数的线程
    public MyThread myThread = new MyThread();

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session){
        this.session = session;
        this.username = username;
        webSocketSet.put(username,this);     //加入set中
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(username);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        System.out.println("来自客户端的消息:" + message);

        Gson gson = new Gson();

        deviceInfo = gson.fromJson(message,DeviceInfo.class);

    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public synchronized  void sendMessage(String message) throws IOException{
        if(this.session != null){
            this.session.getBasicRemote().sendText(message);
//            使用getBasicRemote会报错,当关闭一个websocket的时候,会报TEXT_FULL_WRITING错误.
//            this.session.getAsyncRemote().sendText(message);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void sendOnTime() throws IOException {
        if(count > 100000){
            count = 1;
        }
        for(DeviceInfoWebsocket item : webSocketSet.values()){
            item.sendCurrDeviceInfo(count);
        }
        count++;
    }

    //    服务器推送数据到指定客户端,每个客户端推送不同数据
    public void sendCurrDeviceInfo(int currentCount) throws IOException {
        if(deviceInfo != null){
            if(currentCount%deviceInfo.getAttrTiming() == 0){
                Double deviceCommon = deviceInfo.getAttrCommon();
                Double deviceFloat = deviceInfo.getAttrFloat();
                double deviceCurrNum = deviceCommon + deviceFloat*(myThread.getCurrNumber()-0.5);
                deviceCurrNum = (double)Math.round(deviceCurrNum*100)/100;
                String messageCurr = deviceCurrNum+"";
                this.sendMessage(messageCurr);
            }
        }
    }

}
