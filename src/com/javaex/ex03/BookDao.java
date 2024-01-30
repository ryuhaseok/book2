package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	
	//메소드 일반
	private void getConnection() {
		// 0. import java.sql.*;
		
		try {
		
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName(driver);
		
		// 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		
	}//getConnection()
	
	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
	}// close()
	
	
	//bookInsert
	//책등록
	public int bookInsert(BookVo bookVo) {
		
		int count = -1; //정상적으로 진행되지 못 했을 때 -1
		
		this.getConnection();
		try {
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query +=" insert into book ";
			query +=" values(null, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록되었습니다.");
			
		// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
		}
		
		this.close();
		
		return count;
	}//bookInsert()
	
	
	//bookDelete
	public int bookDelete(int no) {
		
		int count = -1; //정상적으로 진행되지 못 했을때 -1 반환
	
		try {
			this.getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			//실행
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 삭제되었습니다.");
			
		// 4.결과처리
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			this.close();
		}
		
		return count;
	}//authorDelete()
	
	
	//bookUpdate
	public int bookUpdate(int bookId, String title, String pubs, String pubDate, int authorId) {
		int count = -1;
		
		try {
			this.getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set  title = ?, ";
			query += " 		pubs = ? ";
			query += " 		pub_date = ? ";
			query += " 		author_id = ? ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(3, authorId);
			pstmt.setInt(4, bookId);
			
			count = pstmt.executeUpdate();
				 
			System.out.println(count + "건 수정되었습니다.");
			
			// 4.결과처리
		}  catch (SQLException e) {
			System.out.println("error:" + e);
			// 5. 자원정리

		}  finally {

		this.close();
		}
		
		return count;
	}//authorUpdate()

	
	//bookSelect 책만
	public void bookSelect() {
		
		try {
		this.getConnection();
		// 3. SQL문 준비 / 바인딩 / 실행
		String query = "";
		query += " select   book_id, ";
		query += " 			title, ";
		query += " 			pubs ";
		query += " 			pub_date ";
		query += " 			author_id ";
		query += " from book ";
		
		//바인딩
		pstmt = conn.prepareStatement(query);
		
		//실행
		rs = pstmt.executeQuery();
		
		// 4.결과처리
	
		while(rs.next()) {
		int bookId = rs.getInt("book_id");
		String title = rs.getString("title");
		String pubs = rs.getString("pubs");
		String pubDate = rs.getString("pub_date");
		int authorId = rs.getInt("author_id");
		
		System.out.println(bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorId);
		}
		// 5. 자원정리
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		this.close();
		
	}
	
	
	//bookSelectAll 책+작가
	public List<BookVo> bookList() {
		
		//리스트 준비
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		try {
			this.getConnection();
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select   b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs ";
			query += " 			b.pub_date ";
			query += " 			b.author_id ";
			query += " 			a.author_name ";
			query += " 			a.author_desc ";
			query += " from book b ";
			query += " left join author a on b.author_id = a.author_id ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("b.book_id");
				String title = rs.getString("b.title");
				String pubs = rs.getString("b.pubs");
				String pubDate = rs.getString("b.pub_date");
				int authorId = rs.getInt("b.author_id");
				String authorName = rs.getString("a.author_name");
				String authorDesc = rs.getString("a.author_desc");
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				
				bookList.add(bookVo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		
		} 
			this.close();
		
		
		return bookList;
	}//authorList()
	
	
	//bookSelectOne(int bookId) 2번책+작가
	public void bookSelectOne(int bId) {
		try {
			this.getConnection();
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs, ";
			query += " 			b.pub_date, ";
			query += " 			b.author_id, ";
			query += " 			a.author_name, ";
			query += " 			a.author_desc ";
			query += " from book b ";
			query += " left join author a on b.author_id = a.author_id ";
			query += " where b.book_id = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			
			for(int i=0; i<bId; i++) {
				rs.next();
			}
			int bookId = rs.getInt("b.book_id");
			String title = rs.getString("b.title");
			String pubs = rs.getString("b.pubs");
			String pubDate = rs.getString("b.pub_date");
			int authorId = rs.getInt("b.author_id");
			String authorName = rs.getString("a.author_name");
			String authorDesc = rs.getString("a.author_desc");
	
			System.out.println(bookId + ".\t" + title + "\t" + pubs + "\t" + pubDate + "\t" + authorId + "\t" + authorName + "\t" + authorDesc);
			
		// 4.결과처리
		}  catch (SQLException e) {
		System.out.println("error:" + e);
		}
		this.close();
		
	}
}
