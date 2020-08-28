// JDBC를 사용한 select 처리 7 단계

// 1. import 하자.
import java.sql.*;

public class JdbcDemo {
	public static void main(String[] args) {
		// 2. 오라클 드라이버를 메모리에 로딩하자
		// 자바에서 드라이브는 클래스다. 오라클 드라이버는 클래스다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스를 찾았다!");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 못찾음ㅜㅜ");
			// ojdbc8.jar 파일이 어딨는지 찾아주자. 프로젝트 우클릭 -> build path -> Add External Archives 후에 파일 선택 및 적용
			// jar는 압축을 풀지않고 쓴다.
		}
		
		// 3. 오라클 드라이버를 통해서 자바가 오라클DB에 접속하도록하자.
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			// 다른사람것을 쓰고싶다면 localhost(or 자기 IP)자리에 그사람의 ip를 넣기, enterprize는 orcl, expression은 ex쓰기.
			System.out.println("연결 성공!");
		} catch (SQLException e) {
			System.out.println("연결 실패..");
		}
		
		// 4. Statement객체를 생성하자.
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			System.out.println("statement객체 만들기 성공");
			// 5. SQL 문 작성해서 실행하자 ( 쿼리문 작성 )
			String sql = "SELECT empno, ename, sal FROM emp";
			rs = stmt.executeQuery(sql); // select작성시 항상 excuteQuery메소드만 쓴다.
			// select는 바구니가 필요하다. 데이터를 가져와야하기때문에, 그 바구니를 ResultSet이라 한다.
			
			// 6. SELECT일 경우 ResultSet 처리하자. // select 아닌 겨우 6번 과정 필요없다.
			while(rs.next()) {
				int empNo = rs.getInt(1); // 오라클은 index가 1부터 시작하므로 empno는 1column에 있다.
				String eName = rs.getString("ename"); // 이름으로 읽어도 된다.
				double sal = rs.getDouble(3);
				System.out.printf("%d, %s, %7.2f%n", empNo, eName, sal);
			}
			
		} catch (SQLException e) {
			System.out.println("statement객체 만들기 실패 ㅠ");
		}

		// 7. close하자. db도 반드시 닫아야한다.
		// 닫는 순서도 역순 차례로
		try{
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("닫는걸 실패...");
		}
		
	}
}
