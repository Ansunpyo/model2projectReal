package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseRefundLastPageService {

	public int getRefundLastPage() {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int lastPage = purchaseDAO.getRefundNumber();
		close(conn);
		
		return lastPage;
	}

}
