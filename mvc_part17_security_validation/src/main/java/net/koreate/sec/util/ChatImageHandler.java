package net.koreate.sec.util;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatImageHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new Vector<>();
	private Map<WebSocketSession, String> sessionMap = new Hashtable<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결됨 : " + session.getId());
		sessionList.add(session);
		String userName = session.getPrincipal().getName();
		System.out.println("username : " + userName);
		System.out.println("getPrincipal : " + session.getPrincipal());
		System.out.println("getAttribute : " + session.getAttributes());
		sessionMap.put(session, userName);
		System.out.println("연결된 사용자 수 : " + sessionList.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		System.out.println(msg);
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 연결이 끊겼을 때
		System.out.println("연결종료 : " + session.getId());
		String userName = sessionMap.get(session);
		sessionList.remove(session);
		// 사용자 연결 끊김 메세지 전달
		for (WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(","+userName + ",님이 종료하였습니다."));
		}
		sessionMap.remove(session);
		System.out.println("연결된 사용자 수 : " + sessionList.size());
	}

}
