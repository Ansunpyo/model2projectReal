package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.OneOnOneDAO;

public class OneOnOneAdListService {

	public LinkedList[] getOneOnOneAdList(int nowPage) {
		LinkedList[] oneOnOneAdList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
			oneOnOneDAO.setConnection(conn);
			oneOnOneAdList = oneOnOneDAO.selectOneOnOneAdList(nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return oneOnOneAdList;
	}

}
