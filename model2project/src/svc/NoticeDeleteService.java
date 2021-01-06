package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;

public class NoticeDeleteService {

	public int deleteNotice(int notice_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(conn);
			result = noticeDAO.deleteNotice(notice_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
