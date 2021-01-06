package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureUploadPageService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class LectureUploadPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			LinkedList<Subject> subList = null;
			LectureUploadPageService lectureUploadPageService = new LectureUploadPageService();
			subList = lectureUploadPageService.getSubList();
			
			if (subList != null) {
				session.setAttribute("subList", subList);
				forward.setPath("lectureUploadPage.jsp");
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
