package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.CategoryDAO;
import vo.Subject;

public class CategoryListService {

	public LinkedList<Subject> getSubjectList() {
		Connection conn = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(conn);
		LinkedList<Subject> subList = categoryDAO.selectSubjectList();
		close(conn);
		
		return subList;
	}

}
