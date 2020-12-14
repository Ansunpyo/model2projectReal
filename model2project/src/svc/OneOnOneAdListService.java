package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.OneOnOneDAO;

public class OneOnOneAdListService {

	public LinkedList[] getOneOnOneAdList(int nowPage) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		LinkedList[] oneOnOneAdList = oneOnOneDAO.selectOneOnOneAdList(nowPage);
		close(conn);
		
		return oneOnOneAdList;
	}

}
