// SELECT를 위한 7단계
//1. import 한다. java.sql.*;
//2. oracle driver loading한다
//3. db에 connection한다.
//4. 문장 객체(statement)를 생성한다.
//5. excuteQuery()를 통해 주어진 sql문장(select만)을 실행한다.
//6. ResultSet이라는 가상테이블을 처리한다.
//7. ResultSet, Statement, Connection을 close한다.


import java.sql.*; // 1. import

public class JDBCDemo {
	public static void main(String[] args) {
		// 2. step
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Failure");
		}
		
		// 3. 오라클 드라이버를 통해서 자바가 오라클DB에 접속하도록하자.
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			// 다른사람것을 쓰고싶다면 localhost(or 자기 IP)자리에 그사람의 ip를 넣기, enterprize는 orcl, expression은 ex쓰기.
			System.out.println("연결 성공!");
			stmt = conn.createStatement(); //4. step
			String sql = "SELECT empno, ename, LPAD(ename, 10, '*') FROM emp WHERE deptno = 30";
			rs = stmt.executeQuery(sql); // 5. step
			// 6. step
			while(rs.next()) {
				int empNo = rs.getInt("empno");
				String eName = rs.getString("ename");
				String lPad = rs.getString(3);
				System.out.printf("%d, %s, %s%n", empNo, eName, lPad);
			}
		} catch (SQLException e) {
			System.out.println("연결 실패..");
		}
		
		// 7. step
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("닫는걸 실패...");
		}
		
	}
}
