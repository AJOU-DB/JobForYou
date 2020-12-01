/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * 사용자의 정보에 적합한 취업 지원 정책 및 채용 행사 목록을 제공하는 서비스 개발 
 * JobForYou(잡뽀유)
 */

package JobForYou;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.net.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import org.apache.*;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;;



public class JobForYou {
	
	int flag = 1;
	static Connection conn;
	static Statement stm;
	static String email = null;
	
	static int areaCode = 0;
	static String interestCode = null;
    
	public static int menu() //Show Menu List
	{
		System.out.println("\nJobforYou 서비스는 청년취업정책 정보와 채용행사 정보를 제공하고 있습니다.\n"
				+ "정기 메일 수신 신청을 선택하시면, 고객님께 맞춤 정보를 정기적으로 메일로 수신해 드립니다.\n"
				+ "이용하실 서비스 번호를 입력해주세요.\n(ex. 이용하실 서비스가 '정책'이면, 1만 입력해주세요.)\n"
				+ "1. 정책 정보 열람\n"
				+ "2. 채용 행사 정보 열람\n"
				+ "3. 정기 메일 수신 신청\n" 
				+ "4. 서비스 종료");
		Scanner scan = new Scanner(System.in);
		int sltNum = scan.nextInt();
		return sltNum;
	}
	
	public static int CorQ() // Continue or Quit
	{
		System.out.println("\n다른 서비스를 이용하시겠습니까? 원하는 번호를 입력해주세요!\n"
				+ "1. 서비스 선택 페이지\n"
				+ "2. 서비스 종료");
		Scanner scan = new Scanner(System.in);
		int CorQ = scan.nextInt();
		return CorQ;
	}
	
	public static void Quit() //Quit Service Function, Print Quit message.
	{
		System.out.println("\nJobForYou를 이용해 주셔서 감사합니다.\n"
				+ "원하시는 정보를 얻으셨기를 바랍니다! :)\n");
		System.exit(0);
	}
	
	public static int displayAreaList() // display List of Area
	{
		System.out.println("\n지역 코드를 입력해주세요.\n(ex. 지역이 '서울, 강원'이면, 1만 입력해주세요.)\n"
				+ "1. 서울, 강원\n"
				+ "2. 부산, 경남\n"
				+ "3. 대구, 경북\n"
				+ "4. 경기, 인천\n"
				+ "5. 광주, 전라, 제주\n"
				+ "6 대전, 충청");
		Scanner scan = new Scanner(System.in);
		int areaCode = scan.nextInt();
		return areaCode;
	}
	
	public static int displayInterestList() // display List of Interest
	{
		System.out.println("\n관심 분야 코드를 입력해주세요.\n(ex. 관심 분야가 '취업지원'이면, 1만 입력해주세요.)\n"
				+ "1. 취업지원\n"
				+ "2. 교육훈련·체험·인턴\n"
				+ "3. 전문분야 취업지원\n"
				+ "4. 중소기업 취업지원\n"
				+ "5. 해외진출\n"
				+ "6. 창업\n"
				+ "7. R&D 지원\n"
				+ "8. 경영 지원\n"
				+ "9. 자본금 지원\n"
				+ "10. 생활·복지\n"
				+ "11. 건강\n"
				+ "12. 문화\n"
				+ "13. 주거·금융\n"
				+ "14. 생활비 지원 및 금융 혜택\n"
				+ "15. 주거 지원\n"
				+ "16. 학자금 지원");
		Scanner scan = new Scanner(System.in);
		int interestCode = scan.nextInt();
		return interestCode;
	}
	
	public static String getInfoString(String statement)
	{
		System.out.println(statement);
		Scanner scan = new Scanner(System.in);
		String answer = scan.nextLine();
		return answer;
	}
	
	public static int getInfoInt(String statement)
	{
		System.out.println(statement);
		Scanner scan = new Scanner(System.in);
		int answer = scan.nextInt();
		return answer;
	}
	
	public static int services(int sltNum, String name, String interestCode, String email, int areaCode, int flag) throws SQLException
	{
		switch(sltNum) 
		{
			case 1: flag = policyList(name, interestCode, email); //정책 정보 열람
				break;
				
			case 2: flag = recruitmentEventList(name, areaCode); //채용행사 정보 열람
				break;
				
			case 3: flag = subscribe(name, email); //정기 구독 신청
				break;
				
			case 4 : flag = 2;
		}
		return flag;
	}

	public static int policyList(String name, String interestCode, String email) throws SQLException //Display PolicyList
	{
		String JobQuery = "select * from JobPolicy where JobPolicy.interestCode ='"+interestCode+"'";
		ResultSet r = getQuery(JobQuery);
		System.out.println("\n"+name+"님을 위한 정책 정보 리스트를 보여드릴게요!\n");
		while(r.next())
		{
			System.out.println
			(
					"사업명: " +
					r.getString(4)+" | "+
					"사업개요: " +
					r.getString(5)+" | "+
					"담당기관명: " +
					r.getString(6)+" | "+
					"연령: " +
					r.getString(9)+" | "+
					"학력: " +
					r.getString(10)+" | "+
					"취업상태: " +
					r.getString(11)+" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		return CorQ();
	}
	
	public static int recruitmentEventList(String name, int areaCode) throws SQLException // Display RecruitmentEventList
	{
		String RecruitmentEventQuery = "select * from RecruitmentEvent where areaCode = '" + areaCode + "'";
		ResultSet r = getQuery(RecruitmentEventQuery);
		System.out.println("\n"+name+"님을 위한 채용 행사 리스트를 보여드릴게요!\n");
		while(r.next())
		{
			System.out.println
			(
					"채용행사 번호: " +
					r.getString(1)+" | "+
					"행사명: " +
					r.getString(3)+" | "+
					"행사기간: " +
					r.getString(4)+" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		int choice = getInfoInt("\n다른 서비스를 이용하시겠습니까? 원하는 번호를 입력해주세요!\n"
				+ "1. 채용행사 상세정보 보기\n"
				+ "2. 서비스 선택 페이지\n"
				+ "3. 서비스 종료");
		if (choice == 1) {
			int eventNoValue = getInfoInt("\n상세정보를 확인하고 싶은 채용행사 번호를 입력해주세요!");
			return recruitmentEventDetail(name, eventNoValue);
		} else {
			return choice == 2 ? 1 : 2;
		}

	}
	
	public static int recruitmentEventDetail(String name, int eventNoValue) throws SQLException // Display RecruitmentEventDetail
	{
		String RecruitmentEventDetailQuery = "select * from RecruitmentEventDetail where eventNo = '" + eventNoValue + "'";
		ResultSet r = getQuery(RecruitmentEventDetailQuery);
		System.out.println("\n"+name+"님, " + eventNoValue + "번 채용 행사의 상세 정보를 보여드릴게요!\n");
		while(r.next())
		{
			System.out.println
			(
					"행사명: " +
					r.getString(2)+" | "+
					"행사기간: " +
					r.getString(3)+" | "+
					"행사장소: " +
					r.getString(4)+" | "+
					"이메일: " +
					r.getString(5)+" | "+
					"문의전화: " +
					r.getString(6)+" | "+
					"담당자: " +
					r.getString(7)+" | "+
					"오시는길: " +
					r.getString(8) +" | "+
					"\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
			);	
		}
		return CorQ();
	}
	
	
	public static int subscribe(String name, String email) throws SQLException //Regular Mail Subscription
	{
		System.out.print("\n정기 메일 수신을 신청해 주셔서 감사합니다.\n"
				+ "청년취업정보와 채용행사 정보를 정기적으로 받아보실 수 있습니다!\n");
		Scanner scan = new Scanner(System.in);
		email = getInfoString("아래에  메일 수신하실 메일 주소를 입력해주세요.");
		
		try {
//			HttpPost post = new HttpPost(URL);
//			JSONObject payload = new JSONObject();
//			payload.put("name", "myName");
//			payload.put("age", "20");
//			post.setEntity(new StringEntity(payload.toString(), ContentType.APPLICATION_JSON))
			
			double dValue = Math.random();
		    int iValue = (int)(dValue * 10);
		    String s = "";
		    if(iValue % 2 == 0) {
		    	String JobQuery = "select * from JobPolicy where JobPolicy.interestCode ='"+ interestCode +"'";
				ResultSet r = getQuery(JobQuery);
				
				s = s + name+"님을 위한 정책 리스트를 보여드릴게요!";
				r.next();
				
							s = s + "사업명: " +
							r.getString(4)+" | "+
							"사업개요: " +
							r.getString(5)+" | "+
							"담당기관명: " +
							r.getString(6)+" | "+
							"연령: " +
							r.getString(9)+" | "+
							"학력: " +
							r.getString(10)+" | "+
							"취업상태: " +
							r.getString(11)+" | ";
		    } else {
		    	String RecruitmentEventQuery = "select * from RecruitmentEvent where areaCode = '" + areaCode + "'";
				ResultSet r = getQuery(RecruitmentEventQuery);
				s = s + name+"님을 위한 채용 행사 리스트를 보여드릴게요!";
				r.next();
							s = s + "채용행사 번호: " +
							r.getString(1)+" | "+
							"행사명: " +
							r.getString(3)+" | "+
							"행사기간: " +
							r.getString(4)+" | ";
		    }
		    System.out.println(email + s);
	        
	        String json = "{\"email\": \"" + email + "\", \"msg\": \"" + s + "\"}";
	        System.out.println(json);
		    //String json = "{\"email\": \"shhan730@gmail.com\", \"msg\": \"123\"}";
	        URL url = new URL("http://35.232.159.201:3001/api/mail");
	           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	           conn.setConnectTimeout(5000);
	           conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	           conn.setDoOutput(true);
	           conn.setDoInput(true);
	           conn.setRequestMethod("POST");
	           OutputStream os = conn.getOutputStream();
	           os.write(json.getBytes("UTF-8"));
	           os.close(); 
	           InputStream in = new BufferedInputStream(conn.getInputStream());
	           System.out.println(in);
		    
			//String jsonInputString = "{\"email\": \"" + email + "\", \"msg\": \"" + s + "\"}";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.print("\n정기 메일 수신 신청이 완료되었습니다.\n"
				+ "확인 메일을 보내드렸으니 메일함을 확인해 보세요!\n");
		return CorQ();
	}
	
	public static ResultSet getQuery(String Query) throws SQLException
	{
		ResultSet r = stm.executeQuery(Query); // insert query
	    return r;
	}
	
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		// connect Database
		        
        String host = "34.94.93.148";
        String port = "5432";
        String db_name = "postgres";
        String username = "postgres";
        String password = "uMY1vk*m";
      
        ResultSet r;
		conn = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",""+username+"",""+password);//Connect PostgreSQL
	    stm = conn.createStatement();
	    
		try {
			if(conn != null)
			{
				System.out.println("JobForYou 서비스를 시작합니다.\n"); //Success to Connect
			}
			else System.out.println("Connection failed\n"); //Fail to Connect
		
        Scanner scan = new Scanner(System.in);
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* 저희 서비스는 당신의 나이,관심사,지역 정보에 맞추어 *\n"
						 + "* 취업 지원 정책 및,채용 행사 정보들을 제공해 줍니다. *\n"
						 + "* 채용 행사의 경우,각 행사마다  상세정보를 제공합니다.*\n*"
						 + "*************************************\n");
		
		int flag = 1;
		int choice = 0; //for Start Service
		int sltNum = 0; //for menu;
		String name = null;
		int age = 0;
		String eQuery = null;
		String getInfoQuery = null;
		
		System.out.println("서비스를 이용하실건가요? 알맞은 번호를 입력해주세요!\n" //Question to User(Continue or Quit)
				+ "이용할 것이다. ->숫자 1을 입력해주세요!\n"
				+ "종료할 것이다. ->숫자 2를 입력해주세요!");
			
		choice = scan.nextInt();
		scan.nextLine();
		
		if(choice == 2)  //When User want to stop Using Service
			Quit();
		
		else if(choice == 1) //When User want to use our Service
		{
			System.out.println("\n저희 서비스를 이용해보셨나요? 알맞은 번호를 입력해주세요!\n" //Question to User(Have been Used or New)
				+ "사용해봤다. ->숫자 1을 입력해주세요!\n" 
				+ "처음 이용해본다. ->숫자 2를 입력해주세요!");
			choice = scan.nextInt();
			scan.nextLine();
			
			if(choice == 1) //User is member of our Service
			{	
				email = getInfoString("\n로그인을 위한 이메일을 입력해 주세요!");		
				eQuery = "select name from Student where email = '" + email + "'";
				r = getQuery(eQuery);
				
				while(r.next())
				{ System.out.println ("다시 만나서 반갑습니다 "+r.getString(1)+"님! 오늘도 좋은 정보 얻고 가세요 :)"); }
				
				getInfoQuery = "select * from Student where Student.email ='"+email+"'";
				r = getQuery(getInfoQuery);
				
				while(r.next()) 
				{
					email = r.getString(1);
					interestCode = r.getString(2);
					areaCode = r.getInt(3);
					name = r.getString(4);
					age = r.getInt(5);
				}
				
				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,name,interestCode,email,areaCode,flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
			
			else if(choice == 2) //User is new Member
			{
				Area[] areaData = new Area[6];
				areaData[0] = new Area(51, "서울, 강원");
				areaData[1] = new Area(52, "부산, 경남");
				areaData[2] = new Area(53, "대구, 경북");
				areaData[3] = new Area(54, "경기, 인천");
				areaData[4] = new Area(55,"광주, 전라, 제주" );
				areaData[5] = new Area(56, "대전, 충청");
				
				Interest[] interestData = new Interest[16];
				interestData[0] = new Interest("PLCYTP01", "취업지원");
				interestData[1] = new Interest("PLCYTP010001", "교육훈련·체험·인턴");
				interestData[2] = new Interest("PLCYTP010002", "전문분야 취업지원");
				interestData[3] = new Interest("PLCYTP010003", "중소기업 취업지원");
				interestData[4] = new Interest("PLCYTP010004", "해외진출");
				interestData[5] = new Interest("PLCYTP02", "창업");
				interestData[6] = new Interest("PLCYTP020001", "R&D 지원");
				interestData[7] = new Interest("PLCYTP020002", "경영 지원");
				interestData[8] = new Interest("PLCYTP020003", "자본금 지원");
				interestData[9] = new Interest("PLCYTP03", "생활·복지");
				interestData[10] = new Interest("PLCYTP030001", "건강");
				interestData[11] = new Interest("PLCYTP030002", "문화");
				interestData[12] = new Interest("PLCYTP04", "주거·금융");
				interestData[13] = new Interest("PLCYTP040001", "생활비 지원 및 금융 혜택");
				interestData[14] = new Interest("PLCYTP040002", "주거 지원");
				interestData[15] = new Interest("PLCYTP040003", "학자금 지원");
				name = getInfoString("\n회원 가입을 시작하겠습니다.\n이름을 입력해주세요");
				age = getInfoInt("\n만 나이를 숫자로 입력해주세요.");
				email = getInfoString("\n로그인을 위해 사용할 이메일을 입력해주세요");
				int areaChoice = displayAreaList();
				areaCode = areaData[areaChoice - 1].areaCode;
				int interestChoice = displayInterestList();
				interestCode = interestData[interestChoice - 1].interestCode;
				String SignUpQuery = "insert into Student values ('" 
						+ email 
						+ "','"
						+ interestCode
						+ "','"
						+ areaCode
						+ "','"
						+ name
						+ "','"
						+ age
						+"')";
				stm.executeUpdate(SignUpQuery); // insert query for create new Student

				// Login
	            getInfoQuery = "select * from Student where Student.email ='"+email+"'";
	            r = getQuery(getInfoQuery);

	            while(r.next()) 
	            {
	               email = r.getString(1);
	               interestCode = r.getString(2);
	               areaCode = r.getInt(3);
	               name = r.getString(4);
	               age = r.getInt(5);
	            }

				System.out.println("JobForYou 회원가입이 완료되었습니다!");

				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,name,interestCode,email,areaCode,flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
		}
		
	}catch (SQLException ex) 
	{ throw ex; }
}

	static class Area{
		int areaCode;
		String area;
		public Area(int areaCode, String area) {
			this.areaCode = areaCode;
			this.area = area;
		}
	}
	
	static class Interest{
		String interestCode;
		String interest;
		public Interest(String interestCode, String interest) {
			this.interestCode = interestCode;
			this.interest = interest;
		}
	}
}

