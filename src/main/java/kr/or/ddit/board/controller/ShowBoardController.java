package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.paging.model.PageVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/showBoard")
public class ShowBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(ShowBoardController.class);
	
	private IboardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		
		PageVO pageVO = new PageVO(page, pageSize);
		String boardId = request.getParameter("boardId");
		//String boardName = request.getParameter("boardName");
		//---------------------------------------------------------
		// 게시글 페이징 리스트 조회
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", page);
		searchMap.put("pageSize", pageSize);
		searchMap.put("boardId", boardId);
		
		Map<String, Object> resultMap = boardService.articlePagingList(searchMap);
		List<ArticleVO> articleList = (List<ArticleVO>) resultMap.get("articleList");
		int paginationSize = (Integer)resultMap.get("paginationSize");

		request.setAttribute("articleList", articleList);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("PageVO", pageVO);
		
		HttpSession session = request.getSession();
		//session.setAttribute("boardName", boardName);
		//---------------------------------------------------------
		//List<ArticleVO> articleList = boardService.selectAllArticle(boardId); 
		//logger.debug(" " + articleList);
		//request.setAttribute("articleList", articleList);
		
		request.getRequestDispatcher("/board/showBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
