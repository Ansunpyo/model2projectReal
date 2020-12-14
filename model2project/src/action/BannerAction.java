package action;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BannerListService;
import vo.ActionForward;
import vo.Banner;
import vo.Member;

public class BannerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember != null) {
			forward = new ActionForward();
			BannerListService bannerListService = new BannerListService();
			LinkedList<Banner> banList = bannerListService.getBannerList();
			session.setAttribute("banList", banList);
			forward.setPath("bannerList.jsp");
			return forward;
		} else {
			forward = new ActionForward();
			forward.setPath("loginPage.do");
			return forward;
		}
	}

}
