package SalaryChecker.Main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import SalaryChecker.Class.FileHandler;
import SalaryChecker.Class.Salary;

public class SalaryCheck_main {

	/*
	 * Given a String month, it returns an integer that indicates which month it is
	 * Thus helping to access the arraylist (0 is January, 1 February , ...)
	 * 	So i need to do Integer_returned - 1 to access the correct position in the ArrayList.
	 * 
	 * It returns -1 in case an error occured, so in the main i can reask the month until this function returns a value greater than 1.
	 * 
	 * It's static because is called from the main which is static too.
	 */
	
	public static Integer MonthToInt(String month) {
		switch(month) {
			case "JANUARY":
				return 1;
			case "FEBRUARY":
					return 2;
			case "MARCH":
					return 3;
			case "APRIL":
					return 4;
			case "MAY":
					return 5;
			case "JUNE":
					return 6;
			case "JULY":
					return 7;
			case "AUGUST":
					return 8;
			case "SEPTEMBER":
					return 9;
			case "OCTOBER":
					return 10;
			case "NOVEMBER":
					return 11;
			case "DECEMBER":
					return 12;
			default:
					System.out.println("!!! This month is not an option. The format is (January, February, March, ...)\n");
					return -1;
		}
	}
	
	public static void menu() {
		System.out.println("\n-----------------------------\n1]Add Salary");
		System.out.println("2]Add expense");
		System.out.println("3]Print");
		System.out.println("4]Menu");
		System.out.println("5]Load from txt file (overwrites actual data)");
		System.out.println("6]Save data into txt\n-----------------------------\n");
	}
	
	public static void main(String[] args) {
		final String ANSI_GREEN="\u001b[32m";
		final String ANSI_RED="\u001b[31m";		
		final String ANSI_WHITE="\u001b[37m";

		System.out.println(" ****** "+ANSI_GREEN+"SALARY CHECKER"+ANSI_WHITE+" ******");
				
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
					System.out.print(">>>>>>>> Income:    ");
					Scanner scanner_income_1 = new Scanner(System.in);
			 		Double income= scanner_income_1.nextDouble();
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
			 		System.out.print("\n>>>>>>>>> Expense:    ");
					Scanner scanner_outcome_1=new Scanner(System.in);
					Double expense = scanner_outcome_1.nextDouble();
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
					System.out.println("calling readingFile()");
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
