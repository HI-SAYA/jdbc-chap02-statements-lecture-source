package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        /* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
        Statement stmt = null;
        /* select 결과 집합을 받아올 용도의 인터페이스 */
        ResultSet rset = null;

        try {
            /* Connection 객체를 이용하여 Statement 인스턴스 생성 */
           stmt =  con.createStatement();

           rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");

           while(rset.next()) {
               /* next() : ResultSet의 커서 위치를 하나 내리면서 행이 존재하면 true, 존재하지 않으면 false를 반환 */
               System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(con);
        }

    }
}