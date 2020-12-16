package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PurchaseRefundService;
import vo.ActionForward;
import vo.Member;

public class PurchaseRefundAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int nowPage = 1;
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			int pay_number = Integer.parseInt(request.getParameter("pay_number"));
			forward = new ActionForward();
			
			PurchaseRefundService purchaseRefundService = new PurchaseRefundService();
			int result = purchaseRefundService.getRefund(pay_number);
			
			if(result > 0) {
				forward.setPath("purchaseList.do?page="+nowPage);
				return forward;
			} else {
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
