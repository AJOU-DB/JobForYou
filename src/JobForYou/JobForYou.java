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
				scan.nextLine();
				
				switch(sltNum) 
				{
					case 1:
						
				}
			}
			
			else if(choice == 2) //User is new Member
			{
				
			}
		}
		
		
		

	}
}
