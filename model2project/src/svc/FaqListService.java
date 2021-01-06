package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FaqDAO;
import vo.Faq;

public class FaqListService {

	public LinkedList<Faq> getFaqList() {
		LinkedList<Faq> faqList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FaqDAO faqDAO = FaqDAO.getInstance();
			faqDAO.setConnection(conn);
			faqList = faqDAO.selectFaqList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return faqList;
	}

}
