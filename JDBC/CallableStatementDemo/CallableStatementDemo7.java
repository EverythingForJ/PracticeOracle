import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo7 {
	public static void main(String[] args) throws SQLException{
		Scanner scan = new Scanner(System.in);
		System.out.print("�̸� : "); 
		String ename = scan.next().toUpperCase();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_EMP_DEPT_SELECT(?,?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		// IN MODE�� setXxx()�޼ҵ带 ����ϰ�, OUT MODE�� registerOutParameter()�޼ҵ带 ����Ѵ�.
		cstmt.setString(1, ename); // IN Mode
		cstmt.registerOutParameter(2, Types.VARCHAR); // OUT Mode
		cstmt.registerOutParameter(3, Types.NUMERIC); // OUT Mode
		cstmt.execute();
		
		String dname = cstmt.getString(2);
		double sal = cstmt.getDouble(3);

		System.out.println("User Name : " + ename);
		System.out.println("Department Name : " + dname);
		System.out.println("Salary : " + sal);
		
		if(cstmt!=null) cstmt.close();
		DBClose.close(conn);
	}
}
