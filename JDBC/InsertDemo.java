import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class InsertDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn= dbconn.getConnection(); // 2, 3
		//System.out.println(conn);
		// select만 7단계, 나머지는 모두 6단계
		
		Statement stmt = conn.createStatement(); // 4
//		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE )" + 
//					" VALUES (1111, 'CHULSU', 'DEVELOPER', SYSDATE) ";

		int empno = 2222;
		String ename = "YOUNGHEE";
		String job = "Designer";
		String sql = " INSERT INTO EMP_COPY(EMPNO,ENAME, JOB, HIREDATE )" + 
				" VALUES ("+empno+", '"+ename+"', '"+job+"', SYSDATE)";
		
		// ResultSet rs = stmt.executeQuery(sql); // 5. 오직 select에서만 사용
		
		// 5. select를 제외한 나머지 sql은 executeUpdate()를 사용한다.
		// executeUpdate()는 int 반환
		int count = stmt.executeUpdate(sql);
		// 이때 반환받은 count는 데이터베이스에서 DML에 의해 영향을 받은 row의 갯수이다.
		// 만일 0이면 INSERT가 안됐다는 뜻, 1이면 정상적으로 처리됐다는 뜻
		if(count==1) System.out.println("Insert Success");
		else System.out.println("Insert Failure");
		
		DBClose.close(conn, stmt); // 6
	}
}
