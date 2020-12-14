package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MainBannerService;
import svc.MainFreeService;
import svc.MainNoticeService;
import vo.ActionForward;
import vo.Banner;
import vo.Free;
import vo.Notice;

public class MainadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LinkedList<Notice> notList = null;
		LinkedList<Free> freeList = null;
		LinkedList<Banner> banList = null;
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		MainNoticeService mainNoticeService = new MainNoticeService();
		notList = mainNoticeService.getMainNotice();
		session.setAttribute("notList", notList);
		
		MainFreeService mainFreeService = new MainFreeService();
		freeList = mainFreeService.getMainFree();
		session.setAttribute("freeList", freeList);
		
		MainBannerService mainBannerService = new MainBannerService();
		banList = mainBannerService.getBanner();
		session.setAttribute("banList", banList);
		
//		MainLectureService mainLectureService = new MainLectureService();
		
		
		forward = new ActionForward();
		forward.setPath("mainad.jsp");
		
		return forward;
	}

}
