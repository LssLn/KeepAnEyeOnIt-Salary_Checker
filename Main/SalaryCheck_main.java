package SalaryChecker.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import SalaryChecker.Class.FileHandler;
import SalaryChecker.Class.Salary;
import SalaryChecker.Class.Utils;
import SalaryChecker.Class.Year;

public class SalaryCheck_main {

	public static void addSalary(ArrayList<Year> YearsList) {
		System.out.println("\nAdd Salary: ");
		Double income;
		do {
			System.out.print(">>>>>>>> Income:    ");
			Scanner scanner_income_1 = new Scanner(System.in);
	 		income= scanner_income_1.nextDouble();
	 		if(income<0) {//launch exception !!
	 			System.out.println("	Input income is not valid.");
	 		}
			//scanner_income_1.close();
		}while(income<0);
 		int ret_value=0;
 		do {
 			System.out.print(">>>>>>>> Month:    ");
			Scanner scanner_income_2 = new Scanner(System.in);
	 		String month= scanner_income_2.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		if(ret_value != -1) {
	 			Scanner scanner_income_3 = new Scanner(System.in);
	 			System.out.print(">>>>>>> Year:    ");
	 			String year_input = scanner_income_3.nextLine();
	 			Salary salary = new Salary(income,month);
	 			//given the year, the YearsList is cycled
	 			Boolean year_found=false;
	 			for(Year curr_y:YearsList) {
	 				if(curr_y.getYear().equals(year_input)) {
	 					//the year already exists, then it's just an add
	 					curr_y.addMonth(ret_value-1,salary);
	 					year_found=true;
	 				}
	 			}
	 			if(year_found==false) {
	 				//year not yet existing
	 				//year creation
	 				Year year_new = new Year(year_input);
	 				year_new.addMonth(ret_value-1,salary);
	 				//Year is added to YearsList
	 				YearsList.add(year_new);
					//List is sorted for CompareTo (in Year class) setted value (String year)
	 				YearsList.sort(null);
	 			}
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			//it's critical to subtract -1 from ret value now because it may broke the do while
	 		}
 		}while(ret_value==-1); //if error, prompts again the value
		
	}
	
	public static void removeSalary(ArrayList<Year> YearsList) {
		//I check if the year exists, if then Year.remove(ret_value). 
		//Then we check the array_existing_months (Yet to be implemented)
		System.out.println("\n  Remove Salary: ");
		
 		int ret_value=0;
 		do {
 			System.out.print(">>>>>>>> Month:    ");
			Scanner scanner_income_2 = new Scanner(System.in);
	 		String month= scanner_income_2.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//ret_value (key) is used to remove the month (value) in the year hashmap
	 		if(ret_value != -1) {
	 			Scanner scanner_income_3 = new Scanner(System.in);
	 			System.out.print(">>>>>>> Year:    ");
	 			String year_input = scanner_income_3.nextLine();
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean year_found=false;
	 			for(Year curr_y:YearsList) {
	 				if(curr_y.getYear().equals(year_input)) {
	 					year_found=true;
	 					//the year exists, then we try to remove the month (Value)
	 					
	 					/*
	 					 * array_existing_month check
	 					 * 
	 					 * */
	 					
	 					curr_y.removeMonth(ret_value-1);
	 					
	 				}
	 			}
	 			if(year_found==false) {
	 				//year not existing
	 				System.out.println(" The selected year does not exist, thus neither the month.");
	 			}
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			//it's critical to subtract -1 from ret value now because it may broke the do while
	 		}
 		}while(ret_value==-1); //if error, prompts again the value
		
	}
	
	public static void addExpense(ArrayList<Year> YearsList) {
		System.out.println("\nAdd Expense");
		Double expense;
		do {
			System.out.print("\n>>>>>>>>> Expense:    ");
			Scanner scanner_outcome_1=new Scanner(System.in);
			expense = scanner_outcome_1.nextDouble();
			if(expense<0) {
	 			System.out.println("	Input expense is not valid.");
			}
		}while(expense<=0);
 		
		System.out.print(">>>>>>>>> Description:    ");
		Scanner scanner_outcome_2=new Scanner(System.in);
		String description = scanner_outcome_2.nextLine();
		
		//int ret_value_month_to_pick=0;
		int ret_value=0;
 		do {
 			System.out.print(">>>>>>>> Month:    ");
			Scanner scanner_outcome_3 = new Scanner(System.in);
	 		String month= scanner_outcome_3.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(ret_value != -1) {
	 			Scanner scanner_outcome_4 = new Scanner(System.in);
	 			System.out.print(">>>>>>> Year:    ");
	 			String year_input = scanner_outcome_4.nextLine();
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean year_found=false;
	 			for(Year curr_y:YearsList) {
	 				if(curr_y.getYear().equals(year_input)) {
	 					year_found=true;
	 					//the year already exists
	 					//if the month exists, we add the outcome. 
	 					// **** NB :::: If the above doesn't work, 
	 					// 				Do the check with the array created to solve the "Expense added to a non existent month"
	 					Salary s = curr_y.getMonths().get(ret_value-1); //check if this does the intended aka Salary salary = Salary_2021.get(ret_value-1); 

	 					if(s!=null) {
	 						//months exists
	 						s.setSingle_outcome(expense, description);
		 					
	 					}else {
	 						// add exception
	 						System.out.println("	No month exists in this Year for this expense to be added in.\n"
	 								+ "		Create the salary for "+month+" "+year_input);
	 					}
	 					
	 				}
	 			}
	 			if(year_found == false) {
	 				// year was not found. This means no month exists for that Year.
	 				//launch Exception
	 				System.out.println("	No Year exists for this expense to be added in, which means any month.\n"
								+ "		Create the salary for "+month+" "+year_input);
	 			}
	 			
	 			
	 		}
 		}while(ret_value==-1); //if error, prompt again the value
	}
	
	public static void removeExpense(ArrayList<Year> YearsList) {
		System.out.println("\nAdd Expense");
		Integer id;
		do {
			System.out.print("  Insert the expense ID, month and year:\\n>>>>>>>>> ID:    ");
			Scanner scanner_outcome_1=new Scanner(System.in);
			id = Integer.parseInt(scanner_outcome_1.next());
			if(id<0) {
	 			System.out.println("	Input expense is not valid.");
			}
		}while(id<=0);
 		
		//int ret_value_month_to_pick=0;
		int ret_value=0;
 		do {
 			System.out.print(">>>>>>>> Month:    ");
			Scanner scanner_outcome_3 = new Scanner(System.in);
	 		String month= scanner_outcome_3.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(ret_value != -1) {
	 			Scanner scanner_outcome_4 = new Scanner(System.in);
	 			System.out.print(">>>>>>> Year:    ");
	 			String year_input = scanner_outcome_4.nextLine();
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean year_found=false;
	 			for(Year curr_y:YearsList) {
	 				if(curr_y.getYear().equals(year_input)) {
	 					year_found=true;
	 					//the year  exists
	 					//if the month exists, we remove the outcome. 
	 					
	 					Salary s = curr_y.getMonths().get(ret_value-1); // aka Salary salary = Salary_2021.get(ret_value-1); 

	 					if(s!=null) {
	 						//months exists
	 						s.removeSingle_outcome(id);
		 					
	 					}else {
	 						// add exception
	 						System.out.println("	No month exist in this Year for the expense.");
	 					}
	 					
	 				}
	 			}
	 			if(year_found == false) {
	 				// year was not found. This means no month exists for that Year.
	 				//launch Exception
	 				System.out.println("	This Year doesn't exist.");
	 			}
	 			
	 			
	 		}
 		}while(ret_value==-1); //if error, prompt again the value
	}
	
	public static void printAll(ArrayList<Year> YearsList) {
		for(Year y_print : YearsList) {
			System.out.print("\n # "+Utils.ANSI_YELLOW+y_print.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________\n");
			Collection<Salary> salaries = y_print.getMonths().values();
			for(Salary s: salaries) { 	
				s.printSalary(); 	
			}
		}
	}
	
	public static ArrayList<Year> loadData(ArrayList<Year> YearsList) {
		FileHandler fw=new FileHandler();
		System.out.println("Loading file {"+fw.getMyFile()+"} ...");
		FileHandler fr=new FileHandler();
		YearsList = fr.readingFile();
		System.out.println("\nData "+Utils.ANSI_GREEN+"loaded."+Utils.ANSI_WHITE);
		return YearsList;
	}

	public static void saveData(ArrayList<Year> YearsList) {
		FileHandler fw=new FileHandler();
		System.out.println("Writing in file {"+fw.getMyFile()+"} ...");
		fw.writingFile(YearsList);
		System.out.println("\nData "+Utils.ANSI_GREEN+"saved."+Utils.ANSI_WHITE);
	}
	
	public static void printYear(ArrayList<Year> YearsList) {
		Scanner scanner = new Scanner(System.in);
		String year_sel = scanner.next();
		Boolean year_exist=false;
			for(Year curr_y:YearsList) {
				if(curr_y.getYear().equals(year_sel)) {
					//the year already exists
					year_exist=true;
					//print this year
					System.out.print("\n # "+Utils.ANSI_YELLOW+curr_y.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________\n");
					Collection<Salary> salaries = curr_y.getMonths().values();
					for(Salary s: salaries) { 	
						s.printSalary(); 	
					}
				}
			}
			if(year_exist==false) {
				System.out.println("  Year "+year_sel+" does not exist... nothing to see here!");
			}
	}
	
	public static void main(String[] args) {
		
		Utils.intro();
		ArrayList<Year> YearsList = new ArrayList<>();		
		Utils.menu();
		
		int scelta=0;
		do {
			System.out.print(Utils.ANSI_YELLOW+"\n >> "+Utils.ANSI_WHITE);
			Scanner scanner_scelta = new Scanner(System.in);
			scelta=scanner_scelta.nextInt();
			switch(scelta) {
				case 1:
					addSalary(YearsList);
					break;
				case 2:
					addExpense(YearsList);
					break;
				case 3:
					removeExpense(YearsList);
					saveData(YearsList);
					YearsList=loadData(YearsList);
					break;
				case 4:
					printAll(YearsList);
					break;
				case 5:
					printYear(YearsList);
					break;
				case 6:
					YearsList = loadData(YearsList);
					break;
				case 7:
					saveData(YearsList);
					break;
				case 9: 
					Utils.menu();
					break;
			}
		}while(scelta!=0);
	}
}
