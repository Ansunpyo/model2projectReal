package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeWriteService;
import vo.ActionForward;
import vo.Member;
import vo.Notice;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int result = 0;
		
		if(loginMember != null) {
			forward = new ActionForward();
			Notice not = new Notice();
			
			not.setTitle(request.getParameter("title"));
			not.setContents(request.getParameter("contents").replace("\n", "<br/>"));
			
			NoticeWriteService noticeWriteService = new NoticeWriteService();
			result = noticeWriteService.writeNotice(not);
			
			if (result > 0) {
				forward.setRedirect(true);
				forward.setPath("notice.do?page=1");
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
