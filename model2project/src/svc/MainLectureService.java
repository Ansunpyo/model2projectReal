package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Lecture_Video;

public class MainLectureService {

	public LinkedList<Lecture_Video> getLectureThumbnail() {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList<Lecture_Video> lecList = lectureDAO.getLectureThumbnail();
		close(conn);
		
		return lecList;
	}

}
