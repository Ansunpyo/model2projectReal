package dao;

import static db.JdbcUtil.commit;

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
		String sql = "DELETE FROM member WHERE id = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			commit(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
