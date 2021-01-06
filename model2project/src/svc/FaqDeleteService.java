package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FaqDAO;

public class FaqDeleteService {

	public int deleteFaq(int faqId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			FaqDAO faqDAO = FaqDAO.getInstance();
			faqDAO.setConnection(conn);
			result = faqDAO.deleteFaq(faqId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
