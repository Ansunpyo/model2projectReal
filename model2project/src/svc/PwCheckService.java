package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.PwCheckDAO;
import vo.Member;

public class PwCheckService {

	public boolean pwCheck(Member mem) {
		Connection conn = getConnection();
		PwCheckDAO pwCheckDAO = PwCheckDAO.getInstance();
		pwCheckDAO.setConnection(conn);
		boolean isIdEmailRight = pwCheckDAO.pwCheck(mem);
		close(conn);
		
		return isIdEmailRight;
	}

	public String yourPw(Member mem) {
		Connection conn = getConnection();
		PwCheckDAO pwCheckDAO = PwCheckDAO.getInstance();
		pwCheckDAO.setConnection(conn);
		String pw = pwCheckDAO.yourPw(mem);
		close(conn);
		
		return pw;
	}

}
