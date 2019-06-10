package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/showArticle")
public class ShowArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final Logger logger = LoggerFactory
			.getLogger(ShowArticleController.class);
    
    private IboardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleNumber = Integer.parseInt(request.getParameter("aNumber"));
		//logger.debug("aNum : {}", articleNumber);
		
		ArticleVO article = boardService.readArticle(articleNumber); // 선택한 게시글 정보
		request.setAttribute("article", article);
		
		List<ReplyVO> replyList = boardService.readReply(articleNumber);
		request.setAttribute("reply", replyList);
		
		List<AppendVO> appendList = boardService.readAppend(articleNumber); // 게시글의 첨부파일 정보
		request.setAttribute("append", appendList);
		
		request.getRequestDispatcher("/article/showArticle.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost()");
	}

}
