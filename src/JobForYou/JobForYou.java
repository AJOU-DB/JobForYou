/* 
 * Ajou University 2020-2 Data Base Team Project 
 * Jinyoun Song, Hyungeong Park, SeungHun Han
 * 사용자의 정보에 적합한 취업 지원 정책 및 채용 행사 목록을 제공하는 서비스 개발 
 * JobForYou(잡뽀유)
 */

package JobForYou;

import java.util.*;

public class JobForYou {
	
	public static void menu() //Show Menu List
	{
		System.out.println("\nJobforYou 서비스는 청년취업정책 정보와 채용행사 정보를 제공하고 있습니다.\n"
				+ "이용하실 서비스 번호를 입력해주세요.\n"
				+ "1. 정책 정보 열람\n"
				+"2. 채용 행사 정보 열람\n"
				+"3. 정기 구독 신청\n" 
				+"4. 서비스 종료");
	}
	
	public static void CorQ() // Continue or Quit
	{
		System.out.println("\n다른 서비스를 이용하시겠습니까? 원하는 번호를 입력해주세요!\n"
				+ "1. 서비스 선택 페이지\n"
				+"2. 서비스 종료\n");
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
				+"2. 부산, 경남\n"
				+"3. 대구, 경북\n"
				+ "4. 경기, 인천\n"
				+"5. 광주, 전라, 제주\n"
				+"6 대전, 충청");
		Scanner scan = new Scanner(System.in);
		int areaCode = scan.nextInt();
		return areaCode;
	}
	
	public static int displayInterestList() // display List of Interest
	{
		System.out.println("\n관심 분야 코드를 입력해주세요.\n(ex. 관심 분야가 '취업지원'이면, 1만 입력해주세요.)\n"
				+ "1. 취업지원\n"
				+"2. 교육훈련·체험·인턴\n"
				+"3. 전문분야 취업지원\n"
				+ "4. 중소기업 취업지원\n"
				+"5. 해외진출\n"
				+"6. 창업\n"
				+ "7. R&D 지원\n"
				+"8. 경영 지원\n"
				+ "9. 자본금 지원\n"
				+"10. 생활·복지\n"
				+"11. 건강\n"
				+ "12. 문화\n"
				+"13. 주거·금융\n"
				+"14. 생활비 지원 및 금융 혜택\n"
				+ "15. 주거 지원\n"
				+"16. 학자금 지원\n");
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

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		System.out.println("**************JobForYou**************"); //Service Introduction
		System.out.println("* 저희 서비스는 당신의 나이,관심사,지역 정보에 맞추어 *\n"
						 + "* 취업 지원 정책 및,채용 행사 정보들을 제공해 줍니다. *\n"
						 + "* 채용 행사의 경우,각 행사마다  상세정보를 제공합니다.*\n*"
						 + "*************************************\n");
		Scanner scan = new Scanner(System.in);
		int choice = 0; //for Start Service
		int sltNum = 0; //for Menu
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
				System.out.println("로그인을 위한 이메일을 입력해 주세요!\n");
				email = scan.nextLine();
				System.out.println("다시 만나서 반갑습니다"+email+"님! 오늘도 좋은 정보 얻고 가세요 :)");
				
				menu();
				sltNum = scan.nextInt();
				
				switch(sltNum) 
				{
					case 1:
						
				}
			}
			
			else if(choice == 2) //User is new Member
			{
				String name = getInfoString("회원 가입을 시작하겠습니다.\n이름을 입력해주세요\n");
				int age = getInfoInt("만 나이를 숫자로 입력해주세요.\n");
				email = getInfoString("로그인을 위해 사용할 이메일을 입력해주세요\n");
				int areaCode = displayAreaList(); // area 정보 추가하기.
				int interestCode = displayInterestList(); // interest 정보 추가하기.
				// create new Student
//				insert into Student values (email,interestCode,areaCode,name,age,area,interest,isHired); 
				System.out.println("JobForYou 회원가입이 완료되었습니다.");
				menu();
				sltNum = scan.nextInt();
				scan.nextLine();
				
			}
		}
		
		
		

	}
}
