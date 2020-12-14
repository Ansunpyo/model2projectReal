package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FaqDAO;

public class FaqDeleteService {

	public int deleteFaq(int faqId) {
		Connection conn = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(conn);
		int result = faqDAO.deleteFaq(faqId);
		close(conn);
		
		return result;
	}

}
