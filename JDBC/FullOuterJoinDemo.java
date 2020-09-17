import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.example.libs.DBClose;

public class FullOuterJoinDemo {
	public static void main(String[] args) throws SQLException{
		// 2. Driver Loading
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// Class.forName("oracle.jdbc.driver.OracleDrier")
		
		// 3. Connection
		final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		Properties info = new Properties();
		info.setProperty("user", "hrsist");
		info.setProperty("password", "hrsist");
		Connection conn = DriverManager.getConnection(URL, info);

		// 4. Statement
		Statement stmt = conn.createStatement();
				
		// 5. Query 실행
		// Full Outer Join
		String sql = " SELECT EMPLOYEE_ID, FIRST_NAME, E.DEPARTMENT_ID, D.DEPARTMENT_ID, DEPARTMENT_NAME " +
					" FROM EMPLOYEES E FULL OUTER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ";
		ResultSet rs = stmt.executeQuery(sql);
		
		// 6. ResultSet 뽑아내기
		int count =0;
		while(rs.next()) {
			int employees_id = rs.getInt(1);
			String first_name = rs.getString(2);
			int department_id1 = rs.getInt(3);
			int department_id2 = rs.getInt(4);
			String department_name = rs.getString(5);
			System.out.println(++count+" : "+employees_id+"\t"+first_name+"\t"+department_id1+"\t"+department_id2+"\t"+department_name);
		}
		
		// 7. Close하자
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
}
