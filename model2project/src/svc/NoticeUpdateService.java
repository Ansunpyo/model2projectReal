package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeUpdateService {

	public int updateNotice(Notice not) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		int result = noticeDAO.updateNotice(not);
		close(conn);
		
		return result;
	}

}
