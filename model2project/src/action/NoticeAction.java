package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.NoticeLastPageService;
import svc.NoticeService;
import vo.ActionForward;
import vo.Member;
import vo.Notice;

public class NoticeAction implements Action {

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
			NoticeService noticeService = new NoticeService();
			LinkedList<Notice> noticeList = noticeService.getNoticeList(nowPage);
			NoticeLastPageService noticeLastPageService = new NoticeLastPageService();
			int lastPage = noticeLastPageService.getNoticeLastPage();
			session.setAttribute("lastPage", lastPage);
			session.setAttribute("noticeList", noticeList);
			if(loginMember.getId().equals("admin")) {
				forward.setPath("noticead.jsp?page="+nowPage);
				return forward;
			}
			forward.setPath("notice.jsp?page=" + nowPage);
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
