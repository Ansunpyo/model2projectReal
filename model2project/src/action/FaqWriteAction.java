package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FaqWriteService;
import vo.ActionForward;
import vo.Faq;
import vo.Member;

public class FaqWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			Faq faq = new Faq();
			
			faq.setQuestion(request.getParameter("question"));
			faq.setAnswer(request.getParameter("answer").replace("\n", "<br/>"));
			
			FaqWriteService faqWriteService = new FaqWriteService();
			result = faqWriteService.writeFaq(faq);
			
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
