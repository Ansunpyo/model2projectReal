package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Free;
import vo.Free_Comment;
import vo.Member;

public class FreeDAO {
	private static FreeDAO freeDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private FreeDAO() {
		
	}

	public static FreeDAO getInstance() {
		if(freeDAO == null) {
			freeDAO = new FreeDAO();
		}
		return freeDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public LinkedList[] selectFreeList(int nowPage) {
		String sql = "SELECT m.id, m.name, q.id AS qid, q.name AS qname, f.title, f.contents, f.free_num FROM member AS m RIGHT JOIN freeboard AS f ON m.number = f.number LEFT JOIN quitter AS q ON q.number = f.number ORDER BY f.free_num DESC LIMIT ?, " + pageCount;
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Free> freeContentList = new LinkedList<Free>();
		LinkedList[] freeList = null;
		Member mem = null;
		Free fr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					fr = new Free();
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
					fr.setTitle(rs.getString("title"));
					fr.setContents(rs.getString("contents").replace("<br/>", " "));
					fr.setFree_num(rs.getInt("free_num"));
					memList.add(mem);
					freeContentList.add(fr);
				} while(rs.next());
			}
			freeList = new LinkedList[] {freeContentList, memList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return freeList;
	}

	public int getFreeNumber() {
		String sql = "SELECT count(*) AS c FROM freeboard";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
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

	public int writeFree(String id, Free fr) {
		String sql = "INSERT INTO freeboard VALUES ((SELECT number FROM member WHERE id = ?), ?, ?, NULL)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, fr.getTitle());
			pstmt.setString(3, fr.getContents());
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

	public LinkedList<Object> selectFreeView(int free_num) {
		String sql = "SELECT f.free_num, f.title, f.contents, m.id, m.name, q.id AS qid, q.name AS qname FROM member AS m RIGHT JOIN freeboard AS f ON f.number = m.number LEFT JOIN quitter AS q ON f.number = q.number WHERE f.free_num = ?";
		LinkedList<Object> freeViewList = null;
		Member mem = null;
		Free fr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem = new Member();
				fr = new Free();
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
				fr.setTitle(rs.getString("title"));
				fr.setContents(rs.getString("contents"));
				fr.setFree_num(rs.getInt("free_num"));
			}
			freeViewList = new LinkedList<Object>();
			freeViewList.add(fr);
			freeViewList.add(mem);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return freeViewList;
	}

	public int deleteFree(int free_num) {
		String sql = "DELETE FROM freeboard WHERE free_num = ?";
		int result = 0;
		boolean hasReply = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				sql = "SELECT comment_num FROM free_comment WHERE free_num = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, free_num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						hasReply = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(hasReply) {
					sql = "DELETE FROM free_comment WHERE free_num = ?";
					result = 0;
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, free_num);
						result = pstmt.executeUpdate();
						if(result > 0) {
							commit(conn);
						} else {
							rollback(conn);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					commit(conn);
				}
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Free updateFreePage(int free_num) {
		String sql = "SELECT * FROM freeboard WHERE free_num = ?";
		Free fr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fr = new Free();
				fr.setTitle(rs.getString("title"));
				fr.setContents(rs.getString("contents").replace("<br/>", "\n"));
				fr.setFree_num(rs.getInt("free_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return fr;
	}

	public int updateFree(Free fr) {
		String sql = "UPDATE freeboard SET title = ?, contents = ? WHERE free_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fr.getTitle());
			pstmt.setString(2, fr.getContents());
			pstmt.setInt(3, fr.getFree_num());
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

	public LinkedList[] selectMyFreeList(String id, int nowPage) {
		String sql = "SELECT f.title, f.contents, f.free_num, m.id, m.name FROM freeboard AS f JOIN member AS m on f.number = m.number WHERE f.number = (SELECT number FROM member WHERE id = ?) ORDER BY f.free_num DESC LIMIT ?, " + pageCount;
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Free> freeContentList = new LinkedList<Free>();
		LinkedList[] freeList = null;
		Member mem = null;
		Free fr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mem = new Member();
					fr = new Free();
					mem.setId(rs.getString("id"));
					mem.setName(rs.getString("name"));
					fr.setTitle(rs.getString("title"));
					fr.setContents(rs.getString("contents"));
					fr.setFree_num(rs.getInt("free_num"));
					memList.add(mem);
					freeContentList.add(fr);
				} while(rs.next());
			}
			freeList = new LinkedList[] {memList, freeContentList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return freeList;
	}

	public int getMyFreeNumber(String id) {
		String sql = "SELECT count(*) AS c FROM freeboard AS f WHERE f.number = (SELECT number FROM member WHERE id = ?)";
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

	public LinkedList<Free> selectMainFreeList() {
		String sql = "SELECT * FROM freeboard ORDER BY free_num DESC LIMIT 0, " + pageCount;
		LinkedList<Free> freeList = new LinkedList<>();
		Free fr = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					fr = new Free();
					fr.setTitle(rs.getString("title"));
					fr.setFree_num(rs.getInt("free_num"));
					freeList.add(fr);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return freeList;
	}

	public LinkedList[] selectFreeCom(int free_num) {
		String sql = "SELECT m.id, m.number, m.name, fc.contents, fc.comment_num, fc.time, fc.parent, fc.step FROM free_comment AS fc LEFT JOIN member AS m ON fc.number = m.number WHERE fc.free_num = ? ORDER BY comment_num";
		LinkedList[] freeComList = null;
		LinkedList<Member> memList = new LinkedList<>();
		LinkedList<Free_Comment> fCList = new LinkedList<>();
		Member mem = null;
		Free_Comment fc = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					mem = new Member();
					fc = new Free_Comment();
					mem.setNumber(rs.getInt("number"));
					if(rs.getString("id") == null) {
						mem.setId("");
					} else {
						mem.setId(rs.getString("id"));
					}
					if(rs.getString("name") == null) {
						mem.setName("<탈퇴한 회원>");
					} else {
						mem.setName(rs.getString("name"));
					}
					fc.setContents(rs.getString("contents"));
					fc.setComment_num(rs.getInt("comment_num"));
					fc.setTime(rs.getString("time"));
					fc.setParent(rs.getInt("parent"));
					fc.setStep(rs.getInt("step"));
					memList.add(mem);
					fCList.add(fc);
				} while (rs.next());
				freeComList = new LinkedList[]{memList, fCList};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return freeComList;
	}

	public int exComment(Free_Comment fc, int number, int parent, int free_num) {
		String sql = "INSERT INTO free_comment VALUES(?, ?, ?, NULL, NOW(), ?, (SELECT * FROM (SELECT step FROM free_comment WHERE comment_num = ?) AS x) + 1)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			pstmt.setInt(2, number);
			pstmt.setString(3, fc.getContents());
			pstmt.setInt(4, parent);
			pstmt.setInt(5, parent);
			result = pstmt.executeUpdate();
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int delComment(int free_num, int comment_num) {
		String sql = "SELECT free_num FROM free_comment WHERE free_num = ? AND parent = ?";
		int result = 0;
		boolean hasReply = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			pstmt.setInt(2, comment_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hasReply = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(hasReply) {
			sql = "UPDATE free_comment SET contents = '<삭제됨>' WHERE free_num = ? AND comment_num = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, free_num);
				pstmt.setInt(2, comment_num);
				result = pstmt.executeUpdate();
				if (result > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			sql = "DELETE FROM free_comment WHERE free_num = ? AND comment_num = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, free_num);
				pstmt.setInt(2, comment_num);
				result = pstmt.executeUpdate();
				if(result > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		}
		return result;
	}

	public int writeComment(int free_num, int number, String contents) {
		String sql = "INSERT INTO free_comment VALUES (?, ?, ?, NULL, NOW(), 0, 0)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
			pstmt.setInt(2, number);
			pstmt.setString(3, contents);
			result = pstmt.executeUpdate();
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
