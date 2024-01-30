package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		
		int cnt = authorDao.authorInsert("이효리", "제주도");
		System.out.println(cnt + " success");
		
		List<AuthorVo> authorList = authorDao.authorList();

		//향상된 for문 처음부터 끝까지 돌릴때
		for(AuthorVo authorVo : authorList) {
			int id = authorVo.getAuthorId();
			String name = authorVo.getAuthorName();
			String desc = authorVo.getAuthorDesc();
			
			System.out.println(id + ", " + name + ", " + desc);
		}
		
		/*
		for(int i=0; i<authorList.size(); i++) {
			int id = authorList.get(i).getAuthorId();
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			
			System.out.println(id + ", " + name + ", " + desc);
		}
		*/
		//authorDao.authorInsert("이효리", "제주도민");
		//authorDao.authorDelete(12);
	}

}
