package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.MembersDAO;
import vo.Member;

public class QuittersListService {

	public LinkedList<Member> getQuittersList(int nowPage) {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		LinkedList<Member> memList = membersDAO.selectQuittersList(nowPage);
		close(conn);
		
		return memList;
	}

}
