import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class UpdateDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2, 3
		
		Statement stmt = conn.createStatement(); // 4
		
		String sql = " UPDATE EMP_COPY " +
					" SET MGR =2222, SAL=1500, COMM = 100 " +
					" WHERE empno = 5555 ";
		
		int rows = stmt.executeUpdate(sql); // 5
		if(rows == 1) System.out.println("Update Success");
		else System.out.println("Update Failure");
		DBClose.close(conn, stmt); // 6
	}
}
