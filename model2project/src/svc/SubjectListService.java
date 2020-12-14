package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Subject;

public class SubjectListService {

	public LinkedList<Subject> getSubjectList() {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList<Subject> subjectList = lectureDAO.getSubjectList();
		close(conn);
		
		return subjectList;
	}

}
