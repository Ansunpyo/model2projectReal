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
		String sql = "SELECT member.id, q.id As qid, q.name AS qname, member.name, message.title, message.contents, message.time, message.message_num FROM member RIGHT JOIN message ON member.number = message.sender LEFT JOIN quitter AS q ON q.number = message.sender WHERE message.receiver = (SELECT number FROM member WHERE ID = ?) ORDER BY message.message_num DESC LIMIT ?, " + pageCount;
		
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
					
					if(rs.getString("id") != null) {
						mem.setId(rs.getString("id"));
					} else {
						mem.setId(rs.getString("qid"));
					}
					if(rs.getString("name") != null) {
						mem.setName(rs.getString("name"));
					} else {
						mem.setName(rs.getString("qname"));
					}
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
		String sql = "INSERT INTO message VALUES ((SELECT number FROM member WHERE id = ?), (SELECT number FROM member WHERE id = ?), ?, ?, NOW(), NULL)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, mem.getId());
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
		String sql = "SELECT member.id, q.id AS qid, member.name, q.name AS qname, message.title, message.contents, message.time, message.message_num from member LEFT JOIN quitter AS q ON member.number = q.number JOIN message ON member.number = message.receiver AND message.sender = (SELECT number FROM member WHERE ID = ?) ORDER BY message.message_num DESC LIMIT ?, " + pageCount;
		
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
					if(rs.getString("name") != null) {
						mem.setName(rs.getString("name"));
					} else {
						mem.setName(rs.getString("qname"));
					}
					if(rs.getString("id") != null) {
						mem.setId(rs.getString("id"));
					} else {
						mem.setId(rs.getString("qid"));
					}
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

	public boolean isThereId(String receiver) {
		String sql = "SELECT id FROM member WHERE id = ?";
		boolean isThereId = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receiver);
			rs = pstmt.executeQuery();
			if(rs.next()) isThereId = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return isThereId;
	}
}
