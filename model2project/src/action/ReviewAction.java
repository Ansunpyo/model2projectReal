package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewLastPageService;
import svc.ReviewService;
import vo.ActionForward;
import vo.Member;

public class ReviewAction implements Action {

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
			ReviewService reviewService = new ReviewService();
			LinkedList[] reviewList = reviewService.getReviewList(nowPage);
			ReviewLastPageService reviewLastPageService = new ReviewLastPageService();
			int lastPage = reviewLastPageService.getReviewLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("reviewList", reviewList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("reviewad.jsp?page="+nowPage);
				return forward;
			}
			forward.setPath("review.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
