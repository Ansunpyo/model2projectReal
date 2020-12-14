package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BannerUploadService;
import vo.ActionForward;
import vo.Banner;

public class BannerUploadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int result = 0;
		
		Banner ban = null;
		int fileSize = 10 * 1024 * 1024;
		String realFolder = request.getSession().getServletContext().getRealPath("/banner");
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		ban = new Banner();
		ban.setImg(multi.getFilesystemName("bannerFile"));
		
		BannerUploadService bannerUploadService = new BannerUploadService();
		result = bannerUploadService.uploadBanner(ban);
		
		if(result > 0) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("banner.do");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.do");
		}
		
		return forward;
	}

}
