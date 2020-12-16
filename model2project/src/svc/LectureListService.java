package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;

public class LectureListService {

	public LinkedList[] getLecList(int nowPage) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList[] lectureList = lectureDAO.selectLectureList(nowPage);
		close(conn);
		
		return lectureList;
	}

	public LinkedList[] getLecList(String[] subject, int nowPage) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList[] lectureList = lectureDAO.selectLectureList(subject, nowPage);
		close(conn);
		
		return lectureList;
	}

	public LinkedList[] getLecList(String id, int nowPage) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList[] lectureList = lectureDAO.selectLectureList(id, nowPage);
		close(conn);
		
		return lectureList;
	}

}
