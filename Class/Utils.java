package SalaryChecker.Class;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Utils {

	public static String ANSI_GREEN="\033[32m";
	public static String ANSI_RED="\033[91m";
	public static String ANSI_YELLOW="\033[93m";
	public static String ANSI_WHITE="\033[37m";
	public static String ANSI_GRASS="\033[92m";
	/*public static String ANSI_BG_GREEN="\033[42m";
	public static String ANSI_BG_RED="\033[41m";
	public static String RESET="\033[0m";*/
	
	
	public static void intro() {
		System.out.println(Utils.ANSI_GREEN);
		System.out.println("	 #  #     #     #####    #####  ##### ");
		System.out.println("	 # #     # #    #        #      #   # ");
		System.out.println("	 ##     #####   ##### -- #####  #     ");
		System.out.println("	 # #   #     #  #            #  #   # ");
		System.out.println("	 #  # #       # #####    #####  ##### ");
		System.out.println(Utils.ANSI_YELLOW+" 	KEEP AN EYE ON IT: SALARY CHECKER"+Utils.ANSI_WHITE+" 	v 1.1");
	}

	public static void menu() {
		System.out.println("\n"+Utils.ANSI_YELLOW+"  1]"+Utils.ANSI_WHITE+"  Add Month Salary");
		System.out.println(Utils.ANSI_YELLOW+"  2]"+Utils.ANSI_WHITE+"  Add an expense");
		System.out.println(Utils.ANSI_YELLOW+"  3]"+Utils.ANSI_WHITE+"  Remove an expense");
		System.out.println(Utils.ANSI_YELLOW+"  4]"+Utils.ANSI_WHITE+"  Print all the Years");
		System.out.println(Utils.ANSI_YELLOW+"  5]"+Utils.ANSI_WHITE+"  Print an Year");
		System.out.println(Utils.ANSI_YELLOW+"  6]"+Utils.ANSI_WHITE+"  Load from txt file (overwrites actual data)");
		System.out.println(Utils.ANSI_YELLOW+"  7]"+Utils.ANSI_WHITE+"  Save data into txt");
		System.out.println(Utils.ANSI_YELLOW+"  9]"+Utils.ANSI_WHITE+"  Menu\n");


	}
	
	
	/*
	 * Given a String month, it returns an integer that indicates which month it is
	 * Thus helping to access the arraylist (0 is January, 1 February , ...)
	 * 	So i need to do Integer_returned - 1 to access the correct position in the ArrayList.
	 * 
	 * It returns -1 in case an error occured, so in the main i can reask the month until this function returns a value greater than 1.
	 * 
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
					System.out.println("	This month is not an option.\n");
					return -1;
		}
	}

	public static String convertDecimalFormat(Double input) {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		return numberFormat.format(input);
	}
	
	public static void addSalary(ArrayList<Year> YearsList) {
		System.out.println("\n  >>     Add Salary: ");
		Double income;
		do {
			System.out.print("  >>>>>>>> Income:    ");
			Scanner scanner_income_1 = new Scanner(System.in);
	 		income= scanner_income_1.nextDouble();
	 		if(income<0) {//launch exception !!
	 			System.out.println("	Input income is not valid.");
	 		}
			//scanner_income_1.close();
		}while(income<0);
 		int ret_value=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scanner_income_2 = new Scanner(System.in);
	 		String month= scanner_income_2.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		if(ret_value != -1) {
	 			Scanner scanner_income_3 = new Scanner(System.in);
	 			System.out.print("  >>>>>>> Year:    ");
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
 			System.out.println(">>>>>>>> Month:    ");
			Scanner scanner_income_2 = new Scanner(System.in);
	 		String month= scanner_income_2.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//ret_value (key) is used to remove the month (value) in the year hashmap
	 		if(ret_value != -1) {
	 			Scanner scanner_income_3 = new Scanner(System.in);
	 			System.out.println("  >>>>>>> Year:    ");
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
		System.out.println("\n  >>     Add Expense");
		Double expense;
		do {
			System.out.print("\n  >>>>>>>>> Expense:    ");
			Scanner scanner_outcome_1=new Scanner(System.in);
			expense = scanner_outcome_1.nextDouble();
			if(expense<0) {
	 			System.out.println("	Input expense is not valid.");
			}
		}while(expense<=0);
 		
		System.out.print("  >>>>>>>>> Description:    ");
		Scanner scanner_outcome_2=new Scanner(System.in);
		String description = scanner_outcome_2.nextLine();
		
		//int ret_value_month_to_pick=0;
		int ret_value=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scanner_outcome_3 = new Scanner(System.in);
	 		String month= scanner_outcome_3.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(ret_value != -1) {
	 			Scanner scanner_outcome_4 = new Scanner(System.in);
	 			System.out.print("  >>>>>>> Year:    ");
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
		System.out.println("\n  >>     Remove an expense:");
		Integer id;
		do {
			System.out.print("  >>>>>>>>> Expense ID:    ");
			Scanner scanner_outcome_1=new Scanner(System.in);
			id = Integer.parseInt(scanner_outcome_1.next());
			if(id<0) {
	 			System.out.println("	Input expense is not valid.");
			}
		}while(id<=0);
 		
		//int ret_value_month_to_pick=0;
		int ret_value=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scanner_outcome_3 = new Scanner(System.in);
	 		String month= scanner_outcome_3.nextLine();
	 		month=month.toUpperCase();
	 		ret_value=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(ret_value != -1) {
	 			Scanner scanner_outcome_4 = new Scanner(System.in);
	 			System.out.print("  >>>>>>> Year:    ");
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
		Double years_income = 0.00;
		Double years_outcome = 0.00;
		Double years_gain = 0.00;
		for(Year y_print : YearsList) {
			years_income += y_print.getTotIncome();
			years_outcome += y_print.getTotOutcome();
		}
		years_gain=years_income - years_outcome;
		String s_years_income = Utils.convertDecimalFormat(years_income);
		String s_years_outcome = Utils.convertDecimalFormat(years_outcome);
		String s_years_gain= Utils.convertDecimalFormat(years_gain);
		
		System.out.println(" --> 	$   	"+Utils.ANSI_GREEN+s_years_income+"      "+Utils.ANSI_RED+s_years_outcome+Utils.ANSI_YELLOW+"       ++ "+s_years_gain+Utils.ANSI_WHITE+"\n");
		
		for(Year y_print : YearsList) {
			String year_income = Utils.convertDecimalFormat(y_print.getTotIncome());
			/*String year_outcome=null;
			if(Double.compare(y_print.getTotOutcome(),0)==0) {
				System.out.println("GOTCHA!");
				year_outcome = "0";
			}else {
				year_outcome = Utils.convertDecimalFormat(y_print.getTotOutcome());
			}*/
			String year_outcome = Utils.convertDecimalFormat(y_print.getTotOutcome());
			String gain= Utils.convertDecimalFormat(y_print.getTotIncome()-y_print.getTotOutcome());
			System.out.print("\n # "+Utils.ANSI_YELLOW+y_print.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________ "
					+Utils.ANSI_GREEN+"+"+year_income+Utils.ANSI_RED+" -"+year_outcome+Utils.ANSI_GRASS+" 	++ "+gain+"\n");
			Collection<Salary> salaries = y_print.getMonths().values();
			for(Salary s: salaries) { 	
				s.printSalary(); 	
			}
		}
	}
	
	public static ArrayList<Year> loadData(ArrayList<Year> YearsList) {
		FileHandler fw=new FileHandler();
		System.out.println("  Loading file {"+fw.getMyFile()+"} ...");
		FileHandler fr=new FileHandler();
		YearsList = fr.readingFile();
		System.out.println("\n  Data "+Utils.ANSI_GREEN+"loaded."+Utils.ANSI_WHITE);
		return YearsList;
	}

	public static void saveData(ArrayList<Year> YearsList) {
		FileHandler fw=new FileHandler();
		System.out.println("  Writing in file {"+fw.getMyFile()+"} ...");
		fw.writingFile(YearsList);
		System.out.println("\n  Data "+Utils.ANSI_GREEN+"saved."+Utils.ANSI_WHITE);
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
					System.out.print("\n # "+Utils.ANSI_YELLOW+curr_y.getYear()+
							Utils.ANSI_WHITE+" \\________________________________________________________________________\n");
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

	public static void showStats(ArrayList<Year> yearsList) {
		
	}
	
}
