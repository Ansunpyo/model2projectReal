package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.Notice;

public class NoticeWriteService {

	public int writeNotice(Notice not) {
		Connection conn = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(conn);
		int result = noticeDAO.writeNotice(not);
		close(conn);
		
		return result;
	}

}
