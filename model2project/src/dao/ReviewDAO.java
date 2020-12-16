package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.rollback;
import static db.JdbcUtil.commit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Lecture;
import vo.Member;
import vo.Review;

public class ReviewDAO {
	private static ReviewDAO reviewDAO;
	private Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	int pageCount = 5;
	int range = 5;
	
	private ReviewDAO() {
		
	}

	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getReviewNumber() {
		String sql = "SELECT count(*) AS c FROM review";
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

	public LinkedList[] selectReviewList(int nowPageNumber) {
		String sql = "SELECT r.title, r.contents, r.review_num, l.lecture_title, l.lecture_num, m.name FROM review AS r JOIN member AS m on r.number = m.number JOIN lecture AS l ON r.lecture_num = l.lecture_num ORDER BY r.review_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Review> reviewContentList = new LinkedList<Review>();
		LinkedList[] reviewList = null;
		Lecture lec = null;
		Member mem = null;
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPageNumber - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					re = new Review();
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setLecture_num(rs.getInt("lecture_num"));
					mem.setName(rs.getString("name"));
					re.setTitle(rs.getString("title"));
					re.setContents(rs.getString("contents"));
					re.setReview_num(rs.getInt("review_num"));
					lecList.add(lec);
					memList.add(mem);
					reviewContentList.add(re);
				} while(rs.next());
			}
			reviewList = new LinkedList[] {lecList, memList, reviewContentList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}

	public LinkedList<Object> selectReviewView(int review_num) {
		String sql = "SELECT r.review_num, r.title, r.contents, m.name FROM review AS r JOIN member AS m ON r.number = m.number WHERE r.review_num = ?";
		LinkedList<Object> reviewViewList = null;
		Member mem = null;
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem = new Member();
				re = new Review();
				mem.setName(rs.getString("name"));
				re.setReview_num(rs.getInt("review_num"));
				re.setTitle(rs.getString("title"));
				re.setContents(rs.getString("contents"));
			}
			reviewViewList = new LinkedList<Object>();
			reviewViewList.add(re);
			reviewViewList.add(mem);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewViewList;
	}

	public int deleteReview(int review_num) {
		String sql = "DELETE FROM review WHERE review_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Review updateReviewPage(int review_num) {
		String sql = "SELECT * FROM review WHERE review_num = ?";
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				re = new Review();
				re.setTitle(rs.getString("title"));
				re.setContents(rs.getString("contents"));
				re.setReview_num(rs.getInt("review_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return re;
	}

	public int updateReview(Review re) {
		String sql = "UPDATE review SET title = ?, contents = ? WHERE review_num = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, re.getTitle());
			pstmt.setString(2, re.getContents());
			pstmt.setInt(3, re.getReview_num());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public LinkedList<Lecture> selectLectureList() {
		String sql = "SELECT lecture_title, lecture_num FROM Lecture order by lecture_num desc";
		Lecture lec = null;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setLecture_num(rs.getInt("lecture_num"));
					lecList.add(lec);
				} while(rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return lecList;
	}

	public int writeReview(String id, Review re) {
		String sql = "INSERT INTO review VALUES ((SELECT number FROM member WHERE id = ?), ?, ?, ?, NULL)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, re.getLecture_num());
			pstmt.setString(3, re.getContents());
			pstmt.setString(4, re.getTitle());
			result = pstmt.executeUpdate();
			if (result <= 0) {
				rollback(conn);
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public LinkedList[] selectMyReviewList(String id, int nowPage) {
		String sql = "SELECT r.title, r.contents, r.review_num, l.lecture_title, l.lecture_num, m.name FROM review AS r JOIN member AS m on r.number = m.number JOIN lecture AS l ON r.lecture_num = l.lecture_num WHERE r.number = (SELECT number FROM member WHERE id = ?) ORDER BY r.review_num DESC LIMIT ?, " + pageCount;
		LinkedList<Lecture> lecList = new LinkedList<Lecture>();
		LinkedList<Member> memList = new LinkedList<Member>();
		LinkedList<Review> reviewContentList = new LinkedList<Review>();
		LinkedList[] reviewList = null;
		Lecture lec = null;
		Member mem = null;
		Review re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (nowPage - 1) * pageCount);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					lec = new Lecture();
					mem = new Member();
					re = new Review();
					lec.setLecture_title(rs.getString("lecture_title"));
					lec.setLecture_num(rs.getInt("lecture_num"));
					mem.setName(rs.getString("name"));
					re.setTitle(rs.getString("title"));
					re.setContents(rs.getString("contents"));
					re.setReview_num(rs.getInt("review_num"));
					lecList.add(lec);
					memList.add(mem);
					reviewContentList.add(re);
				} while(rs.next());
			}
			reviewList = new LinkedList[] {lecList, memList, reviewContentList};
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;
	}

	public int getMyReviewNumber(String id) {
		String sql = "SELECT count(*) AS c FROM review WHERE review.number = (SELECT number FROM member WHERE id = ?)";
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
	}
}
