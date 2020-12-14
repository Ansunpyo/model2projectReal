package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeViewService {

	public Notice getNoticeView(int notice_num) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		Notice not = noticeDAO.selectNoticeView(notice_num);
		close(conn);
		
		return not;
	}

}
