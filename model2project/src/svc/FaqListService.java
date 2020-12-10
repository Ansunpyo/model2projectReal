package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FaqDAO;
import vo.Faq;

public class FaqListService {

	public ArrayList<Faq> getFaqList() {
		Connection conn = getConnection();
		FaqDAO faqDAO = FaqDAO.getInstance();
		faqDAO.setConnection(conn);
		ArrayList<Faq> faqList = faqDAO.selectFaqList();
		close(conn);
		
		return faqList;
	}

}
