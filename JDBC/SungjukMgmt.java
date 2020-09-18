import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class SungjukMgmt {
	private Scanner scan;
	private Connection conn;
	
	public SungjukMgmt() { // Constructor
		this.scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		this.conn = dbconn.getConnection();
		System.out.println("객체가 생성됐습니다.");
	}
	
	@Override
	protected void finalize() {
		System.out.println("프로그램을 이용해주셔서 감사합니다.");
		DBClose.close(conn);
	}
	
	public static void main(String[] args) throws SQLException {
		SungjukMgmt sm = new SungjukMgmt(); // 생성자 호출
		while(true) {
			int choice = sm.showMenu();
			if(choice == 9) break;
			if(choice >= 1 && choice <=5) sm.process(choice);
		}
		sm=null; // null 넣고
		System.gc(); // 가비지 컬렉터 호출
	}
	
	
	private int showMenu() {
		System.out.println("\n9. 종료하기 \t 1. 모두 보기 \t 2. 검색하기 \t 3. 추가하기 \t 4. 수정하기 \t 5. 삭제하기");
		System.out.print("선택>> ");
		return this.scan.nextInt();
	}

	private void process(int choice) throws SQLException { // 1부터 5까지만
		switch(choice) {
		case 1 : selectAll();	break;
		case 2 : break;
		case 3 : insert();	break;
		case 4 : update();	break;
		case 5 : break;
		}
	}
	
	
	
	private void update() throws SQLException {
		System.out.print("수정할 학생의 학번 : ");
		String hakbun = this.scan.next();
		String sql = "SELECT KOR, ENG, MAT FROM STUDENT WHERE HAKBUN = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sql); // 4. 아직까지 불완전한 sql문장
		pstmt.setString(1, hakbun);
		ResultSet rs = pstmt.executeQuery(); // 5.
		rs.next(); // 6.
		int kor = rs.getInt("kor");
		int eng = rs.getInt("eng");
		int mat = rs.getInt("mat");
		int tot = kor+eng+mat;
		double avg = tot / 3.;
		String grade = (avg >= 90) ? "A":
						(avg >= 80) ? "B":
							(avg >= 70) ? "C":
								(avg >= 60) ? "D" : "F";
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		sql = "UPDATE STUDENT SET TOT =?, AVG = ?, GRADE = ? WHERE HAKBUN = ?"; // 불완전한 SQL문장
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, tot);
		pstmt.setDouble(2, avg);
		pstmt.setString(3, grade);
		pstmt.setString(4, hakbun);
		
		int row = pstmt.executeUpdate();
		if(row==1)System.out.println("Update Success");
		else System.out.println("Update Failure");
		
		if(pstmt!=null) pstmt.close();
				
	}
	
	
	private void insert() throws SQLException {
		System.out.print("학번 : ");	String hakbun = this.scan.next();
		System.out.print("이름 : ");	String name = this.scan.next();
		System.out.print("국어 : ");	int kor = this.scan.nextInt();
		System.out.print("영어 : ");	int eng = this.scan.nextInt();
		System.out.print("수학 : ");	int mat = this.scan.nextInt();
		// 불완전한 SQL문 만들기
		
		String sql = " INSERT INTO STUDENT (HAKBUN,NAME,KOR,ENG,MAT) VALUES(?,?,?,?,?) ";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, hakbun);
		pstmt.setString(2, name);
		pstmt.setInt(3, kor);
		pstmt.setInt(4, eng);
		pstmt.setInt(5, mat);
		
		int rows = pstmt.executeUpdate(); // parameter에 sql 넣지말기.
		if(rows==1) System.out.println("추가 성공");
		else System.out.println("추가 실패");
		
		if(pstmt!=null)pstmt.close();
	}
	
	private void selectAll() throws SQLException {
		Statement stmt = this.conn.createStatement(); // 4.
		String sql = "SELECT * FROM STUDENT ORDER BY TOT DESC ";
		ResultSet rs = stmt.executeQuery(sql); // 5.
		
		System.out.println("학번\t이름 \t국어\t영어\t수학\t총점\t평균\t평점");
		System.out.println("-----------------------------------------------------");
		
		while(rs.next()) {
			String hakbun = rs.getString(1);
			String name = rs.getString(2);
			int kor = rs.getInt(3);
			int eng = rs.getInt(4);
			int mat = rs.getInt(5);
			int tot = rs.getInt(6);
			double avg= rs.getDouble(7);
			String grade = rs.getString(8);
			System.out.println(hakbun+"\t"+name+"\t"+kor+"\t"+eng+"\t"+mat+"\t"+tot+"\t"+avg+"\t"+grade);
		}
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
	}

}