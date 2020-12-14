package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeUpdatePageService;
import vo.ActionForward;
import vo.Member;
import vo.Notice;

public class NoticeUpdatePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Notice not = null;
		
		if(loginMember != null) {
			forward = new ActionForward();
			int notice_num = (int) session.getAttribute("notice_num");
			
			NoticeUpdatePageService noticeUpdatePageService = new NoticeUpdatePageService();
			not = noticeUpdatePageService.updateNotice(notice_num);
			session.setAttribute("not", not);
			if (not != null) {
				forward.setPath("noticeUpdatead.jsp");
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
