import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo4 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		String sql = "{ call SP_EMP_SELECT(?,?,?) }"; // 불완전 sql문장
		CallableStatement cstmt = conn.prepareCall(sql);
		// 주의점, IN mode만 setXXX()메소드를 사용할 것
		cstmt.setInt(1, 7788);
		cstmt.registerOutParameter(2, Types.VARCHAR); // 이름
		cstmt.registerOutParameter(3, Types.NUMERIC); // 봉급
		cstmt.execute(); // 주의할점, executeUpdate()나 executeQuery()가 아니다.

		String ename = cstmt.getString(2);
		double sal = cstmt.getDouble(3);
		
		System.out.println("사원 번호 : 7788");
		System.out.println("사원명 : "+ename);
		System.out.println("봉급  : "+sal);
		
		if(cstmt!=null)cstmt.close();
		DBClose.close(conn); // 7
	}
}
