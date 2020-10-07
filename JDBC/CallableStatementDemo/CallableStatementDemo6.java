import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo6 {
	public static void main(String[] args) throws SQLException{
		Scanner scan = new Scanner(System.in);
		System.out.print("������ ���۰� : "); 
		int start = scan.nextInt();
		System.out.print("������ �������� : "); 
		int end = scan.nextInt();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_SUM_V3(?,?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		// IN MODE�� setXxx()�޼ҵ带 ����ϰ�, OUT MODE�� registerOutParameter()�޼ҵ带 ����Ѵ�.
		cstmt.setInt(1, start);
		cstmt.setInt(2, end);
		cstmt.registerOutParameter(3, Types.VARCHAR);
		cstmt.execute();
		
		String result = cstmt.getString(3);
		System.out.println(result);
		
		if(cstmt!=null) cstmt.close();
		DBClose.close(conn);
	}
}
