package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PurchaseDAO;

public class PurchaseDoRefundService {

	public int doRefund(int pay_number) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			PurchaseDAO purchaseDAO = PurchaseDAO.getInstance();
			purchaseDAO.setConnection(conn);
			result = purchaseDAO.doRefund(pay_number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
