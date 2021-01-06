package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.EditProfileDAO;

public class EditProfileService {

	public int doEdit(String id, String pw, String name, String email, String gender, String major, String education) {
		int result = 0;
		Connection conn = null;
		try {
			EditProfileDAO editProfileDAO = EditProfileDAO.getInstance();
			conn = getConnection();
			editProfileDAO.setConnection(conn);
			result = editProfileDAO.doEditProfile(id, pw, name, email, gender, major, education);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
