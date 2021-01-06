package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneAnswerService {

	public int oneOnOneAnswer(One_On_One one) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
			oneOnOneDAO.setConnection(conn);
			result = oneOnOneDAO.oneOnOneAnswer(one);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
