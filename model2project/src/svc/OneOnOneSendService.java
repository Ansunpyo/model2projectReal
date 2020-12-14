package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneSendService {

	public int sendOneOnOne(String id, One_On_One one) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		int result = oneOnOneDAO.sendOneOnOne(id, one);
		close(conn);
		
		return result;
	}

}
