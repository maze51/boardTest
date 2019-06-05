package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	private IuserService userService;
	private IboardService boardService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		logger.debug("LoginController doGet()");
		
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String encryptPassword = KISA_SHA256.encrypt(password);
		
		UserVO userVO = userService.getUser(userId);
		
		if(userVO!=null && encryptPassword.equals(userVO.getPass())){
			List<BoardVO> boardList = boardService.showBoardList();
			List<BoardVO> useBoardList = new ArrayList<BoardVO>(); // 사용하는 게시판을 저장
			
			for (int i=0;i<boardList.size();i++) {
				if(boardList.get(i).getBoard_use().equals("1")){
					useBoardList.add(boardList.get(i));
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", userVO);
			ServletContext context = request.getServletContext();
			context.setAttribute("BOARD_LIST", useBoardList); // application에 저장
//			logger.debug("boardList : {}", useBoardList.get(0));
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
		
	}

}
