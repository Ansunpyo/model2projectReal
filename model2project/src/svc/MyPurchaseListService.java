package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.PurchaseDAO;

public class MyPurchaseListService {

	public LinkedList[] getMyPurchaseList(String id, int nowPage) {
		LinkedList[] payList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
			purchaseDAO.setConnection(conn);
			payList = purchaseDAO.selectMyPurchaseList(id, nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return payList;
	}

}
