package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.NoticeDAO;
import vo.Notice;

public class MainNoticeService {

	public LinkedList<Notice> getMainNotice() {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		LinkedList<Notice> noticeList = noticeDAO.selectNoticeList(1);
		close(conn);
		
		return noticeList;
	}

}
