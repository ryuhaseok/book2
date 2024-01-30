package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		
		//authorDao.authorInsert("서장훈", "농구선수");
		//authorDao.authorInsert("안정환", "축구선수");
		
		AuthorVo authorVo = new AuthorVo("황일영", "개발강사");
		authorDao.authorInsert(authorVo);
		
		//authorDao.authorDelete(14);
		
		List<AuthorVo> authorList = authorDao.authorList();
		
		for(AuthorVo Vo : authorList) {
			System.out.println(Vo.getAuthorId() + ", "
							 + Vo.getAuthorName() + ", "
							 + Vo.getAuthorDesc());
		}
		
	}

}
