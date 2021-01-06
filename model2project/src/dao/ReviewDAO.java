package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import vo.Lecture;
import vo.Member;
import vo.Review;
import vo.Review_Comment;

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
		String sql = "SELECT r.title, r.contents, r.review_num, l.lecture_title, l.lecture_num, m.id, m.name, q.id AS qid, q.name AS qname FROM member AS m RIGHT JOIN review AS r ON r.number = m.number LEFT JOIN quitter AS q ON q.number = r.number JOIN lecture AS l ON r.lecture_num = l.lecture_num ORDER BY r.review_num DESC LIMIT ?, " + pageCount;
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
					if(rs.getString("id") != null) {
						mem.setId(rs.getString("id"));
					} else {
						mem.setId(rs.getString("qid"));
					}
					if(rs.getString("name") != null) {
						mem.setName(rs.getString("name"));
					} else {
						mem.setName(rs.getString("qname"));
					}
					re.setTitle(rs.getString("title"));
					re.setContents(rs.getString("contents").replace("<br/>", " "));
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
		String sql = "SELECT r.review_num, r.title, r.contents, m.id, m.name, q.id AS qid, q.name AS qname FROM member AS m RIGHT JOIN review AS r ON r.number = m.number LEFT JOIN quitter AS q ON q.number = r.number WHERE r.review_num = ?";
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
				if(rs.getString("id") != null) {
					mem.setId(rs.getString("id"));
				} else {
					mem.setId(rs.getString("qid"));
				}
				if(rs.getString("name") != null) {
					mem.setName(rs.getString("name"));
				} else {
					mem.setName(rs.getString("qname"));
				}
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
		boolean hasReply = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			result = pstmt.executeUpdate();
			if (result > 0) {
				sql = "SELECT comment_num FROM review_comment WHERE review_num = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, review_num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						hasReply = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(hasReply) {
					sql = "DELETE FROM review_comment WHERE review_num = ?";
					result = 0;
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, review_num);
						result = pstmt.executeUpdate();
						if(result > 0) {
							commit(conn);
						} else {
							rollback(conn);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					commit(conn);
				}
			} else {
				rollback(conn);
			}
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
				re.setContents(rs.getString("contents").replace("<br/>", "\n"));
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
		String sql = "SELECT lecture_title, lecture_num FROM lecture order by lecture_num desc";
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
		String sql = "SELECT r.title, r.contents, r.review_num, l.lecture_title, l.lecture_num, m.id, m.name FROM review AS r JOIN member AS m on r.number = m.number JOIN lecture AS l ON r.lecture_num = l.lecture_num WHERE r.number = (SELECT number FROM member WHERE id = ?) ORDER BY r.review_num DESC LIMIT ?, " + pageCount;
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
					mem.setId(rs.getString("id"));
					mem.setName(rs.getString("name"));
					re.setTitle(rs.getString("title"));
					re.setContents(rs.getString("contents").replace("<br/>", "\n"));
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

	public int exComment(Review_Comment rc, int number, int parent, int review_num) {
		String sql = "INSERT INTO review_comment VALUES(?, ?, ?, NULL, NOW(), ?, (SELECT * FROM (SELECT step FROM review_comment WHERE comment_num = ?) AS x) + 1)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			pstmt.setInt(2, number);
			pstmt.setString(3, rc.getContents());
			pstmt.setInt(4, parent);
			pstmt.setInt(5, parent);
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
		}
		return result;
	}

	public int delComment(int review_num, int comment_num) {
		String sql = "SELECT review_num FROM review_comment WHERE review_num = ? AND parent = ?";
		int result = 0;
		boolean hasReply = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			pstmt.setInt(2, comment_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hasReply = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(hasReply) {
			sql = "UPDATE review_comment SET contents = '<삭제됨>' WHERE review_num = ? AND comment_num = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, review_num);
				pstmt.setInt(2, comment_num);
				result = pstmt.executeUpdate();
				if (result > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			sql = "DELETE FROM review_comment WHERE review_num = ? AND comment_num = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, review_num);
				pstmt.setInt(2, comment_num);
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
			}
		}
		return result;
	}

	public int writeComment(int review_num, int number, String contents) {
		String sql = "INSERT INTO review_comment VALUES (?, ?, ?, NULL, NOW(), 0, 0)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			pstmt.setInt(2, number);
			pstmt.setString(3, contents);
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
		}
		return result;
	}

	public LinkedList[] selectReviewCom(int review_num) {
		String sql = "SELECT m.number, m.id, m.name, rc.contents, rc.comment_num, rc.time, rc.parent, rc.step FROM review_comment AS rc LEFT JOIN member AS m ON rc.number = m.number WHERE rc.review_num = ? ORDER BY comment_num";
		LinkedList[] reviewComList = null;
		LinkedList<Member> memList = new LinkedList<>();
		LinkedList<Review_Comment> rCList = new LinkedList<>();
		Member mem = null;
		Review_Comment rc = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					mem = new Member();
					rc = new Review_Comment();
					mem.setNumber(rs.getInt("number"));
					if(rs.getString("id") == null) {
						mem.setId("");
					} else {
						mem.setId(rs.getString("id"));
					}
					if(rs.getString("name") == null) {
						mem.setName("<탈퇴한 회원>");
					} else {
						mem.setName(rs.getString("name"));
					}
					rc.setContents(rs.getString("contents"));
					rc.setComment_num(rs.getInt("comment_num"));
					rc.setTime(rs.getString("time"));
					rc.setParent(rs.getInt("parent"));
					rc.setStep(rs.getInt("step"));
					memList.add(mem);
					rCList.add(rc);
				} while (rs.next());
				reviewComList = new LinkedList[]{memList, rCList};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return reviewComList;
	}
}
