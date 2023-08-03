package com.ohgiraffers.section02.preparedstatement;

import java.sql.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;
public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        /* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
        PreparedStatement pstmt = null;
        /* select 결과 집합을 받아올 용도의 인터페이스 */
        ResultSet rset = null;

        try {
            /* Connection 객체를 이용하여 PreparedStatement 인스턴스 생성
             * => 객체 생성 시 수행할 sql 구문을 인자로 전달한다. */
            pstmt =  con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            // 일반 statement와 다른 점 : 생성할 때 sql구문 전달

            rset = pstmt.executeQuery();

            while(rset.next()) {
                /* next() : ResultSet의 커서 위치를 하나 내리면서 행이 존재하면 true, 존재하지 않으면 false를 반환 */
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
}

// section01.statement.Application1 과 결과가 동일하다.