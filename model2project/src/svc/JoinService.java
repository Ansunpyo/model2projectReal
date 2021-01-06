package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.JoinDAO;
import vo.Member;

public class JoinService {

	public Member getJoinTeacherMember(String id, String pw, String classify, String name, String email, String gender, String major, String education) {
		Member joinedMember = null;
		Connection conn = null;
		try {
			JoinDAO joinDAO = JoinDAO.getInstance();
			conn = getConnection();
			joinDAO.setConnection(conn);
			joinedMember = joinDAO.joinTeacher(id, pw, classify, name, email, gender, major, education);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return joinedMember;
	}

	public Member getJoinStudentMember(String id, String pw, String classify, String name, String email, String gender) {
		Member joinedMember = null;
		Connection conn = null;
		try {
			JoinDAO joinDAO = JoinDAO.getInstance();
			conn = getConnection();
			joinDAO.setConnection(conn);
			joinedMember = joinDAO.joinStudent(id, pw, classify, name, email, gender);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return joinedMember;
	}

}
