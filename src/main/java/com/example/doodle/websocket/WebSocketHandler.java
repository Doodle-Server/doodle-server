package com.example.doodle.websocket;

import com.example.doodle.dto.SysLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    //userid의 web session 저장
    Map<String, WebSocketSession> userSessions = new HashMap<>();

    //ROOM마다 연결되어있는 userId list를 저장
    Map<String, List<String>> roomUsers = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        log.info(session.getAttributes().get("userid") + " 연결됨");
        String userid = (String) session.getAttributes().get("userid");

        if(userSessions.get(userid)!=null){
            userSessions.replace(userid, session);
        }
        else{
            userSessions.put(userid, session);
        }


    }

    //소켓에 메시지를 보냈을때 js에서 on.message
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextmessage: " + message.getPayload());

        //protocol: RoomNum, 보내는id, 내용
        String msg = message.getPayload();
        if(StringUtils.isNotEmpty(msg)) {
            String[] strs = msg.split(",");
            if(strs != null && strs.length == 3) {
                String roomNum = strs[0];
                String sendId = strs[1];
                String content = strs[2];

                //입장일시
                if(content.equals("ENTER")) {
                    //해당 roomNum의 Map의 userId 리스트에 sendId를 넣어준다.
                    System.out.println("ENTER안에 있음");

                    if(roomUsers.get(roomNum) == null) {
                        List<String> list = new ArrayList<>();
                        roomUsers.put(roomNum, list);
                    }

                    roomUsers.get(roomNum).add(sendId);
                    System.out.println(sendId + "가 들어왔습니다.");

                    List<String> roomUserList = roomUsers.get(roomNum);
                    for(int i = 0; i < roomUserList.size(); i++) {
                        System.out.println(i + roomUserList.get(i) + " " + userSessions.get(roomUserList.get(i)));
                    }
                }
                //퇴장일시
                else if(content.equals("OUT")) {
                    // room을 나갈시 Map의 userId 리스트에 sendId를 지운다.
                    roomUsers.get(roomNum).remove(sendId);
                    System.out.println("나갔습니다.");
                    List<String> roomUserList = roomUsers.get(roomNum);
                    for(int i = 0; i < roomUserList.size(); i++) {
                        System.out.println(i + roomUserList.get(i) + " " + userSessions.get(roomUserList.get(i)));
                    }
                }

                //해당 room의 userList를 가져옴
                List<String> roomUserList = roomUsers.get(roomNum);

                for(int i = 0; i < roomUserList.size(); i++) {
                    //room의 userId의 session에 보내기
                    userSessions.get(roomUserList.get(i)).sendMessage(new TextMessage(sendId + "," + content));
                }
            }
        }
    }


    //소켓이 close 됐을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        //sessions.remove(session);
        System.out.println(session.getAttributes().get("userid")+" 연결 종료됨");

    }


}
