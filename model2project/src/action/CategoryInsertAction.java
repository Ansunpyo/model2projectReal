package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CategoryWriteService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class CategoryInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			Subject sub = new Subject();
			
			sub.setSubject_name(request.getParameter("subject_name"));
			
			CategoryWriteService categoryWriteService = new CategoryWriteService();
			result = categoryWriteService.insertSubject(sub);
			
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
