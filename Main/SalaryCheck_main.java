package SalaryChecker.Main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import SalaryChecker.Class.FileHandler;
import SalaryChecker.Class.Salary;

public class SalaryCheck_main {

	
	
	public static void intro() {
		final String ANSI_GREEN="\033[92m";
		final String ANSI_WHITE="\033[37m";

		System.out.println(ANSI_GREEN);
		System.out.println(" *  *     *     *****    *****  ***** ");
		System.out.println(" * *     * *    *        *      *   * ");
		System.out.println(" **     *****   ***** -- *****  *     ");
		System.out.println(" * *   *     *  *            *  *   * ");
		System.out.println(" *  * *       * *****    *****  ***** ");
		System.out.println(ANSI_WHITE);
	}
	
	public static void main(String[] args) {
//		final String ANSI_GREEN="\u001b[32m";
//		final String ANSI_RED="\u001b[31m";		
//		final String ANSI_WHITE="\u001b[37m";
		
		final String ANSI_GREEN="\033[92m";
		final String ANSI_RED="\033[31m";
		final String ANSI_YELLOW="\033[33m";
		final String ANSI_WHITE="\033[37m";
		final String ANSI_MGNT="\033[36m";
		
		intro();
		System.out.println(ANSI_GREEN+" KEEP AN EYE ON IT: SALARY CHECKER"+ANSI_WHITE+" v 1.1");
				
		HashMap<Integer,Salary> Salary_2021 = new HashMap<>(12);

		menu();
		int scelta=0;
		FileHandler fw=new FileHandler(); //used for menu read and write, option 6 and 7
		do {
			System.out.print("\nScelta >> ");
			Scanner scanner_scelta = new Scanner(System.in);
			scelta=scanner_scelta.nextInt();
			switch(scelta) {
				case 1:
					System.out.println("\nAdd Salary: ");
					Double income;
					do {
						System.out.print(">>>>>>>> Income:    ");
						Scanner scanner_income_1 = new Scanner(System.in);
				 		income= scanner_income_1.nextDouble();
				 		if(income<0) {//launch exception !!
				 			System.out.println("!!! Input income is not valid.");
				 		}
					}while(income<0);
					
			 		int ret_value=0;
			 		do {
			 			System.out.print(">>>>>>>> Month:    ");
						Scanner scanner_income_2 = new Scanner(System.in);
				 		String month= scanner_income_2.nextLine();
				 		month=month.toUpperCase();
				 		ret_value=MonthToInt(month);
				 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
				 		if(ret_value != -1) {
				 			Salary salary = new Salary(income,month);
				 			Salary_2021.put(ret_value-1, salary); 
				 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
				 			//it's critical to subtract -1 from ret value now because it may broke the do while
				 		}
			 		}while(ret_value==-1); //if error, prompt again the value
					
					break;
				case 2:
					System.out.println("\nAdd Expense");
					Double expense;
					do {
						System.out.print("\n>>>>>>>>> Expense:    ");
						Scanner scanner_outcome_1=new Scanner(System.in);
						expense = scanner_outcome_1.nextDouble();
						if(expense<0) {
				 			System.out.println("!!! Input expense is not valid.");
						}
					}while(expense<=0);
			 		
					System.out.print(">>>>>>>>> Description:    ");
					Scanner scanner_outcome_2=new Scanner(System.in);
					String description = scanner_outcome_2.nextLine();
					
					//int ret_value_month_to_pick=0;
					ret_value=0;
			 		do {
			 			System.out.print(">>>>>>>> Month:    ");
						Scanner scanner_outcome_3 = new Scanner(System.in);
				 		String month= scanner_outcome_3.nextLine();
				 		month=month.toUpperCase();
				 		ret_value=MonthToInt(month);
				 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
				 		if(ret_value != -1) {
				 			Salary salary = Salary_2021.get(ret_value-1); 
				 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
				 			salary.setSingle_outcome(expense, description);
				 			//it's critical to subtract -1 from ret value now because it may broke the do while
				 		}
			 		}while(ret_value==-1); //if error, prompt again the value
					break;
				case 3:
					Collection<Salary> salaries = Salary_2021.values();
					for(Salary s: salaries) { 	s.printSalary(); 	}
					break;
				case 4: 
					menu();
					break;
				case 5:
					System.out.println("Loading file {"+fw.getMyFile()+"} ...");
					FileHandler fr=new FileHandler();
					Salary_2021=fr.readingFile();
					Collection<Salary> salarieses = Salary_2021.values();
					for(Salary s: salarieses) { 	s.printSalary(); 	}
					System.out.println("\n-- Data loaded.");
					break;
				case 6:
					System.out.println("Writes in file");
					fw.writingHashMap(Salary_2021);
					System.out.println("\n-- Data saved into: {"+fw.getMyFile()+"}");
			}
		}while(scelta!=0);
	}
}
