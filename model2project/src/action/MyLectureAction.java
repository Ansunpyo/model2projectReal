package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureLastPageService;
import svc.LectureListService;
import svc.SubjectListService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class MyLectureAction implements Action {

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
			LinkedList[] lectureList = lectureListService.getLecList(id, nowPage);
			
			LectureLastPageService lectureLastPageService = new LectureLastPageService();
			int lastPage = lectureLastPageService.getLectureLastPage(id);
			
			session.setAttribute("subjectList", subjectList);
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("lectureList", lectureList);
			forward.setPath("lectureList.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
