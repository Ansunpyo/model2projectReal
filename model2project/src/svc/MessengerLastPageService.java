package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;

public class MessengerLastPageService {

	public int getMessengerLastPage(String id) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		int lastPage = messengerDAO.getMessageNumber(id);
		close(conn);
		
		return lastPage;
	}

}
