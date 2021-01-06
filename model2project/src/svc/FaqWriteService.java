package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FaqDAO;
import vo.Faq;

public class FaqWriteService {

	public int writeFaq(Faq faq) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			FaqDAO faqDAO = FaqDAO.getInstance();
			faqDAO.setConnection(conn);
			result = faqDAO.writeFaq(faq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
