package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;

public class OneDelService {

	public int deleteOne(int one_on_one_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
			oneOnOneDAO.setConnection(conn);
			result = oneOnOneDAO.delOne(one_on_one_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
