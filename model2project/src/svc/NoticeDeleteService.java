package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;

public class NoticeDeleteService {

	public int deleteNotice(int notice_num) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		int result = noticeDAO.deleteNotice(notice_num);
		close(conn);
		
		return result;
	}

}
