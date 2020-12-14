package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CategoryListService;
import vo.ActionForward;
import vo.Member;
import vo.Subject;

public class CategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			CategoryListService categoryListService = new CategoryListService();
			LinkedList<Subject> subList = categoryListService.getSubjectList();
			session.setAttribute("subList", subList);
			forward.setPath("category.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
