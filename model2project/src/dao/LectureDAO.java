package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Lecture;
import vo.Member;
import vo.Subject;

public class LectureDAO {
	private static LectureDAO lectureDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 8;
	int range = 5;
	
	private LectureDAO() {
		
	}

	public static LectureDAO getInstance() {
		if(lectureDAO == null) {
			lectureDAO = new LectureDAO();
		}
		return lectureDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public LinkedList[] selectLectureList(int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, l.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					sub = new Subject();
					
					lec.setLecture_num(rs.getInt("lecture_num"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					lec.setVideo(rs.getString("video"));
					mem.setName(rs.getString("name"));
					sub.setSubject_name(rs.getString("subject_name"));
					lecList.add(lec);
					memList.add(mem);
					subList.add(sub);
				} while(rs.next());
			}
			lectureList = new LinkedList[] {lecList, memList, subList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public int getLectureNumber() {
		String sql = "SELECT count(*) AS c FROM lecture";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % 5 != 0) {
					result = (result / 5) + 1;
				} else {
					result = result / 5;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public LinkedList<Subject> getSubjectList() {
		String sql = "SELECT * FROM subject ORDER BY code DESC";
		LinkedList<Subject> subjectList = new LinkedList<>();
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					sub = new Subject();
					sub.setCode(rs.getInt("code"));
					sub.setSubject_name(rs.getString("subject_name"));
					subjectList.add(sub);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return subjectList;
	}
}
