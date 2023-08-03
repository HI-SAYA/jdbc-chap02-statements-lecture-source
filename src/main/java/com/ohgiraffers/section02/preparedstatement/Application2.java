package com.ohgiraffers.section02.preparedstatement;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /* 1. Connection 생성 */
        Connection con = getConnection();

        /* 2. PreparedStatement 선언 */
        PreparedStatement pstmt = null;

        /* 3. ResultSet 선언 (조회 구문 실행 시 필요) */
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하려는 사번을 입력해주세요 : ");
        String empId = sc.nextLine();
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";  // empId를 ?로 바꿔준다. 위치 홀더라고 한다.
        // 굳이 try/catch 구문에 없어도 되니까 바깥쪽으로 옮겨줘도 된다.
        // ? 위치홀더 사용이유 : '' 를 계속 사용하게 되면 상당히 귀찮아 진다. 위치홀더는 (?, ?, ?, ? ...) 식으로 사용할 수 있다. 간결함

        try {
            /* 4. PreparedStatement 객체를 Connection 객체를 이용해 생성 */
            pstmt = con.prepareStatement(query); //  <- 순서 1. 적고 try/catch
            /* 위치 홀더로 표기한 자리에 넣을 값을 채워준다. */
            pstmt.setString(1, empId); // ? 위치 홀더 첫번째에 empId로 채워준다.
            // ? 위치 홀더가 많을 경우 인덱스를 증가시켜 가면서 변수를 적어준다. set타입(2(인덱스), 변수))

            /* 5. executeQuery() 메소드로 쿼리문 실행 후 결과 ResultSet으로 반환 */
            rset = pstmt.executeQuery(); // query 문을 반드시 빼야한다. 이미 위에서 query문을 썼기 때문에.

            /* 6. ResultSet에 담긴 결과물을 컬럼 이름을 이용해서 꺼내어 출력 */
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME") );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {  // <- 반납
            /* 7. 사용한 자원 반납 */
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
