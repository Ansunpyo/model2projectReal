package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseAllLastPageService {

	public int getPurchaseLastPage() {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
			purchaseDAO.setConnection(conn);
			lastPage = purchaseDAO.getPurchaseNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
