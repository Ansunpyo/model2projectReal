package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PurchaseDoRefundService;
import vo.ActionForward;
import vo.Member;

public class PurchaseDoRefundAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int nowPage = 1;
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			int pay_number = Integer.parseInt(request.getParameter("pay_number"));
			forward = new ActionForward();
			
			PurchaseDoRefundService purchaseDoRefundService = new PurchaseDoRefundService();
			int result = purchaseDoRefundService.doRefund(pay_number);
			
			if(result > 0) {
				forward.setPath("purchaseRefundList.do?page="+nowPage);
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
