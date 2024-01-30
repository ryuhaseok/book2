package com.javaex.ex03;

import java.util.List;

import com.javaex.ex03.AuthorDao;

public class BookApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		
		//작가 5명 등록
		AuthorVo authorVo1 = new AuthorVo("김문열", "경북 영양");
		authorDao.authorInsert(authorVo1);

		AuthorVo authorVo2 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo2);
		
		AuthorVo authorVo3 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo3);
		
		AuthorVo authorVo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(authorVo4);
		
		AuthorVo authorVo5 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(authorVo5);
		
		//작가 2명 삭제
		authorDao.authorDelete(1);
		authorDao.authorDelete(2);
		
		//작가 1명 수정
		authorDao.authorUpdate("류하석", "집가자", 5);
		
		//작가 출력
		List<AuthorVo> authorList = authorDao.authorList();
				
		for(AuthorVo Vo : authorList) {
			System.out.println(Vo.getAuthorId() + ", "
							 + Vo.getAuthorName() + ", "
							 + Vo.getAuthorDesc());
		}
		
		
		BookDao bookDao = new BookDao();
		
		//책 5권 등록
		BookVo bookvo1 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(bookvo1);

		BookVo bookvo2 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(bookvo2);
		
		BookVo bookvo3 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(bookvo3);
		
		BookVo bookvo4 = new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert(bookvo4);
		
		BookVo bookvo5 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(bookvo5);
		
		//책 수정
		bookDao.bookUpdate(1, "순정만화", "재미주의", "2011-08-03", 5);
		
		//책 삭제
		bookDao.bookDelete(5);
		
		//책 1권 출력
		bookDao.bookSelectOne(2);
		
		//책만 다 출력
		bookDao.bookSelect();
		
		//책 + 작가 다 출력
		List<BookVo> bookList = bookDao.bookList();
		
		for(BookVo Vo : bookList) {
			System.out.println(Vo.getBookId() + ", "
							 + Vo.getTitle() + ", "
							 + Vo.getPubs() + ", "
							 + Vo.getPubDate() + ", "
							 + Vo.getAuthorId() + ", "
							 + Vo.getAuthorName() + ", "
							 + Vo.getAuthorDesc());
		}
		
	}

}
