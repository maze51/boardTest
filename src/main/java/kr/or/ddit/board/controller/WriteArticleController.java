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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/writeArticle")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class WriteArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(WriteArticleController.class);
    
	private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/article/writeArticle.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		logger.debug("WriteArticleController doPost()");
		
		int articleNumber = boardService.getNextArticleNumber(); // 게시글 번호
		
		HttpSession session = request.getSession();
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId(); // 작성자
		String boardId = request.getParameter("boardId"); // 게시판아이디
		logger.debug("boardId: {}", boardId);
		String title = (String) request.getParameter("title"); // 제목
		logger.debug("title: {}", title);
		String content = (String) request.getParameter("content"); // 내용
		logger.debug("content: {}", content);
		
		ArticleVO articleVo = new ArticleVO();
		
		articleVo.setArticle_number(articleNumber);
		articleVo.setArticle_user(userId);
		articleVo.setArticle_board(boardId);
		articleVo.setArticle_title(title);
		articleVo.setArticle_content(content);
		articleVo.setArticle_group(articleNumber);
		
		int insertCnt = boardService.writeArticle(articleVo);
		
		// 파일 처리 시작-------------------------------------------------
		
		
		// profile 파일 업로드 처리
		
		Part profile = request.getPart("profile");
		AppendVO append = new AppendVO();
		int fileInsertCnt = 0;
		logger.debug("size : {}", profile);
		// 사용자가 파일을 업로드한 경우
		if(profile.getSize() > 0){
			
			String contentDisposition = profile.getHeader("content-disposition");
			String filename = PartUtil.getFileName(contentDisposition); // 실제 파일명
			String ext = PartUtil.getExt(filename);
			
			String uploadPath = PartUtil.getUploadPath();
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
				//////////////////////////////////////////////////////////
				if(ServletFileUpload.isMultipartContent(request)){
					try {
						List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
						
						for(FileItem item : multiparts){
							if(!item.isFormField()){
								String name = new File(item.getName()).getName();
								item.write(new File(uploadPath + File.separator + UUID.randomUUID().toString() + ext));
							}
						}
						
					} catch (FileUploadException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			/*	
			// 파일 디스크에 쓰기
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			append.setAppend_path(filePath);
			append.setAppend_filename(filename);
			append.setAppend_article(articleNumber);
			
			profile.write(filePath);
			profile.delete(); // 디스크 임시 공간에 저장된 파일이 있다면 삭제한다.
			*/
			}
		fileInsertCnt = boardService.insertAppend(append);
		}
		if(insertCnt == 1 && fileInsertCnt == 1){
			logger.debug("새글작성완료");
			response.sendRedirect(request.getContextPath()+"/showArticle?aNumber="+articleNumber);
		}
	}

}
