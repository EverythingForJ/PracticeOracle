import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class JDBCDemo1 {
	private Scanner scan;
	private Connection conn;
	
	public JDBCDemo1() { // 생성자
		this.scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		this.conn = dbconn.getConnection(); // 2,3
	}
	
	public static void main(String[] args) throws SQLException{
		JDBCDemo1 jd = new JDBCDemo1();
		int choice = jd.showMenu();
		jd.process(choice);		
	}
	
	void process(int choice) throws SQLException {
		switch(choice) {
			case 9 : System.out.println("Bye"); DBClose.close(this.conn); System.exit(0);  break;
			case 1 : selectAll(); break;
			case 2 : 
			case 3 : 
			case 4 : selectWhere(choice); break;
		}
	}
	
	void selectWhere(int choice) throws SQLException { // 조건 검색
		String sql = "SELECT * FROM emp ";
		String where = null;
		switch(choice) {
			case 2 : 
				System.out.println("찾고자 하시는 사원의 이름 : ");
				String name = this.scan.next().toUpperCase();
				where = " WHERE UPPER(ename) = '" + name+ "' ";
				break;
			case 3 :
				System.out.println("찾고자 하시는 부서번호 : ");
				int deptno = this.scan.nextInt();
				where = " WHERE deptno = " + deptno;
				break;
			case 4 : 
				System.out.println("찾고자 하시는 사원의 입사년도 : ");
				String hiredate = this.scan.next(); // "1987"
				where = " WHERE TO_CHAR(hiredate, 'YYYY') = '" + hiredate+ "' ";
				break;
		}
		sql += where;
		Statement stmt = this.conn.createStatement(); // 4
		ResultSet rs = stmt.executeQuery(sql); // 5
		while(rs.next()) {	// 6
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			double sal = rs.getDouble("sal");
			Date hiredate = rs.getDate("hiredate");
			int deptno = rs.getInt("deptno");
			System.out.println(empno+"\t"+ename+"\t"+sal+"\t"+hiredate+"\t"+deptno);
		}
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
	}
	
	void selectAll() throws SQLException{ // 모두 보기
		// 4. step
		Statement stmt = this.conn.createStatement();
		String sql = "SELECT * FROM emp ORDER BY empno ASC";
		ResultSet rs = stmt.executeQuery(sql); // 5.
		while(rs.next()) {
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			double sal = rs.getDouble("sal");
			Date hiredate = rs.getDate("hiredate");
			System.out.println(empno+"\t"+ename+"\t"+sal+"\t"+hiredate);
		}
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
	}
	
	int showMenu() {
		System.out.println("*****Menu*****");
		System.out.println("1. 모두 보기");
		System.out.println("2. 사원의 이름으로 검색하기");
		System.out.println("3. 부서 번호로 검색하기");
		System.out.println("4. 입사년도로 검색하기");
		System.out.println("9. 종료");
		System.out.println("선택 >> ");
		return this.scan.nextInt();
	}
}
