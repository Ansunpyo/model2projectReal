package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PwCheckDAO;
import vo.Member;

public class PwCheckService {

	public boolean pwCheck(Member mem) {
		boolean isIdEmailRight = false;
		Connection conn = null;
		try {
			conn = getConnection();
			PwCheckDAO pwCheckDAO = PwCheckDAO.getInstance();
			pwCheckDAO.setConnection(conn);
			isIdEmailRight = pwCheckDAO.pwCheck(mem);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isIdEmailRight;
	}

	public String yourPw(Member mem) {
		String pw = null;
		Connection conn = null;
		try {
			conn = getConnection();
			PwCheckDAO pwCheckDAO = PwCheckDAO.getInstance();
			pwCheckDAO.setConnection(conn);
			pw = pwCheckDAO.yourPw(mem);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return pw;
	}

}
