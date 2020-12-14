package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;

public class FreeViewService {

	public LinkedList<Object> getFreeView(int free_num) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		LinkedList<Object> freeList = freeDAO.selectFreeView(free_num);
		close(conn);
		
		return freeList;
	}

}
