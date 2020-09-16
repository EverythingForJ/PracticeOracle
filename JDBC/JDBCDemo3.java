// 1. import하자.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

public class JDBCDemo3 {
	public static void main(String[] args) {
		//2. Driver Loading
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("Oracle Driver Loading Success");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Oracle Driver Loading Failure");
//		}
		try { // 2. 다른 방법
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("Oracle Driver Loading Success");
		} catch (SQLException e) {
			System.out.println("Oracle Driver Loading Failure");
		}
		
		//3. Connection
		Properties info = new Properties();
		info.setProperty("user", "scott");
		info.setProperty("password", "tiger");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, info);
			System.out.println(conn);
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		}
		
		//4. Making a Statement object
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			String name = "SCOTT";
//			// 사원의 이름을 입력받아서 그 사원의 사번, 이름, 소속 부서이름, 위치, 부서번호 출력하기
//			String sql = "   SELECT empno, ename, dname, loc, deptno  " + 
//			                  "    FROM  emp  NATURAL JOIN dept  " + 
//					          "    WHERE ename = '" + name + "' ";
//			rs = stmt.executeQuery(sql);   //5. 
//			while(rs.next()) { // 6.
//				int empno = rs.getInt("empno");
//				String ename = rs.getString("ename");
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//				int deptno = rs.getInt("deptno");
//				System.out.println(empno + "\t" + ename + "\t" + dname + "\t" + loc + "\t" + deptno);
//			}
//		}catch (SQLException e) {
//			System.out.println(e.toString());
//		}
		
		//4. Making a Prepared Statement object = '' 대신 물음표 사용
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			String name = "SCOTT";
			// 사원의 이름을 입력받아서 그 사원의 사번, 이름, 소속 부서이름, 위치, 부서번호 출력하기
			String sql = "   SELECT empno, ename, dname, loc, deptno  " + 
			                  "    FROM  emp  NATURAL JOIN dept  " + 
					          "    WHERE ename = ? "; // 불완전한 SQL문장
			pstmt = conn.prepareStatement(sql); // 아직까진 불완전한 SQL 문장. 문법검사, 개체검사
			pstmt.setString(1, name); // 첫번째 물음표에  원하는 이름 넣기 -> 완전한 SQL문장 완료
			rs = pstmt.executeQuery();  // 주의할점, sql을 파라미터에 넣으면 안된다. 이미 완성됐기 때문에
			
			while(rs.next()) { // 6.
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				int deptno = rs.getInt("deptno");
				System.out.println(empno + "\t" + ename + "\t" + dname + "\t" + loc + "\t" + deptno);
			}
				
		}catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		
	}
}
