package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.EditProfilePageDAO;
import vo.Member;

public class EditProfilePageService {

	public Member getProfileData(String id) {
		Member loginMember = null;
		Connection conn = null;
		try {
			EditProfilePageDAO editProfilePageDAO = EditProfilePageDAO.getInstance();
			conn = getConnection();
			editProfilePageDAO.setConnection(conn);
			loginMember = editProfilePageDAO.selectLoginMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return loginMember;
	}

}
