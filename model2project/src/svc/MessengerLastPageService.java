package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;

public class MessengerLastPageService {

	public int getMessengerLastPage(String id) {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			MessengerDAO messengerDAO = MessengerDAO.getInstance();
			messengerDAO.setConnection(conn);
			lastPage = messengerDAO.getMessageNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
