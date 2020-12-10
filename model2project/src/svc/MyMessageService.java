package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MessengerDAO;

public class MyMessageService {

	public ArrayList[] getMyMessageList(String id, int nowPage) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		ArrayList[] messageList = messengerDAO.selectMyMessageList(id, nowPage);
		close(conn);
		
		return messageList;
	}

}
