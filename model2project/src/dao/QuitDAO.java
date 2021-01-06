package dao;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;
import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuitDAO {
	private static QuitDAO quitDAO;
	private Connection conn;
	
	private QuitDAO() {
		
	}
	
	public static QuitDAO getInstance() {
		if(quitDAO == null) {
			quitDAO = new QuitDAO();
		}
		return quitDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int deleteMember(String id) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO quitter VALUES (?, (SELECT email FROM member WHERE id = ?), (SELECT number FROM member WHERE id = ?), (SELECT name FROM member WHERE id = ?))";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
				return result;
			} else {
				sql = "DELETE FROM member WHERE id = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					
					result = pstmt.executeUpdate();
					if (result <= 0) {
						rollback(conn);
					}
					commit(conn);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(conn);
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
