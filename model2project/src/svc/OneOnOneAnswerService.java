package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneAnswerService {

	public int oneOnOneAnswer(One_On_One one) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		int result = oneOnOneDAO.oneOnOneAnswer(one);
		close(conn);
		
		return result;
	}

}
