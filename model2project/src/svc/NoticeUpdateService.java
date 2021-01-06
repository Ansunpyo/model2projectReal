package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeUpdateService {

	public int updateNotice(Notice not) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(conn);
			result = noticeDAO.updateNotice(not);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
