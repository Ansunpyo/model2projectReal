package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Member;
import vo.Message;

public class MessengerDAO {
	private static MessengerDAO messengerDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private MessengerDAO() {
		
	}
	
	public int getMessageNumber(String id) {
		String sql = "SELECT count(*) AS c FROM message where receiver = (SELECT number FROM member WHERE id = ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public static MessengerDAO getInstance() {
		if(messengerDAO == null) {
			messengerDAO = new MessengerDAO();
		}
		return messengerDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public LinkedList[] selectMessageList(String id, int nowPageNumber) {
		String sql = "SELECT member.name, message.title, message.contents, message.time, message.message_num from member join message on member.number = message.sender and message.receiver = (SELECT number FROM member WHERE ID = ?) ORDER BY message.message_num DESC LIMIT ?, " + pageCount;
		
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Message> mesList = new LinkedList<Message>();
		LinkedList[] messageList = null;
		Member mem = null;
		Message mes = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPageNumber - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					mes = new Message();

					mem.setName(rs.getString("name"));
					mes.setTitle(rs.getString("title"));
					mes.setContents(rs.getString("contents"));
					mes.setTime(rs.getString("time"));
					mes.setMessage_num(rs.getInt("message_num"));
					mesList.add(mes);
					memList.add(mem);
				} while(rs.next());
			}
			messageList = new LinkedList[] {mesList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return messageList;
	}

	public int deleteMessage(int message_num) {
		int result = 0;
		String sql = "DELETE FROM message WHERE message_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message_num);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int sendMessage(String id, Member mem, Message mes) {
		int result = 0;
		String sql = "INSERT INTO message VALUES ((SELECT number FROM member WHERE id = ?), (SELECT number FROM member WHERE name = ?), ?, ?, NOW(), NULL)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mes.getTitle());
			pstmt.setString(4, mes.getContents());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public LinkedList[] selectMyMessageList(String id, int nowPage) {
		String sql = "SELECT member.name, message.title, message.contents, message.time, message.message_num from member join message on member.number = message.receiver and message.sender = (SELECT number FROM member WHERE ID = ?) ORDER BY message.message_num DESC LIMIT ?, " + pageCount;
		
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Message> mesList = new LinkedList<Message>();
		LinkedList[] messageList = null;
		Member mem = null;
		Message mes = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					mes = new Message();

					mem.setName(rs.getString("name"));
					mes.setTitle(rs.getString("title"));
					mes.setContents(rs.getString("contents"));
					mes.setTime(rs.getString("time"));
					mes.setMessage_num(rs.getInt("message_num"));
					mesList.add(mes);
					memList.add(mem);
				} while(rs.next());
			}
			messageList = new LinkedList[] {mesList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return messageList;
	}

	public int getMyMessageNumber(String id) {
		String sql = "SELECT count(*) AS c FROM message where sender = (SELECT number FROM member WHERE id = ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}
}
