package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.MessengerDAO;

public class MyMessageService {

	public LinkedList[] getMyMessageList(String id, int nowPage) {
		LinkedList[] messageList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			MessengerDAO messengerDAO = MessengerDAO.getInstance();
			messengerDAO.setConnection(conn);
			messageList = messengerDAO.selectMyMessageList(id, nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return messageList;
	}

}
