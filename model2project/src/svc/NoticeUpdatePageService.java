package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeUpdatePageService {

	public Notice updateNotice(int notice_num) {
		Notice not = null;
		Connection conn = null;
		try {
			conn = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(conn);
			not = noticeDAO.updateNoticePage(notice_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return not;
	}

}
