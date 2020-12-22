package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FavoritesListService;
import svc.LectureLastPageService;
import svc.LectureListService;
import svc.SubjectListService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class LectureListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int nowPage = 1;
		if(request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			
			SubjectListService subjectListService = new SubjectListService();
			LinkedList<Subject> subjectList = subjectListService.getSubjectList();
			
			LectureListService lectureListService = new LectureListService();
			LinkedList[] lectureList = lectureListService.getLecList(nowPage);
			
			LectureLastPageService lectureLastPageService = new LectureLastPageService();
			int lastPage = lectureLastPageService.getLectureLastPage();
			
			FavoritesListService favoritesListService = new FavoritesListService();
			LinkedList<Integer> favList = favoritesListService.getFavoritesList(id, lectureList);
			
			session.setAttribute("subjectList", subjectList);
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("lectureList", lectureList);
			session.setAttribute("favList", favList);
			forward.setPath("lectureList.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
