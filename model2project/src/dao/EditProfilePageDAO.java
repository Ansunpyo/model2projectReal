package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class EditProfilePageDAO {
	private static EditProfilePageDAO editProfilePageDAO;
	private Connection conn;
	
	private EditProfilePageDAO() {
		
	}
	
	public static EditProfilePageDAO getInstance() {
		if(editProfilePageDAO == null) {
			editProfilePageDAO = new EditProfilePageDAO();
		}
		return editProfilePageDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public Member selectLoginMember(String id) {
		Member loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM member WHERE id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setPassword(rs.getString("password"));
				loginMember.setName(rs.getString("name"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));
				loginMember.setClassify(rs.getString("classify"));
				if(loginMember.getClassify().equals("교사")) {
					loginMember.setMajor(rs.getString("major"));
					loginMember.setEducation(rs.getString("education"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}
