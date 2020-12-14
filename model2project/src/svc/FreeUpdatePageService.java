package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;
import vo.Free;

public class FreeUpdatePageService {

	public Free updateFree(int free_num) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		Free fr = freeDAO.updateFreePage(free_num);
		close(conn);
		
		return fr;
	}

}
