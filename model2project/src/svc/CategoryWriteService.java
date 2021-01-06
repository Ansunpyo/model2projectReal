package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CategoryDAO;
import vo.Subject;

public class CategoryWriteService {

	public int insertSubject(Subject sub) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			CategoryDAO categoryDAO = CategoryDAO.getInstance();
			categoryDAO.setConnection(conn);
			result = categoryDAO.insertSubject(sub);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
