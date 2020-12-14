package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BannerDAO;
import vo.Banner;

public class BannerUploadService {

	public int uploadBanner(Banner ban) {
		int result = 0;
		Connection conn = getConnection();
		BannerDAO bannerDAO = BannerDAO.getInstance();
		bannerDAO.setConnection(conn);
		result = bannerDAO.uploadBanner(ban);
		return result;
	}

}
