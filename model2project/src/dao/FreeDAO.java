package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Free;
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
		String sql = "SELECT m.name, f.title, f.contents, f.free_num FROM member AS m JOIN freeboard AS f ON m.number = f.number ORDER BY f.free_num DESC LIMIT ?, " + pageCount;
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
					mem.setName(rs.getString("name"));
					fr.setTitle(rs.getString("title"));
					fr.setContents(rs.getString("contents"));
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
		String sql = "SELECT F.free_num, f.title, f.contents, m.name FROM freeboard AS f JOIN member AS m ON f.number = m.number WHERE f.free_num = ?";
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
				mem.setName(rs.getString("name"));
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
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_num);
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
				fr.setContents(rs.getString("contents"));
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
		String sql = "SELECT f.title, f.contents, f.free_num, m.name FROM freeboard AS f JOIN member AS m on f.number = m.number WHERE f.number = (SELECT number FROM member WHERE id = ?) ORDER BY f.free_num DESC LIMIT ?, " + pageCount;
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

}
