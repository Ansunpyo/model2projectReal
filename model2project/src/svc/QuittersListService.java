package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MembersDAO;
import vo.Member;

public class QuittersListService {

	public ArrayList<Member> getQuittersList(int nowPage) {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		ArrayList<Member> memList = membersDAO.selectQuittersList(nowPage);
		close(conn);
		
		return memList;
	}

}
