package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeUpdatePageService {

	public Notice updateNotice(int notice_num) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		Notice not = noticeDAO.updateNoticePage(notice_num);
		close(conn);
		
		return not;
	}

}
