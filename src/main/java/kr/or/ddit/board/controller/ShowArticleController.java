package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		
		Map<String, Object> resultMap = boardService.readArticle(articleNumber);
		
		request.setAttribute("article", resultMap.get("article")); // 선택한 게시글 정보
		request.setAttribute("reply", resultMap.get("reply")); // 게시글의 댓글 정보
		request.setAttribute("append", resultMap.get("append")); // 게시글의 첨부파일 정보
		
		request.getRequestDispatcher("/article/showArticle.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
