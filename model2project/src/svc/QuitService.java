package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.QuitDAO;

public class QuitService {

	public int deleteMember(String id) {
		QuitDAO quitDAO = QuitDAO.getInstance();
		Connection conn = getConnection();
		quitDAO.setConnection(conn);
		int result = quitDAO.deleteMember(id);
		close(conn);
		return result;
	}

}
