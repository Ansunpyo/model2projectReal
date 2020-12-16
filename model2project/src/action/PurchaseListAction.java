package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MyPurchaseLastPageService;
import svc.MyPurchaseListService;
import vo.ActionForward;
import vo.Member;
import vo.Pay;

public class PurchaseListAction implements Action {

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
			MyPurchaseListService myPurchaseListService = new MyPurchaseListService();
			LinkedList[] payList = myPurchaseListService.getMyPurchaseList(id, nowPage);
			
			MyPurchaseLastPageService myPurchaseLastPageService = new MyPurchaseLastPageService();
			int lastPage = myPurchaseLastPageService.getMyPurchaseLastPage(id);
			
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("payList", payList);
			forward.setPath("myPurchase.jsp?page="+nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
