import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo5 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_EMP_COUNT(?) }"; // 불완전 sql문장
		CallableStatement cstmt = conn.prepareCall(sql); // 4
		// 주의점, IN mode만 setXXX()메소드를 사용할 것
		// OUT용 파라미터는 registerOutParameter()를 사용
		cstmt.registerOutParameter(1, Types.NUMERIC); // 봉급
		cstmt.execute(); // 5. 주의할점, executeUpdate()나 executeQuery()가 아니다.
		
		int count = cstmt.getInt(1);
		
		System.out.println("이 회사의 사원 수는   "+count+"명 입니다.");
		
		if(cstmt!=null)cstmt.close();
		DBClose.close(conn); // 7
	}
}
