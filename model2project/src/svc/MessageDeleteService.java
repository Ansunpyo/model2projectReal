package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;

public class MessageDeleteService {

	public int deleteMessage(int message_num) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		int result = messengerDAO.deleteMessage(message_num);
		close(conn);
		
		return result;
	}

}
