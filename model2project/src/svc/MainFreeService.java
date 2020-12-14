package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;
import vo.Free;

public class MainFreeService {

	public LinkedList<Free> getMainFree() {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		LinkedList<Free> freeList = freeDAO.selectMainFreeList();
		close(conn);
		
		return freeList;
	}

}
