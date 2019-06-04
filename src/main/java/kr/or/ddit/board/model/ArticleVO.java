package kr.or.ddit.board.model;

import java.util.Date;

public class ArticleVO {
	private String article_id;
	private String article_user;
	private String article_board;
	private String article_pid;
	private String article_title;
	private String article_content;
	private Date article_date;
	
	public ArticleVO() {
		
	}

	public ArticleVO(String article_id, String article_user,
			String article_board, String article_pid, String article_title,
			String article_content, Date article_date) {
		super();
		this.article_id = article_id;
		this.article_user = article_user;
		this.article_board = article_board;
		this.article_pid = article_pid;
		this.article_title = article_title;
		this.article_content = article_content;
		this.article_date = article_date;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getArticle_user() {
		return article_user;
	}

	public void setArticle_user(String article_user) {
		this.article_user = article_user;
	}

	public String getArticle_board() {
		return article_board;
	}

	public void setArticle_board(String article_board) {
		this.article_board = article_board;
	}

	public String getArticle_pid() {
		return article_pid;
	}

	public void setArticle_pid(String article_pid) {
		this.article_pid = article_pid;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Date getArticle_date() {
		return article_date;
	}

	public void setArticle_date(Date article_date) {
		this.article_date = article_date;
	}
	
	
}
