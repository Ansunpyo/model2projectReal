package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Intro;
import vo.Member;

public class IntroDAO {

	DataSource ds;
	Connection conn;
	private static IntroDAO introDAO;
	
	private IntroDAO() {
		
	}
	
	public static IntroDAO getInstance() {
		if(introDAO == null) {
			introDAO = new IntroDAO();
		}
		return introDAO;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	//글의 개수 구하기
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt=conn.prepareStatement("SELECT COUNT(*) FROM intro");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return listCount;
	}
	
	//글 목록 보기
	public ArrayList<Intro> selectArticleList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String intro_list_sql = "SELECT * FROM intro ORDER BY intro_num DESC, number ASC LIMIT ?, ?";
		ArrayList<Intro> articleList = new ArrayList<Intro>();
		Intro intro = null;
		int startrow = (page - 1) * 10; //읽기 시작할 row 번호
		
		try {
			pstmt = conn.prepareStatement(intro_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				intro = new Intro();
				intro.setNumber(rs.getInt("number"));
				intro.setIntro_num(rs.getInt("intro_num"));
				intro.setContents(rs.getString("contents"));
				intro.setImg1(rs.getString("img1"));
				intro.setImg2(rs.getString("img2"));
				intro.setImg3(rs.getString("img3"));
				intro.setImg4(rs.getString("img4"));
				intro.setImg5(rs.getString("img5"));
				intro.setImg6(rs.getString("img6"));
				intro.setReadcount(rs.getInt("readcount"));
				articleList.add(intro);
			}
		} catch (Exception ex) {
			System.out.println("getintroList 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		
		return articleList;
	}
	
	//글 등록
	public int insertArticle(String id, Intro article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		int insertCount = 0;
		String sql = "SELECT number FROM member WHERE member.id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				num = rs.getInt("number");
			}
			
			
			sql = "INSERT INTO intro VALUES (?,NULL,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,article.getContents());
			pstmt.setInt(3, 0);
			pstmt.setString(4,article.getImg1());
			pstmt.setString(5,article.getImg2());
			pstmt.setString(6,article.getImg3());
			pstmt.setString(7,article.getImg4());
			pstmt.setString(8,article.getImg5());
			pstmt.setString(9,article.getImg6());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("introInsert 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		
		return insertCount;
	}
	//조회수 업데이트
	public int updateReadCount(int intro_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE intro SET readcount = readcount + 1 WHERE intro_num = " + intro_num;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		} finally {
			if(pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}
	//글 내용 보기
	public ArrayList[] selectArticle(String id, int intro_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Intro intro = null;
		Member member = null;
		ArrayList<Intro> intList = new ArrayList<Intro>();
		ArrayList<Member> memList = new ArrayList<Member>();
		ArrayList[] articleList = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT m.name, m.email, m.gender, m.major, m.education, i.intro_num, i.contents, i.readcount, i.img1, i.img2, i.img3, i.img4, i.img5, i.img6 FROM member AS m JOIN intro AS i ON m.number = i.number WHERE m.id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				intro = new Intro();
				member = new Member();
				intro.setIntro_num(rs.getInt("intro_num"));
				intro.setContents(rs.getString("contents"));
				intro.setImg1(rs.getString("img1"));
				intro.setImg2(rs.getString("img2"));
				intro.setImg3(rs.getString("img3"));
				intro.setImg4(rs.getString("img4"));
				intro.setImg5(rs.getString("img5"));
				intro.setImg6(rs.getString("img6"));
				intro.setReadcount(rs.getInt("readcount"));	//강사만 볼 수 있게 설정
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setGender(rs.getString("gender"));
				member.setMajor(rs.getString("major"));
				member.setEducation(rs.getString("education"));
				intList.add(intro);
				memList.add(member);
				articleList = new ArrayList[] {intList, memList};
			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			if(rs != null) close(rs);
			if(pstmt != null) close(pstmt);
		}
		return articleList;
	}
	//글쓴이인지 확인
	public boolean isArticleIntroWriter(int intro_num, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String intro_sql = "SELECT * FROM intro WHERE intro_num = ?";
		boolean isWriter = false;
		
		try {
			pstmt = conn.prepareStatement(intro_sql);
			pstmt.setInt(1, intro_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(password.contentEquals(rs.getString("password"))) {
				isWriter = true;
			}
		} catch (SQLException ex) {
			System.out.println("isintroWriter 에러 : " + ex);
		} finally {
			if(pstmt != null) close(pstmt);
		}
		return isWriter;
	}
	//글 수정
	public int updateArticle(Intro article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE intro SET contents = ?, img1 = ?, img2 = ?, img3 = ?, img4 = ?, img5 = ?, img6 = ?, WHERE intro_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getContents());
			pstmt.setString(2, article.getImg1());
			pstmt.setString(3, article.getImg2());
			pstmt.setString(4, article.getImg3());
			pstmt.setString(5, article.getImg4());
			pstmt.setString(6, article.getImg5());
			pstmt.setString(7, article.getImg6());
			pstmt.setInt(8, article.getIntro_num());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardModify 에러 : " + e);
		} finally {
			if(pstmt != null) close(pstmt);
		}
		return updateCount;
	}
	//글 삭제
	public int deleteArticle(int intro_num) {
		PreparedStatement pstmt = null;
		String intro_delete_sql = "DELETE FROM intro WHERE intro_num = ?";
		int deleteCount = 0;
		try {
			pstmt = conn.prepareStatement(intro_delete_sql);
			pstmt.setInt(1, intro_num);
			deleteCount = pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return deleteCount;
	}

	public Intro selectArticleForModify(int intro_num) {
		PreparedStatement pstmt = null;
		String sql = "SELECT contents, img1, img2, img3, img4, img5, img6 FROM intro WHERE intro_num = ?";
		ResultSet rs = null;
		Intro intro = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, intro_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				intro = new Intro();
				intro.setContents(rs.getString("contents"));
				intro.setImg1(rs.getString("img1"));
				intro.setImg1(rs.getString("img2"));
				intro.setImg1(rs.getString("img3"));
				intro.setImg1(rs.getString("img4"));
				intro.setImg1(rs.getString("img5"));
				intro.setImg1(rs.getString("img6"));
			}
		} catch (Exception e) {
			System.out.println("boardModify 에러 : " + e);
		} finally {
			if(pstmt != null) close(pstmt);
		}
		return intro;
	}
}

