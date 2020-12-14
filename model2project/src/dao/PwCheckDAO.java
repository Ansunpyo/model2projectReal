package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static db.JdbcUtil.*;
import vo.Member;

public class PwCheckDAO {
	private static PwCheckDAO pwCheckDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private PwCheckDAO() {
		
	}

	public static PwCheckDAO getInstance() {
		if(pwCheckDAO == null) {
			pwCheckDAO = new PwCheckDAO();
		}
		return pwCheckDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public boolean pwCheck(Member mem) {
		String sql = "SELECT id, email FROM member WHERE id = ? AND email = ?";
		boolean isIdEmailRight = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isIdEmailRight = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isIdEmailRight;
	}

	public String yourPw(Member mem) {
		String sql = "SELECT password FROM member WHERE id = ?";
		String pw = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return pw;
	}
}
