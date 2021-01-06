package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;

public class LectureListService {

	public LinkedList[] getLecList(int nowPage) {
		LinkedList[] lectureList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lectureList = lectureDAO.selectLectureList(nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lectureList;
	}

	public LinkedList[] getLecList(String[] subject, int nowPage) {
		LinkedList[] lectureList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lectureList = lectureDAO.selectLectureList(subject, nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lectureList;
	}

	public LinkedList[] getLecList(String id, int nowPage) {
		LinkedList[] lectureList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lectureList = lectureDAO.selectLectureList(id, nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lectureList;
	}

}
