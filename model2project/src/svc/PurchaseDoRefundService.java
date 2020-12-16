package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseDoRefundService {

	public int doRefund(int pay_number) {
		Connection conn = getConnection();
		PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
		purchaseDAO.setConnection(conn);
		int result = purchaseDAO.doRefund(pay_number);
		close(conn);
		
		return result;
	}

}
