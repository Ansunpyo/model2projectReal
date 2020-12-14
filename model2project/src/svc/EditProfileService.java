package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.EditProfileDAO;

public class EditProfileService {

	public int doEdit(String id, String pw, String name, String email, String gender, String major, String education) {
		EditProfileDAO editProfileDAO = EditProfileDAO.getInstance();
		Connection conn = getConnection();
		editProfileDAO.setConnection(conn);
		int result = editProfileDAO.doEditProfile(id, pw, name, email, gender, major, education);
		close(conn);
		return result;
	}

}
