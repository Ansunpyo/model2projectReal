package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;

public class NoticeLastPageService {

	public int getNoticeLastPage() {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		int lastPage = noticeDAO.getNoticeNumber();
		close(conn);
		
		return lastPage;
	}

}
