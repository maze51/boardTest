package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/writeArticle")
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
		//logger.debug("WriteArticleController doGet()");
		
		request.getRequestDispatcher("/article/writeArticle.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
