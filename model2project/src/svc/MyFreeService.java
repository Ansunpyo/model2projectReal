package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;

public class MyFreeService {

	public LinkedList[] getMyFreeList(String id, int nowPage) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		LinkedList[] freeList = freeDAO.selectMyFreeList(id, nowPage);
		close(conn);
		
		return freeList;
	}

}
