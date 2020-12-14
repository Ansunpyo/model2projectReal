package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.JoinDAO;
import vo.Member;

public class JoinService {

	public Member getJoinTeacherMember(String id, String pw, String classify, String name, String email, String gender, String major, String education) {
		JoinDAO joinDAO = JoinDAO.getInstance();
		Connection conn = getConnection();
		joinDAO.setConnection(conn);
		Member joinedMember = joinDAO.joinTeacher(id, pw, classify, name, email, gender, major, education);
		close(conn);
		return joinedMember;
	}

	public Member getJoinStudentMember(String id, String pw, String classify, String name, String email, String gender) {
		JoinDAO joinDAO = JoinDAO.getInstance();
		Connection conn = getConnection();
		joinDAO.setConnection(conn);
		Member joinedMember = joinDAO.joinStudent(id, pw, classify, name, email, gender);
		close(conn);
		return joinedMember;
	}

}
