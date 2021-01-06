package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.MembersDAO;
import vo.Member;

public class MembersListService {

	public LinkedList<Member> getMembersList(int nowPage) {
		LinkedList<Member> memList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			MembersDAO membersDAO = MembersDAO.getInstance();
			membersDAO.setConnection(conn);
			memList = membersDAO.selectMembersList(nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return memList;
	}

}
