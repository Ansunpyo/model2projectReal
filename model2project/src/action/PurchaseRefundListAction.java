package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PurchaseRefundLastPageService;
import svc.PurchaseRefundListService;
import vo.ActionForward;
import vo.Member;
import vo.Pay;

public class PurchaseRefundListAction implements Action {

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
			PurchaseRefundListService purchaseRefundListService = new PurchaseRefundListService();
			LinkedList[] payList = purchaseRefundListService.getRefundList(nowPage);
			
			PurchaseRefundLastPageService purchaseRefundLastPageService = new PurchaseRefundLastPageService();
			int lastPage = purchaseRefundLastPageService.getRefundLastPage();
			
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("payList", payList);
			forward.setPath("purchaseRefundList.jsp?page="+nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
