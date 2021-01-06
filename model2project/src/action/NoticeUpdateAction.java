package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeUpdateService;
import vo.ActionForward;
import vo.Member;
import vo.Notice;

public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		Notice not = new Notice();
		
		if(loginMember != null) {
			forward = new ActionForward();
			not.setNotice_num(Integer.parseInt(request.getParameter("notice_num")));
			not.setTitle(request.getParameter("title"));
			not.setContents(request.getParameter("contents").replace("\n", "<br/>"));
			
			NoticeUpdateService noticeUpdateService = new NoticeUpdateService();
			int result = noticeUpdateService.updateNotice(not);
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("notice.do");
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
