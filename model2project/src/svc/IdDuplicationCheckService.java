package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MembersDAO;

public class IdDuplicationCheckService {

	public String dupChk(String id) {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		String result = membersDAO.dupChk(id);
		close(conn);
		
		return result;
	}

}
