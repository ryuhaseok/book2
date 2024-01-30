package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	
	//생성자
	
	//메소드 g/s
	
	//메소드 일반
	
	//작가리스트
	public List<AuthorVo> authorList() {
		
		//리스트 준비
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//db에서 데이터 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(no, name, desc);
				
				authorList.add(authorVo);
			}
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		
		return authorList;
	}//authorList()
	
	//작가수정
	public void authorUpdate() {
		
	}
	
	//작가삭제
	public int authorDelete(int no) {
		
		int count = -1; //정상적으로 진행되지 못 했을때 -1 반환
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			//실행
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 삭제되었습니다.");
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
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
		}
		
		return count;
	}//authorDelete()
	
	//작가등록
	public int authorInsert(String name, String desc) {
		
		int count = -1; //정상적으로 진행되지 못 했을 때 -1
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query +=" insert into author ";
			query +=" values(null, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록되었습니다.");
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		
		return count;
	}//authorInsert()
}
