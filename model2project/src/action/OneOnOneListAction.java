package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.OneOnOneListService;
import vo.ActionForward;
import vo.Member;
import vo.One_On_One;

public class OneOnOneListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			String id = loginMember.getId();
			forward = new ActionForward();
			OneOnOneListService oneOnOneListService = new OneOnOneListService();
			LinkedList<One_On_One> oneOnOneList = oneOnOneListService.getOneOnOneList(id);
			session.setAttribute("oneOnOneList", oneOnOneList);
			forward.setPath("one_on_one.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
