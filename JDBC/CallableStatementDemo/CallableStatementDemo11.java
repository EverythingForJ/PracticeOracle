import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date; // util.date�� �ڽ��� sql.date�� util�� �޾Ƶ��ȴ�.

import oracle.jdbc.OracleTypes;

public class CallableStatementDemo11 {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
		String sql = "{ call SP_EMP_DEPT_SELECT(?) }";
		
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.registerOutParameter(1, OracleTypes.CURSOR); // OUT Mode
		cstmt.execute();
		
		Object obj = cstmt.getObject(1);
		ResultSet rs = (ResultSet)obj; // ��������ȯ ����
		System.out.println("��� ��ȣ \t ��� �̸� \t ���� \t �Ի��� \t �μ� �̸� \t �μ� ��ġ \t �μ� ��ȣ");
		System.out.println("--------------------------------------------------------------");
		while(rs.next()) {
			int empno = rs.getInt("empno");
			String ename =rs.getString("ename");
			String job = rs.getString("job");
			Date hiredate = rs.getDate("hiredate");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");
			int deptno = rs.getInt("deptno");
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%d%n",
					empno, ename, job, hiredate, dname, loc, deptno);
		}
		
		if(rs != null) rs.close();
		if(cstmt != null) cstmt.close();
		DBClose.close(conn);
	}
}
