package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FreeDAO;

public class FreeBoardService {

	public ArrayList[] getFreeList(int nowPage) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		ArrayList[] freeList = freeDAO.selectFreeList(nowPage);
		close(conn);
		
		return freeList;
	}

}
