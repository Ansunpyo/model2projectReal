package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class LoginDAO {

	private static LoginDAO loginDAO;
	private Connection conn;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if(loginDAO == null) {
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public Member selectLoginMember(String id, String pw) {
		Member loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM member WHERE id = ? AND password = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
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

	public Member getAutoMember(String id) {
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
