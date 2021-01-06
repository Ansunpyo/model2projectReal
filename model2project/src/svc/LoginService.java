package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;

public class LoginService {

	public Member getLoginMember(String id, String pw) {
		Member loginMember = null;
		Connection conn = null;
		try {
			LoginDAO loginDAO = LoginDAO.getInstance();
			conn = getConnection();
			loginDAO.setConnection(conn);
			loginMember = loginDAO.selectLoginMember(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return loginMember;
	}

	public Member getAutoLogin(String id) {
		Member loginMember = null;
		Connection conn = null;
		try {
			LoginDAO loginDAO = LoginDAO.getInstance();
			conn = getConnection();
			loginDAO.setConnection(conn);
			loginMember = loginDAO.getAutoMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return loginMember;
	}

}
