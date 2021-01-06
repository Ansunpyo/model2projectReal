package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.CategoryDAO;
import vo.Subject;

public class CategoryListService {

	public LinkedList<Subject> getSubjectList() {
		LinkedList<Subject> subList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			CategoryDAO categoryDAO = CategoryDAO.getInstance();
			categoryDAO.setConnection(conn);
			subList = categoryDAO.selectSubjectList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return subList;
	}

}
