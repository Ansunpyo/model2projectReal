package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;

public class MessageDeleteService {

	public int deleteMessage(int message_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			MessengerDAO messengerDAO = MessengerDAO.getInstance();
			messengerDAO.setConnection(conn);
			result = messengerDAO.deleteMessage(message_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
