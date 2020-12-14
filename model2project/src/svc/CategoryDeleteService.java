package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CategoryDAO;

public class CategoryDeleteService {

	public int deleteSubject(int code) {
		Connection conn = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(conn);
		int result = categoryDAO.deleteSubject(code);
		close(conn);
		
		return result;
	}

}
