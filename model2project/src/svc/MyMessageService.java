package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.MessengerDAO;

public class MyMessageService {

	public LinkedList[] getMyMessageList(String id, int nowPage) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		LinkedList[] messageList = messengerDAO.selectMyMessageList(id, nowPage);
		close(conn);
		
		return messageList;
	}

}
