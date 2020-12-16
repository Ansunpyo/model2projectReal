package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.IntroListService;
import vo.ActionForward;
import vo.PageInfo;
public class IntroListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList[] articleList = null;
		int page=1;
		int limit=12;
		HttpSession session = request.getSession();
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		IntroListService introListService = new IntroListService();
		int listCount=introListService.getListCount();
		//총 리스트 수를 받아옴
		articleList=introListService.getArticleList(page, limit);
		//리스트 받아옴
		//총 페이지 수
		int maxPage = (int)((double)listCount/limit+0.95);
		//0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등)
		int startPage=(((int)((double)page/10+0.9))-1) * 10 +1;
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등)
		int endPage = startPage+10-1;
		
		if(endPage>maxPage) endPage=maxPage;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		session.setAttribute("pageInfo", pageInfo);
		session.setAttribute("articleList", articleList);
		ActionForward forward = new ActionForward();
		forward.setPath("/intro/intro_list.jsp");
		return forward;
	}

}
