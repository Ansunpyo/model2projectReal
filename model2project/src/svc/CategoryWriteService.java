package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CategoryDAO;
import vo.Subject;

public class CategoryWriteService {

	public int insertSubject(Subject sub) {
		Connection conn = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(conn);
		int result = categoryDAO.insertSubject(sub);
		close(conn);
		
		return result;
	}

}
