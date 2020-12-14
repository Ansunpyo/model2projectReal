package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewWriteService;
import vo.ActionForward;
import vo.Member;
import vo.Review;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			Review re = new Review();
			
			re.setLecture_num(Integer.parseInt(request.getParameter("lecture_num")));
			re.setTitle(request.getParameter("title"));
			re.setContents(request.getParameter("contents"));
			
			ReviewWriteService reviewWriteService = new ReviewWriteService();
			result = reviewWriteService.writeReview(id, re);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("review.do?page=1");
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
