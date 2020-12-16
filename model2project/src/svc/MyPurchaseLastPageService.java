package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class MyPurchaseLastPageService {

	public int getMyPurchaseLastPage(String id) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int lastPage = purchaseDAO.getMyPurchaseNumber(id);
		close(conn);
		
		return lastPage;
	}

}
