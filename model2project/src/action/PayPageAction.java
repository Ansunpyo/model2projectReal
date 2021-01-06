package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IsAuthorService;
import svc.PaidCheckService;
import svc.PayPageService;
import vo.ActionForward;
import vo.Member;

public class PayPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		boolean paid = false;
		boolean author = false;
		if(loginMember != null) {
			forward = new ActionForward();
			String id = loginMember.getId();
			int lecture_num = Integer.parseInt(request.getParameter("lecture_num"));
			
			PaidCheckService paidCheckService = new PaidCheckService();
			paid = paidCheckService.paidCheck(id, lecture_num);
			
			IsAuthorService isAuthorService = new IsAuthorService();
			author = isAuthorService.authorCheck(id, lecture_num);
			if(paid || author) {
				forward.setPath("lectureDetail.do?lecture_num =" + lecture_num);
				return forward;
			} else {
				PayPageService payPageService = new PayPageService();
				LinkedList<Object> lectureList = payPageService.getLecList(lecture_num);
				
				session.setAttribute("lectureList", lectureList);
				forward.setPath("pay.jsp");
				return forward;
			}
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
