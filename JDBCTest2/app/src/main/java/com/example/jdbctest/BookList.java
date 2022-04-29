package com.example.jdbctest;

import android.os.health.SystemHealthManager;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookList {
    Connection con;

    public BookList() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/madang2";
        String userid = "madang2";
        String pwd = "madang2";
        con = DriverManager.getConnection(url, userid, pwd);
        System.out.println("데이터베이스  연결 성공...");
    }

    public void selectQuery() throws SQLException {
        String query = "select * from book";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("도서번호\t도서이름\t\t출판사\t가격");
        while (rs.next()) {
            System.out.print("\t" + rs.getInt(1));
            System.out.print("\t" + rs.getString(2));
            System.out.print("\t\t" + rs.getString(3));
            System.out.println("\t" + rs.getInt(4));
        }
        con.close();
    }

    public static void main(String[] args) throws SQLException {
        BookList book = new BookList();
        book.selectQuery();
    }

}
