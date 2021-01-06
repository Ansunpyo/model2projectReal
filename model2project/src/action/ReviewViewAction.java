package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewViewService;
import vo.ActionForward;
import vo.Member;

public class ReviewViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		if(loginMember != null) {
			forward = new ActionForward();
			ReviewViewService reviewViewService = new ReviewViewService();
			LinkedList<Object> reviewViewList = reviewViewService.getReviewView(review_num);

			LinkedList[] reviewComList = reviewViewService.getReviewCom(review_num);
			
			session.setAttribute("reviewComList", reviewComList);
			session.setAttribute("reviewViewList", reviewViewList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("reviewViewad.jsp?review_num=" + review_num + "&page=" + request.getParameter("page"));
				return forward;
			}
			forward.setPath("reviewView.jsp?review_num=" + review_num + "&page=" + request.getParameter("page"));
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
