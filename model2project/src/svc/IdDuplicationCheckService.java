package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MembersDAO;

public class IdDuplicationCheckService {

	public String dupChk(String id) {
		String result = null;
		Connection conn = null;
		try {
			conn = getConnection();
			MembersDAO membersDAO = MembersDAO.getInstance();
			membersDAO.setConnection(conn);
			result = membersDAO.dupChk(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
