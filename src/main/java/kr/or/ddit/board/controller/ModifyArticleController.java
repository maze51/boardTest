package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/modifyArticle")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class ModifyArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ModifyArticleController.class);
	
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//logger.debug("ModifyArticleController doGet()");
		int articleNumber = Integer.parseInt(request.getParameter("arNum"));
		
		ArticleVO article = boardService.readArticle(articleNumber); // 선택한 게시글 정보
		request.setAttribute("article", article);
		
		List<AppendVO> appendList = boardService.readAppend(articleNumber); // 게시글의 첨부파일 정보
		request.setAttribute("append", appendList);
		
		request.setAttribute("appendSize", appendList.size());
		request.getRequestDispatcher("/article/modifyArticle.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("ModifyArticleController doPost()");
		request.setCharacterEncoding("utf-8");
		
		int articleNum = Integer.parseInt(request.getParameter("articleNum")); // 게시글번호
		String title = (String) request.getParameter("title"); // 제목
		String content = (String) request.getParameter("content"); // 내용
		
		ArticleVO articleVo = new ArticleVO();
		
		articleVo.setArticle_number(articleNum);
		articleVo.setArticle_title(title);
		articleVo.setArticle_content(content);
		
		int modifyCnt = boardService.modifyArticle(articleVo);
		
		// 파일 처리 시작-------------------------------------------------
		
		List<Part> pList = (List<Part>) request.getParts();
		int cnt = 0;
		
		for(int i=0;i<pList.size();i++){
			//logger.debug("header : {}", pList.get(i).getHeaderNames());
			if(pList.get(i).getHeaderNames().contains("content-type")){ // form에서 입력받는 내용 중 파일만 선택
				int fileInsertCnt = fileUpload(articleNum, pList.get(i)); // 파일 업로드 과정 수행
				cnt += fileInsertCnt;
			}
		}
		
		if((modifyCnt == 1 && cnt == pList.size()) || modifyCnt == 1){
			logger.debug("게시글수정완료");
			response.sendRedirect(request.getContextPath()+"/showArticle?aNumber="+articleNum);
		}
	}
	
	public int fileUpload(int articleNumber, Part profile) throws IOException {
		AppendVO append = new AppendVO();
		int fileInsertCnt = 0;
		
		// 사용자가 파일을 업로드한 경우
		if(profile.getSize() > 0){
			
			String contentDisposition = profile.getHeader("content-disposition");
			String filename = PartUtil.getFileName(contentDisposition); // 실제 파일명
			String ext = PartUtil.getExt(filename);
			
			String uploadPath = PartUtil.getUploadPath();
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
				
			// 파일 디스크에 쓰기
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			append.setAppend_path(filePath);
			append.setAppend_filename(filename);
			append.setAppend_article(articleNumber);
			
			profile.write(filePath);
			profile.delete(); // 디스크 임시 공간에 저장된 파일이 있다면 삭제한다.
			}
		fileInsertCnt = boardService.insertAppend(append);
		}
		return fileInsertCnt;
	}
}
