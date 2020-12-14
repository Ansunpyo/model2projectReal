package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FaqDAO;
import vo.Faq;

public class FaqWriteService {

	public int writeFaq(Faq faq) {
		Connection conn = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(conn);
		int result = faqDAO.writeFaq(faq);
		close(conn);
		
		return result;
	}

}
