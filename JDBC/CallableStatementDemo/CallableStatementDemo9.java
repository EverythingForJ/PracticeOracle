import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo9 {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_TEST_V3(?) }";
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		// ���� �ϳ��� IN, OUT�� �� �Ѵ�.
		cstmt.setString(1, "������"); // In Mode
		cstmt.registerOutParameter(1, Types.VARCHAR); // Out Mode
		cstmt.execute();
		
		String result = cstmt.getString(1);
		System.out.println(result);
		
		if(cstmt!=null) cstmt.close();
		DBClose.close(conn);
	}
}
