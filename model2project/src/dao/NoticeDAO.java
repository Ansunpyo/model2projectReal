package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Notice;

public class NoticeDAO {
	private static NoticeDAO noticeDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private NoticeDAO() {
		
	}

	public static NoticeDAO getInstance() {
		if(noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public LinkedList<Notice> selectNoticeList(int nowPage) {
		String sql = "SELECT * FROM notice AS n ORDER BY n.notice_num DESC LIMIT ?, " + pageCount;
		LinkedList<Notice> noticeList = new LinkedList<Notice>();
		Notice not = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					not = new Notice();
					not.setTitle(rs.getString("title"));
					not.setContents(rs.getString("contents").replace("<br/>", " "));
					not.setNotice_num(rs.getInt("notice_num"));
					noticeList.add(not);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeList;
	}

	public int getNoticeNumber() {
		String sql = "SELECT count(*) AS c FROM notice";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Notice selectNoticeView(int notice_num) {
		String sql = "SELECT * FROM notice WHERE notice_num = ?";
		Notice not = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				not = new Notice();
				not.setTitle(rs.getString("title"));
				not.setContents(rs.getString("contents"));
				not.setNotice_num(rs.getInt("notice_num"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return not;
	}

	public int deleteNotice(int notice_num) {
		String sql = "DELETE FROM notice WHERE notice_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Notice updateNoticePage(int notice_num) {
		String sql = "SELECT * FROM notice WHERE notice_num = ?";
		Notice not = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				not = new Notice();
				not.setTitle(rs.getString("title"));
				not.setContents(rs.getString("contents").replace("<br/>", "\n"));
				not.setNotice_num(rs.getInt("notice_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return not;
	}

	public int updateNotice(Notice not) {
		String sql = "UPDATE notice SET title = ?, contents = ? WHERE notice_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, not.getTitle());
			pstmt.setString(2, not.getContents());
			pstmt.setInt(3, not.getNotice_num());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int writeNotice(Notice not) {
		String sql = "INSERT INTO notice VALUES (?, ?, NULL)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, not.getTitle());
			pstmt.setString(2, not.getContents());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
