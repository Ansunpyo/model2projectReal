package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.QuitDAO;

public class QuitService {

	public int deleteMember(String id) {
		int result = 0;
		Connection conn = null;
		try {
			QuitDAO quitDAO = QuitDAO.getInstance();
			conn = getConnection();
			quitDAO.setConnection(conn);
			result = quitDAO.deleteMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
