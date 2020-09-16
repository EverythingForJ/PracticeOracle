import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class EquiJoinDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
//		String sql = "SELECT E.ENAME, E.DEPTNO, D.DNAME, D.LOC " +
//				" FROM emp e, dept d " + 
//				" WHERE e.deptno=d.deptno AND E.ENAME LIKE UPPER('S%') "; // ��ǥ�� �����, equi join, inner join, simple join.
//		String sql = "SELECT E.ENAME, DEPTNO, D.DNAME, D.LOC " +
//				" FROM emp e NATURAL JOIN dept d " + 
//				" WHERE E.ENAME LIKE UPPER('S%') "; // ǥ�� �����
		// Natural Join ���� ����� Į��(���� ����)���� �ĺ��� ��, ���̺� �̸��̳� ���̺� ��Ī�� ����� �� ����.
		
		String sql = "SELECT E.ENAME, DEPTNO, D.DNAME, D.LOC " +
				" FROM emp e INNER JOIN dept d USING (DEPTNO) " + 
				" WHERE E.ENAME LIKE UPPER('S%') ";
		
		Statement stmt = conn.createStatement(); // 4
		ResultSet rs = stmt.executeQuery(sql); // 5
		while(rs.next()) { // 6
			String ename = rs.getString(1);
			int deptno = rs.getInt(2);
			String dname = rs.getString(3);
			String loc = rs.getString(4);
			
			System.out.println(ename +", "+ deptno +", "+ dname +", "+ loc);
		}
		
		DBClose.close(conn, stmt, rs); // 7
	}
}
