package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FreeDAO;

public class FreeViewService {

	public LinkedList<Object> getFreeView(int free_num) {
		LinkedList<Object> freeList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			freeList = freeDAO.selectFreeView(free_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return freeList;
	}

	public LinkedList[] getFreeCom(int free_num) {
		LinkedList[] freeComList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			freeComList = freeDAO.selectFreeCom(free_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return freeComList;
	}

}
