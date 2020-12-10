package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CategoryDAO;
import dao.FavoritesDAO;
import vo.Subject;

public class CategoryListService {

	public ArrayList<Subject> getSubjectList() {
		Connection conn = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(conn);
		ArrayList<Subject> subList = categoryDAO.selectSubjectList();
		close(conn);
		
		return subList;
	}

}
