package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;
import vo.Free;

public class FreeUpdateService {

	public int updateFree(Free fr) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			result = freeDAO.updateFree(fr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
