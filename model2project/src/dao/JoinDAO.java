package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class JoinDAO {

	private static JoinDAO joinDAO;
	private Connection conn;
	ResultSet rs;
	private JoinDAO() {
		
	}
	
	public static JoinDAO getInstance() {
		if(joinDAO == null) {
			joinDAO = new JoinDAO();
		}
		return joinDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public Member joinTeacher(String id, String pw, String classify, String name, String email, String gender, String major, String education) {
		Member joinedMember = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, classify);
			pstmt.setString(3, id);
			pstmt.setString(4, pw);
			pstmt.setString(5, name);
			pstmt.setString(6, email);
			pstmt.setString(7, gender);
			pstmt.setString(8, major);
			pstmt.setString(9, education);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sql = "SELECT * FROM member WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					joinedMember = new Member();
					joinedMember.setNumber(rs.getInt("number"));
					joinedMember.setId(id);
					joinedMember.setPassword(pw);
					joinedMember.setClassify(classify);
					joinedMember.setEducation(education);
					joinedMember.setEmail(email);
					joinedMember.setGender(gender);
					joinedMember.setMajor(major);
					joinedMember.setName(name);
				}
			} else {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(conn);
		}
		return joinedMember;
	}

	public Member joinStudent(String id, String pw, String classify, String name, String email, String gender) {
		Member joinedMember = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, classify);
			pstmt.setString(3, id);
			pstmt.setString(4, pw);
			pstmt.setString(5, name);
			pstmt.setString(6, email);
			pstmt.setString(7, gender);
			pstmt.setString(8, null);
			pstmt.setString(9, null);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sql = "SELECT * FROM member WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					joinedMember = new Member();
					joinedMember.setNumber(rs.getInt("number"));
					joinedMember.setId(id);
					joinedMember.setPassword(pw);
					joinedMember.setClassify(classify);
					joinedMember.setEmail(email);
					joinedMember.setGender(gender);
					joinedMember.setName(name);
				}
			} else {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(conn);
		}
		return joinedMember;
	}

}
