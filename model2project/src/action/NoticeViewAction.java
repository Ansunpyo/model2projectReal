package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeViewService;
import vo.ActionForward;
import vo.Member;
import vo.Notice;

public class NoticeViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		if(loginMember != null) {
			forward = new ActionForward();
			NoticeViewService noticeViewService = new NoticeViewService();
			Notice not = noticeViewService.getNoticeView(notice_num);

			session.setAttribute("not", not);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("noticeViewad.jsp?page=" + request.getParameter("page"));
				return forward;
			}
			forward.setPath("noticeView.jsp?page=" + request.getParameter("page"));
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
