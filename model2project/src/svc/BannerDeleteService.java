package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BannerDAO;

public class BannerDeleteService {

	public int deleteBanner(String img) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			BannerDAO bannerDAO = BannerDAO.getInstance();
			bannerDAO.setConnection(conn);
			result = bannerDAO.deleteBanner(img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
