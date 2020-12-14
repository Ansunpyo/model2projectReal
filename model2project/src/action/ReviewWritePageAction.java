package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewWritePageService;
import vo.ActionForward;
import vo.Lecture;
import vo.Member;

public class ReviewWritePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			forward = new ActionForward();
			LinkedList<Lecture> lecList = null;
			ReviewWritePageService reviewWritePageService = new ReviewWritePageService();
			lecList = reviewWritePageService.reviewWritePage();
			
			if (lecList != null) {
				session.setAttribute("lecList", lecList);
				forward.setPath("reviewWrite.jsp");
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
