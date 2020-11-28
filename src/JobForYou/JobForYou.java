/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * 사용자의 정보에 적합한 취업 지원 정책 및 채용 행사 목록을 제공하는 서비스 개발 
 * JobForYou(잡뽀유)
 */

package JobForYou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

public class JobForYou {
	
	int flag = 1;
	String url = "";
    String user = "postgres";
    String password = "";
    Connection conn;
    Statement st;
    ResultSet rs;
    
//	public static void connectDatabase() // Connect Database
//	{
//        conn = DriverManager.getConnection(url, user, password);
//        st = conn.createStatement();
//        st.executeUpdate(""); // create table
//        st.executeUpdate(""); // insert value 
//	}
//	
//	public static void getQuery()
//	{
//		rs = st.executeQuery(""); // insert query
//	}
	
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
	
	public static int services(int sltNum,int age,int interestCode,int areaCode,int eventNo, int flag)
	{
		switch(sltNum) 
		{
			case 1: flag = policyList(interestCode, age); //정책 정보 열람
				break;
				
			case 2: flag = recruitmentEventList(areaCode); //채용행사 정보 열람
				break;
				
			case 3: flag = subscribe();//정기 구독 신청
				break;
				
			case 4 : flag = 2;
		}
		return flag;
	}

	public static int policyList(int interestCode, int age) //Display PolicyList
	{
		/*select *
		from jobPolicy
		where interestCode = ‘interestCode’ and startAge <= age and endAge >= age;*/
		return CorQ();
	}
	
	public static int recruitmentEventList(int areaCode) // Display RecruitmentEventList
	{
//		select *
//		from RecruitmentEvent
//		where areaCode = 51;
		int choice = getInfoInt("\n다른 서비스를 이용하시겠습니까? 원하는 번호를 입력해주세요!\n"
				+ "1. 채용행사 상세정보 보기\n"
				+ "2. 서비스 선택 페이지\n"
				+ "3. 서비스 종료");
		if (choice == 1) {
			int eventNoValue = getInfoInt("\n상세정보를 확인하고 싶은 채용행사 번호를 입력해주세요!\n");
			return recruitmentEventDetail(eventNoValue);
		} else {
			return choice == 2 ? 1 : 2;
		}

	}
	
	public static int recruitmentEventDetail(int eventNoValue) // Display RecruitmentEventDetail
	{
//		select distinct *
//		from RecruitmentEventDetail join RecruitmentEvent Using(eventNo)
//		where eventNo = eventNoValue
		System.out.print("\n채용행사 상세정보 입니다.\n");
		return CorQ();
	}
	
	
	public static int subscribe() //Regular Mail Subscription
	{
		System.out.print("\n정기 메일 수신을 신청해 주셔서 감사합니다.\n"
				+ "청년취업정보와 채용행사 정보를 정기적으로 받아보실 수 있습니다!\n");
		Scanner scan = new Scanner(System.in);
		String email = getInfoString("아래에  메일 수신하실 메일 주소를 입력해주세요.");
		System.out.print("\n정기 메일 수신 신청이 완료되었습니다.\n"
				+ "확인 메일을 보내드렸으니 메일함을 확인해 보세요!\n");
		return CorQ();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// connect Database
		
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* 저희 서비스는 당신의 나이,관심사,지역 정보에 맞추어 *\n"
						 + "* 취업 지원 정책 및,채용 행사 정보들을 제공해 줍니다. *\n"
						 + "* 채용 행사의 경우,각 행사마다  상세정보를 제공합니다.*\n*"
						 + "*************************************\n");
		Scanner scan = new Scanner(System.in);
		int flag = 1;
		int choice = 0; //for Start Service
		int sltNum = 0; //for menu;
		int age = 0;
		int areaCode = 0;
		int interestCode = 0;
		int eventNo = 0;
		String email = null;
		
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
				System.out.println("다시 만나서 반갑습니다"+email+"님! 오늘도 좋은 정보 얻고 가세요 :)");
				
				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,age,interestCode,areaCode,eventNo, flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
			
			else if(choice == 2) //User is new Member
			{
				String name = getInfoString("\n회원 가입을 시작하겠습니다.\n이름을 입력해주세요");
				age = getInfoInt("\n만 나이를 숫자로 입력해주세요.");
				email = getInfoString("\n로그인을 위해 사용할 이메일을 입력해주세요");
				areaCode = displayAreaList(); // add area Info
				interestCode = displayInterestList(); // Add Interest Info
				// create new Student
//				insert into Student values (email,interestCode,areaCode,name,age,area,interest,isHired); 
				System.out.println("JobForYou 회원가입이 완료되었습니다.");

				while(flag == 1) //if flag == 1, repeat service
				{
					sltNum = menu();
					flag = services(sltNum,age,interestCode,areaCode,eventNo, flag);
				}
				Quit(); //flag is 2, so User want to quit.
			}
		}
	}
}
