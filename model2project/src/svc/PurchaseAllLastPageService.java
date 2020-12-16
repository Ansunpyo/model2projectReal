package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseAllLastPageService {

	public int getPurchaseLastPage() {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int lastPage = purchaseDAO.getPurchaseNumber();
		close(conn);
		
		return lastPage;
	}

}
