import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementDemo8 {
	public static void main(String[] args) throws SQLException{
		Scanner scan = new Scanner(System.in);
		System.out.print("µø ¿Ã∏ß : "); 
		String dong = scan.next();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_ZIPCODE_SELECT(?,?) }";
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		cstmt.setString(1, dong); // In Mode
		cstmt.registerOutParameter(2, Types.VARCHAR); // Out Mode
		cstmt.execute();
		
		String result = cstmt.getString(2);
		System.out.println(result);
		
		if(cstmt!=null) cstmt.close();
		DBClose.close(conn);
	}
}
