import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InnerJoinDemo {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();    //2,3
					
		// 3개 이상의 테이블 표준 등가 조인
		String sql = " SELECT EMPLOYEE_ID, FIRST_NAME, HIRE_DATE, E.DEPARTMENT_ID, DEPARTMENT_NAME, CITY, STATE_PROVINCE, COUNTRY_NAME " +
					" FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID=D.DEPARTMENT_ID " +
					" INNER JOIN LOCATIONS LO ON D.LOCATION_ID = LO.LOCATION_ID " +
					" INNER JOIN COUNTRIES CO ON LO.COUNTRY_ID = CO.COUNTRY_ID " +
					" WHERE E.DEPARTMENT_ID IN (?,?,?,?)"; // 불완전한 SQL문장
		
		PreparedStatement pstmt = conn.prepareStatement(sql);  //4. 아직도 불완전한 SQL문자으 하지만 여기서 문법 검사, 개체검사를 수행한다.
		pstmt.setInt(1, 10);
		pstmt.setInt(2, 20);
		pstmt.setInt(3, 30);
		pstmt.setInt(4, 40); //완전한 SQL문장
		ResultSet rs = pstmt.executeQuery();   //5. 매우 주의하자....파라미터로 sql을 전달하지 말자.
		
		int count = 0;
		while(rs.next()) { // 6
			int employees_id = rs.getInt(1);
			String department_name = rs.getString(5);
			String city = rs.getString("CITY");
			String country_name = rs.getString("COUNTRY_NAME");
			System.out.println(++count+" : "+employees_id+"\t"+department_name+"\t"+city+"\t"+country_name);
		}
		
		DBClose.close(conn, pstmt, rs); // 7
	}
}
