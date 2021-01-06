package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.OneOnOneDAO;
import vo.One_On_One;

public class OneOnOneListService {

	public LinkedList<One_On_One> getOneOnOneList(String id) {
		LinkedList<One_On_One> oneOnOneList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
			oneOnOneDAO.setConnection(conn);
			oneOnOneList = oneOnOneDAO.selectOneOnOneList(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return oneOnOneList;
	}

}
