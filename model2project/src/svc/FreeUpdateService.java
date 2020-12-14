package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;
import vo.Free;

public class FreeUpdateService {

	public int updateFree(Free fr) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int result = freeDAO.updateFree(fr);
		close(conn);
		
		return result;
	}

}
