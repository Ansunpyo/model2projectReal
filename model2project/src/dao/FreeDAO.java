package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	public ArrayList[] selectFreeList(int nowPage) {
		String sql = "SELECT m.name, f.title, f.contents, f.free_num FROM member AS m JOIN freeboard AS f ON m.number = f.number ORDER BY f.free_num DESC LIMIT ?, " + pageCount;
		ArrayList<Member> memList = new ArrayList<Member>();
		ArrayList<Free> freeContentList = new ArrayList<Free>();
		ArrayList[] freeList = null;
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
			freeList = new ArrayList[] {freeContentList, memList};
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
				if (result % 5 != 0) {
					result = (result / 5) + 1;
				} else {
					result = result / 5;
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
