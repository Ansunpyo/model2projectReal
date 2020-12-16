package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PurchaseAllLastPageService;
import svc.PurchaseAllListService;
import vo.ActionForward;
import vo.Member;
import vo.Pay;

public class PurchaseAllListAction implements Action {

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
			forward = new ActionForward();
			PurchaseAllListService purchaseAllListService = new PurchaseAllListService();
			LinkedList[] payList = purchaseAllListService.getPurchaseList(nowPage);
			
			PurchaseAllLastPageService purchaseAllLastPageService = new PurchaseAllLastPageService();
			int lastPage = purchaseAllLastPageService.getPurchaseLastPage();
			
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("payList", payList);
			forward.setPath("purchaseAllList.jsp?page="+nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
