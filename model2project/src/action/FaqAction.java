package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FaqListService;
import vo.ActionForward;
import vo.Faq;
import vo.Member;

public class FaqAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			FaqListService faqListService = new FaqListService();
			LinkedList<Faq> faqList = faqListService.getFaqList();
			session.setAttribute("faqList", faqList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("faqad.jsp");
				return forward;
			}
			forward.setPath("faq.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
