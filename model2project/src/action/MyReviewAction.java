package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyReviewLastPageService;
import svc.MyReviewService;
import vo.ActionForward;
import vo.Member;

public class MyReviewAction implements Action {

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
			String id = loginMember.getId();
			forward = new ActionForward();
			MyReviewService myReviewService = new MyReviewService();
			LinkedList[] reviewList = myReviewService.getMyReviewList(id, nowPage);
			MyReviewLastPageService myReviewLastPageService = new MyReviewLastPageService();
			int lastPage = myReviewLastPageService.getMyReviewLastPage(id);
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("reviewList", reviewList);
			forward.setPath("myReview.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
