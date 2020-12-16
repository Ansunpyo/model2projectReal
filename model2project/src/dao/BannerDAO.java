package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import static db.JdbcUtil.*;
import vo.Banner;

public class BannerDAO {
	private static BannerDAO bannerDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private BannerDAO() {
		
	}

	public static BannerDAO getInstance() {
		if(bannerDAO == null) {
			bannerDAO = new BannerDAO();
		}
		return bannerDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public LinkedList<Banner> selectBannerList() {
		String sql = "SELECT * FROM banner";
		LinkedList<Banner> banList = new LinkedList<>();
		Banner ban = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					ban = new Banner();
					ban.setImg(rs.getString("img"));
					banList.add(ban);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return banList;
	}

	public int deleteBanner(String img) {
		String sql = "DELETE FROM banner WHERE img = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img);
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

	public int uploadBanner(Banner ban) {
		String sql = "INSERT INTO banner VALUES (?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ban.getImg());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
