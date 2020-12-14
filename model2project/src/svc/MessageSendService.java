package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MessengerDAO;
import vo.Member;
import vo.Message;

public class MessageSendService {

	public int sendMessage(String id, Member mem, Message mes) {
		Connection conn = getConnection();
		MessengerDAO messengerDAO = MessengerDAO.getInstance();
		messengerDAO.setConnection(conn);
		int result = messengerDAO.sendMessage(id, mem, mes);
		close(conn);
		
		return result;
	}

}
