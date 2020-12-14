package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneListService {

	public LinkedList<One_On_One> getOneOnOneList(String id) {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		LinkedList<One_On_One> oneOnOneList = oneOnOneDAO.selectOneOnOneList(id);
		close(conn);
		
		return oneOnOneList;
	}

}
