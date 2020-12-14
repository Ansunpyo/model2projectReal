package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FaqDeleteService;
import vo.ActionForward;
import vo.Member;

public class FaqDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			int faqId = Integer.parseInt(request.getParameter("faqId"));
			forward = new ActionForward();
			FaqDeleteService faqDeleteService = new FaqDeleteService();
			result = faqDeleteService.deleteFaq(faqId);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("faq.do");
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
