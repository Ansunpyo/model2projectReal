package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureMDService;
import svc.SubjectListService;
import vo.ActionForward;
import vo.Lecture;
import vo.Member;
import vo.Subject;

public class LectureMDAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			int nowPage = Integer.parseInt(request.getParameter("page"));
			
			SubjectListService subjectListService = new SubjectListService();
			LinkedList<Subject> subjectList = subjectListService.getSubjectList();
			
			LectureMDService lectureMDService = new LectureMDService();
			Lecture lec = lectureMDService.getLecForMD(lecture_num);
			
			if (lec != null) {
				session.setAttribute("lec", lec);
				session.setAttribute("subjectList", subjectList);
				forward.setPath("lectureMDPage.jsp?page=" + nowPage);
				return forward;
			} else {
				forward.setRedirect(true);
				forward.setPath("index.do");
				return forward;
			}
			
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
