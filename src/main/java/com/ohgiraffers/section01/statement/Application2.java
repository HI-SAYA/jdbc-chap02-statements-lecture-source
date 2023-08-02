package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /* 1. Connection 생성 */
        Connection con = getConnection();

        /* 2. Statement 선언 */
        Statement stmt = null;

        /* 3. ResultSet 선언 (조회 구문 실행 시 필요) */
        ResultSet rset = null;

        try {
            /* 4. Statement 객체를 Connection 객체를 이용해 생성 */
            stmt = con.createStatement(); //  <- 순서 1. 적고 try/catch

            Scanner sc = new Scanner(System.in);
            System.out.print("조회하려는 사번을 입력해주세요 : ");
            String empId = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            /* 5. executeQuery() 메소드로 쿼리문 실행 후 결과 ResultSet으로 반환 */
            rset = stmt.executeQuery(query);

            /* 6. ResultSet에 담긴 결과물을 컬럼 이름을 이용해서 꺼내어 출력 */
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME") );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {  // <- 반납
            /* 7. 사용한 자원 반납 */
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
