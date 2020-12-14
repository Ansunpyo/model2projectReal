package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BannerDAO;

public class BannerDeleteService {

	public int deleteBanner(String img) {
		Connection conn = getConnection();
		BannerDAO bannerDAO = BannerDAO.getInstance();
		bannerDAO.setConnection(conn);
		int result = bannerDAO.deleteBanner(img);
		close(conn);
		
		return result;
	}

}
