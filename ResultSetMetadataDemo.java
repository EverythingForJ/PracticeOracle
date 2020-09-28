import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ResultSetMetadataDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
		Statement stmt = conn.createStatement(); // 4
		ResultSet rs = stmt.executeQuery("SELECT * FROM EMP ");
		ResultSetMetaData rsmd = rs.getMetaData(); // 뽑아올 데이터 형식 모를 때
		int count = rsmd.getColumnCount(); // 컬럼의 갯수 
		// System.out.println(count);
		
		for(int i=1; i<=count; i++) { // db index 1부터 시작
			// System.out.println(rsmd.getColumnLabel(i)); // 컬럼 별칭 출력
			// System.out.println(rsmd.getColumnName(i)); // 컬럼 이름 출력 (버전 차이로 출력 차이 없을 수 있다.)
			// System.out.println("==========================");
			
			System.out.print(rsmd.getColumnName(i) + ":");
//			switch(rsmd.getColumnType(i)) {
//			case Types.NUMERIC : 
//			case Types.DATE : 
//			case Types.CHAR : 
//			default :  // varchar
//			}
			System.out.print(rsmd.getColumnTypeName(i)); // 위보다 편해진 기능
			System.out.print(" : "+rsmd.getPrecision(i)); // 사이즈
			System.out.println(" : "+rsmd.getScale(i)); // 소수점
		}
		
		DBClose.close(conn);
				
	}
}
