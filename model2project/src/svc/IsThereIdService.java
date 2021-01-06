package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;

public class IsThereIdService {

	public boolean isThere(String receiver) {
		boolean isThereId = false;
		Connection conn = null;
		try {
			MessengerDAO messengerDAO = MessengerDAO.getInstance();
			conn = getConnection();
			messengerDAO.setConnection(conn);
			isThereId = messengerDAO.isThereId(receiver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isThereId;
	}

}
