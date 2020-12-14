package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;

public class FreeBoardService {

	public LinkedList[] getFreeList(int nowPage) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		LinkedList[] freeList = freeDAO.selectFreeList(nowPage);
		close(conn);
		
		return freeList;
	}

}
