package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.BannerDAO;
import vo.Banner;

public class BannerListService {

	public LinkedList<Banner> getBannerList() {
		LinkedList<Banner> banList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			BannerDAO bannerDAO = BannerDAO.getInstance();
			bannerDAO.setConnection(conn);
			banList = bannerDAO.selectBannerList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return banList;
	}

}
