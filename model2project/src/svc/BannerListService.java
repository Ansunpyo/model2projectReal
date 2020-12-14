package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.BannerDAO;
import vo.Banner;

public class BannerListService {

	public LinkedList<Banner> getBannerList() {
		Connection conn = getConnection();
		BannerDAO bannerDAO = BannerDAO.getInstance();
		bannerDAO.setConnection(conn);
		LinkedList<Banner> banList = bannerDAO.selectBannerList();
		close(conn);
		
		return banList;
	}

}
