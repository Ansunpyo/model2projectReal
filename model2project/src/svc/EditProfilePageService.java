package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.EditProfilePageDAO;
import vo.Member;

public class EditProfilePageService {

	public Member getProfileData(String id) {
		EditProfilePageDAO editProfilePageDAO = EditProfilePageDAO.getInstance();
		Connection conn = getConnection();
		editProfilePageDAO.setConnection(conn);
		Member loginMember = editProfilePageDAO.selectLoginMember(id);
		close(conn);
		return loginMember;
	}

}
