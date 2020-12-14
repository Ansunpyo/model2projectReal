package dao;

import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditProfileDAO {

	private static EditProfileDAO editProfileDAO;
	private Connection conn;
	
	private EditProfileDAO() {
		
	}
	
	public static EditProfileDAO getInstance() {
		if(editProfileDAO == null) {
			editProfileDAO = new EditProfileDAO();
		}
		return editProfileDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int doEditProfile(String id, String pw, String name, String email, String gender, String major, String education) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET password=?, name=?, email=?, gender=?, major=?, education=? WHERE id=?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, gender);
			pstmt.setString(5, major);
			pstmt.setString(6, education);
			pstmt.setString(7, id);
			
			result = pstmt.executeUpdate();
			commit(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
