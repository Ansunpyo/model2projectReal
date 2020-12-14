package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.MembersDAO;
import vo.Member;

public class MembersListService {

	public LinkedList<Member> getMembersList(int nowPage) {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		LinkedList<Member> memList = membersDAO.selectMembersList(nowPage);
		close(conn);
		
		return memList;
	}

}
