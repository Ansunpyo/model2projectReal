package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.PurchaseDAO;
import vo.Pay;

public class MyPurchaseListService {

	public LinkedList[] getMyPurchaseList(String id, int nowPage) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		LinkedList[] payList = purchaseDAO.selectMyPurchaseList(id, nowPage);
		close(conn);
		
		return payList;
	}

}
