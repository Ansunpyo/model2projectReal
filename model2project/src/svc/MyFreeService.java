package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;

public class MyFreeService {

	public LinkedList[] getMyFreeList(String id, int nowPage) {
		LinkedList[] freeList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			freeList = freeDAO.selectMyFreeList(id, nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return freeList;
	}

}
