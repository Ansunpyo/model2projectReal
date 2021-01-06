package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.NoticeDAO;
import vo.Notice;

public class MainNoticeService {

	public LinkedList<Notice> getMainNotice() {
		LinkedList<Notice> noticeList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(conn);
			noticeList = noticeDAO.selectNoticeList(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return noticeList;
	}

}
