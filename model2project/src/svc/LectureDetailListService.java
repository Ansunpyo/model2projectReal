package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Lecture_Video;

public class LectureDetailListService {

	public LinkedList<Lecture_Video> getVid(int lecture_num) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList<Lecture_Video> vidList = lectureDAO.getVid(lecture_num);
		close(conn);
		
		return vidList;
	}
}

