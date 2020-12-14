package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CategoryDeleteService;
import vo.ActionForward;
import vo.Member;

public class CategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			int code = Integer.parseInt(request.getParameter("code"));
			forward = new ActionForward();
			CategoryDeleteService categoryDeleteService = new CategoryDeleteService();
			result = categoryDeleteService.deleteSubject(code);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("category.do");
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
