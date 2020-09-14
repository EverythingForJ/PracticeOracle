// SELECT�� ���� 7�ܰ�
//1. import �Ѵ�. java.sql.*;
//2. oracle driver loading�Ѵ�
//3. db�� connection�Ѵ�.
//4. ���� ��ü(statement)�� �����Ѵ�.
//5. excuteQuery()�� ���� �־��� sql����(select��)�� �����Ѵ�.
//6. ResultSet�̶�� �������̺��� ó���Ѵ�.
//7. ResultSet, Statement, Connection�� close�Ѵ�.


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
		
		// 3. ����Ŭ ����̹��� ���ؼ� �ڹٰ� ����ŬDB�� �����ϵ�������.
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			// �ٸ�������� ����ʹٸ� localhost(or �ڱ� IP)�ڸ��� �׻���� ip�� �ֱ�, enterprize�� orcl, expression�� ex����.
			System.out.println("���� ����!");
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
			System.out.println("���� ����..");
		}
		
		// 7. step
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("�ݴ°� ����...");
		}
		
	}
}
