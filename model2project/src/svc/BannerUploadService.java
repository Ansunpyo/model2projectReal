package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BannerDAO;
import vo.Banner;

public class BannerUploadService {

	public int uploadBanner(Banner ban) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			BannerDAO bannerDAO = BannerDAO.getInstance();
			bannerDAO.setConnection(conn);
			result = bannerDAO.uploadBanner(ban);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
