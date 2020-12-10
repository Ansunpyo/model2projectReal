package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneAdListService {

	public ArrayList[] getOneOnOneAdList(int nowPage) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		ArrayList[] oneOnOneAdList = oneOnOneDAO.selectOneOnOneAdList(nowPage);
		close(conn);
		
		return oneOnOneAdList;
	}

}
