package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

import vo.Intro;
import vo.Lecture;
import vo.Lecture_Video;
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
		String sql = "SELECT l.lecture_num, m.number, m.name, v.chapter, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num GROUP BY l.lecture_num ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					sub = new Subject();
					vid = new Lecture_Video();
					lec.setLecture_num(rs.getInt("lecture_num"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					mem.setName(rs.getString("name"));
					mem.setNumber(rs.getInt("number"));
					sub.setSubject_name(rs.getString("subject_name"));
					vid.setVideo(rs.getString("video"));
					lecList.add(lec);
					memList.add(mem);
					subList.add(sub);
					vidList.add(vid);
				} while(rs.next());
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
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
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
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
		String sql = "SELECT * FROM subject ORDER BY code";
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

	public LinkedList[] selectLectureList(String[] subject, int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num WHERE v.chapter = 1 AND s.code = ? ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for(String s:subject) {
				pstmt.setString(1, s);
				pstmt.setInt(2, (nowPage - 1) * pageCount);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						lec = new Lecture();
						mem = new Member();
						sub = new Subject();
						vid = new Lecture_Video();
						
						lec.setLecture_num(rs.getInt("lecture_num"));
						lec.setLecture_title(rs.getString("lecture_title"));
						lec.setPrice(rs.getInt("price"));
						mem.setName(rs.getString("name"));
						sub.setSubject_name(rs.getString("subject_name"));
						vid.setVideo(rs.getString("video"));
						lecList.add(lec);
						memList.add(mem);
						subList.add(sub);
						vidList.add(vid);
					} while(rs.next());
				
				}
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public LinkedList[] selectLectureList(String id, int nowPage) {
		String sql = "SELECT l.lecture_num, m.name, l.lecture_title, l.price, s.subject_name, v.video FROM member AS m JOIN lecture AS l ON m.number = l.number JOIN subject AS s ON l.subject_code = s.code JOIN lecture_video AS v ON l.lecture_num = v.lecture_num WHERE v.chapter = 1 AND l.number = (SELECT number FROM member WHERE id = ?) ORDER BY lecture_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Subject> subList = new LinkedList<Subject>();
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList[] lectureList = null;
		Lecture lec = null;
		Member mem = null;
		Subject sub = null;
		Lecture_Video vid = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					sub = new Subject();
					vid = new Lecture_Video();
					
					lec.setLecture_num(rs.getInt("lecture_num"));
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setPrice(rs.getInt("price"));
					mem.setName(rs.getString("name"));
					sub.setSubject_name(rs.getString("subject_name"));
					vid.setVideo(rs.getString("video"));
					lecList.add(lec);
					memList.add(mem);
					subList.add(sub);
					vidList.add(vid);
				} while(rs.next());
			}
			lectureList = new LinkedList[] {lecList, memList, subList, vidList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lectureList;
	}

	public int getLectureNumber(String id) {
		String sql = "SELECT count(*) AS c FROM lecture AS l JOIN member AS m ON l.number = m.number WHERE m.id = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("c");
				if (result % pageCount != 0) {
					result = (result / pageCount) + 1;
				} else {
					result = result / pageCount;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			close(pstmt);
		}
		return result;
//칸수 맞추기용		
		//칸수 맞추기용		
		//칸수 맞추기용		
		//칸수 맞추기용		
		//칸수 맞추기용		
		//칸수 맞추기용		
	}

	public LinkedList<Subject> selectSubList() {
		String sql = "SELECT * FROM subject ORDER BY code";
		LinkedList<Subject> subList = new LinkedList<>();
		Subject sub = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					sub = new Subject();
					sub.setCode(rs.getInt("code"));
					sub.setSubject_name(rs.getString("subject_name"));
					subList.add(sub);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return subList;
	}

	public int lectureUpload(String id, Lecture lec, Lecture_Video vid) {
		String sql = "INSERT INTO lecture VALUES ((SELECT number FROM member WHERE id = ?), NULL, ?, ?, ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, lec.getLecture_title());
			pstmt.setInt(3, lec.getSubject_code());
			pstmt.setInt(4, lec.getPrice());
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				sql = "INSERT INTO lecture_video VALUES ((SELECT lecture_num FROM lecture WHERE lecture_title = ?), 1, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, lec.getLecture_title());
				pstmt.setString(2, vid.getVideo());
				pstmt.setString(3, vid.getChapter_title());
				result = pstmt.executeUpdate();
				if(result <= 0) {
					rollback(conn);
				}
			} else {
				rollback(conn);
			}
			
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public LinkedList<Lecture_Video> getLectureThumbnail() {
		String sql = "SELECT v.lecture_num, v.video FROM lecture_video AS v JOIN lecture AS l ON v.lecture_num = l.lecture_num WHERE v.chapter = 1 AND l.price = 0 ORDER BY v.lecture_num DESC LIMIT 0, 8";
		LinkedList<Lecture_Video> vidList = new LinkedList<Lecture_Video>();
		Lecture_Video vid = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					vid = new Lecture_Video();
					vid.setVideo(rs.getString("video"));
					vid.setLecture_num(rs.getInt("lecture_num"));
					vidList.add(vid);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vidList;
	}

	public Lecture getLecForMD(int lecture_num) {
		String sql = "SELECT * FROM lecture WHERE lecture_num = ?";
		Lecture lec = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lec = new Lecture();
				lec.setLecture_num(rs.getInt("lecture_num"));
				lec.setLecture_title(rs.getString("lecture_title"));
				lec.setPrice(rs.getInt("price"));
				lec.setSubject_code(rs.getInt("subject_code"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return lec;
	}

	public int lectureModify(Lecture lec) {
		String sql = "UPDATE lecture SET lecture_title = ?, price = ?, subject_code = ? WHERE lecture_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lec.getLecture_title());
			pstmt.setInt(2, lec.getPrice());
			pstmt.setInt(3, lec.getSubject_code());
			pstmt.setInt(4, lec.getLecture_num());
			result = pstmt.executeUpdate();
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return result;
	}

	public int deleteLecture(int lecture_num) {
		String sql = "DELETE FROM lecture WHERE lecture_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			result = pstmt.executeUpdate();
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public LinkedList<Object> lecture4Pay(int lecture_num) {
		String sql = "SELECT l.lecture_num, l.lecture_title, m.name, q.name AS qname, l.price FROM lecture AS l LEFT JOIN member AS m ON l.number = m.number LEFT JOIN quitter AS q ON l.number = q.number WHERE lecture_num = ?";
		LinkedList<Object> lectureList = null;
		Lecture lec = null;
		Member mem = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lec = new Lecture();
				mem = new Member();
				lec.setLecture_num(rs.getInt("lecture_num"));
				lec.setLecture_title(rs.getString("lecture_title"));
				lec.setPrice(rs.getInt("price"));
				if(rs.getString("name") != null) {
					mem.setName(rs.getString("name"));
				} else {
					mem.setName(rs.getString("qname"));
				}
				lectureList = new LinkedList<Object>(Arrays.asList(lec, mem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return lectureList;
	}

	public boolean paidCheck(String id, int lecture_num) {
		String sql = "SELECT * FROM pay WHERE lecture_num = ? AND number = (SELECT number FROM member WHERE id = ?)";
		boolean paid = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				paid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return paid;
	}

	public int doPay(String id, int lecture_num, String type, String pay_code) {
		String sql = "INSERT INTO pay VALUES (?, (SELECT number FROM member WHERE id = ?), ?, ?, 0, null, now());";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			pstmt.setString(2, id);
			pstmt.setString(3, type);
			if(pay_code.length() == 0) {
				pstmt.setString(4, "<계좌이체>");
			} else {
				pstmt.setString(4, pay_code);
			}
			result = pstmt.executeUpdate();
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
		
	public LinkedList[] getVid(int lecture_num) {
		String sql = "SELECT v.chapter, v.lecture_num, v.video, v.chapter_title, l.number FROM lecture_video AS v JOIN lecture AS l ON v.lecture_num = l.lecture_num WHERE v.lecture_num = ? ORDER BY chapter";
		LinkedList[] lvList = null;
		LinkedList<Lecture_Video> vidList = new LinkedList<>();
		LinkedList<Lecture> lecList = new LinkedList<>();
		Lecture_Video vid = null;
		Lecture lec = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					vid = new Lecture_Video();
					lec = new Lecture();
					vid.setChapter(rs.getInt("chapter"));
					vid.setLecture_num(rs.getInt("lecture_num"));
					vid.setVideo(rs.getString("video"));
					vid.setChapter_title(rs.getString("chapter_title"));
					lec.setNumber(rs.getInt("number"));
					vidList.add(vid);
					lecList.add(lec);
				} while (rs.next());
				lvList = new LinkedList[] {vidList, lecList};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return lvList;
	}

	public int lectureDetailUpload(String id, Lecture lec, Lecture_Video vid) {
		int result = 0;
		try {
			String sql = "INSERT INTO lecture_video VALUES (?, (SELECT chapter FROM (SELECT chapter FROM lecture_video WHERE lecture_num = ? ORDER BY chapter DESC LIMIT 0, 1) AS vid) + 1, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lec.getLecture_num());
			pstmt.setInt(2, lec.getLecture_num());
			pstmt.setString(3, vid.getVideo());
			pstmt.setString(4, vid.getChapter_title());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}

			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteArticle(int chapter, int lecture_num) {
		int deleteCount = 0;
		String Lecture_delete_sql = "DELETE FROM lecture_video WHERE chapter = ? and lecture_num=?";
		try {
			pstmt = conn.prepareStatement(Lecture_delete_sql);
			pstmt.setInt(1, chapter);
			pstmt.setInt(2, lecture_num);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("lectureDelete 에러 : " + ex);
		} finally {
			if (pstmt != null)
				close(pstmt);
		}
		return deleteCount;
	}

	public boolean isFree(int lecture_num) {
		String sql = "SELECT price FROM lecture WHERE lecture_num = ?";
		boolean free = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt("price") == 0) {
					free = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return free;
	}

	public boolean isAuthor(String id, int lecture_num) {
		String sql = "SELECT number FROM lecture WHERE lecture_num = ?";
		boolean author = false;
		int lecAuthor = 0;
		int memNumber = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lecAuthor = rs.getInt("number");
			}
			sql = "SELECT number FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memNumber = rs.getInt("number");
			}
			if (lecAuthor == memNumber) {
				author = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return author;
	}
}
