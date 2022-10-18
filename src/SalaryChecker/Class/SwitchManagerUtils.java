package SalaryChecker.Class;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SwitchManagerUtils {
	static FileHandler fHUtil = new FileHandler();

	public static void addSalary(ArrayList<Year> yearsList) {
		System.out.println("\n  >>     Add Salary: ");
		String strIncome;
		Double income=0.0;
		boolean incomeValid=false;
		do {
			System.out.print("  >>>>>>>> Income:    ");
			Scanner scannerIncome1 = new Scanner(System.in);
			strIncome= scannerIncome1.next();
	 		//income validation
	 		if(Utils.doubleIsNumeric(strIncome) && !(Utils.hasComma(strIncome))) {
	 			income=Double.parseDouble(strIncome);
	 			if(income>=0) {
	 				//Ok
	 				incomeValid=true;
	 			}else{
	 				//TODO: launch exception + log
		 			System.out.println("	Input income is not valid.");
		 		}
	 		}else{
 				//TODO: launch exception + log
	 			System.out.println("	Input income is not valid.");
	 		}
			//scanner_income_1.close();
		}while(incomeValid == false);
 		int retValue=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scannerIncome2 = new Scanner(System.in);
	 		String month= scannerIncome2.nextLine();
	 		month=month.toUpperCase();
	 		retValue=Utils.MonthToInt(month);
	 		if(retValue != -1) {
	 			
	 			boolean yearValid=false;
	 			Integer year=0;
	 			String yearInput;
	 			do {
	 				System.out.print("  >>>>>>> Year:    ");
		 			Scanner scannerIncome3 = new Scanner(System.in);
		 			yearInput = scannerIncome3.nextLine();

		 			if(Utils.integerIsNumeric(yearInput) && !Utils.hasComma(yearInput) && !Utils.hasDot(yearInput)) {
		 				year = Integer.parseInt(yearInput);
		 				if(year>0 && year<9999) {
		 					yearValid=true;
		 				}else{
			 				//TODO: launch exception + log
				 			System.out.println("	Year is not valid.");
				 		}
		 			}else{
		 				//TODO: launch exception + log
			 			System.out.println("	Year is not valid.");
			 		}
	 			}while(yearValid == false);
	 			
	 			
	 			Salary salary = new Salary(income,month);
	 			//given the year, the YearsList is cycled
	 			Boolean yearFound=false;
	 			for(Year yCurr:yearsList) {
	 				if(yCurr.getYear().equals(yearInput)) {
	 					//the year already exists, then it's just an add
	 					yCurr.addMonth(retValue-1,salary);
	 					yearFound=true;
	 				}
	 			}
	 			if(yearFound==false) {
	 				//year not yet existing
	 				//year creation
	 				Year yearNew = new Year(yearInput);
	 				yearNew.addMonth(retValue-1,salary);
	 				//Year is added to YearsList
	 				yearsList.add(yearNew);
					//List is sorted for CompareTo (in Year class) setted value (String year)
	 				yearsList.sort(null);
	 			}
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			//it's critical to subtract -1 from ret value now because it may broke the do while
	 		}
 		}while(retValue==-1); //if error, prompts again the value
		
	}
	
	/*
	 * Given the year and the month,
	 * edits a salary income with a new value
	 */
	public static void modifySalary(ArrayList<Year> yearsList) {
		System.out.println("\n  >>     Edit a month salary:");
		int retValue=0;
		Boolean yearFound=false;
		do {
			Scanner scannerOutcome4 = new Scanner(System.in);
			System.out.print("  >>>>>>> Year:    ");
			String yearInput = scannerOutcome4.nextLine();
			
 			for(Year yCurr:yearsList) {
 				if(yCurr.getYear().equals(yearInput)) {
 					yearFound=true;
 					//the year  exists. If the month exists, remove the outcome. 
 					
 					//month input
 					do {
 			 			System.out.print("  >>>>>>>> Month:    ");
 						Scanner scannerOutcome3 = new Scanner(System.in);
 				 		String month= scannerOutcome3.nextLine();
 				 		month=month.toUpperCase();
 				 		retValue=Utils.MonthToInt(month);
	 					Salary s = yCurr.getMonths().get(retValue-1); // aka Salary salary = Salary_2021.get(ret_value-1); 
	
	 					if(s!=null) {
	 						//month exists
	 						
	 						//new value
	 						System.out.println("\n  >>     new Salary value: ");
	 						String strIncome;
	 						Double income=0.0;
	 						boolean incomeValid=false;
	 						do {
	 							System.out.print("  >>>>>>>> Income:    ");
	 							Scanner scannerIncome1 = new Scanner(System.in);
	 							strIncome= scannerIncome1.next();
	 					 		//income validation
	 					 		if(Utils.doubleIsNumeric(strIncome) && !(Utils.hasComma(strIncome))) {
	 					 			income=Double.parseDouble(strIncome);
	 					 			if(income>=0) {
	 					 				//Ok
	 					 				incomeValid=true;
	 					 				s.editSalary(income);
	 					 			}else{
	 					 				//TODO: launch exception + log
	 						 			System.out.println("	Input income is not valid.");
	 						 		}
	 					 		}else{
	 				 				//TODO: launch exception + log
	 					 			System.out.println("	Input income is not valid.");
	 					 		}
	 							//scanner_income_1.close();
	 						}while(incomeValid == false);
		 					
	 					}else {
	 						// add exception
	 						System.out.println("	No month exist in this Year for the expense.");
	 					}
 					}while(retValue==-1);
 				}
 			}
 			if(yearFound == false) {
 				// year was not found. This means no month exists for that Year.
 				//launch Exception
 				System.out.println("	This Year doesn't exist.");
 			}
		}while(yearFound==false);
	}
	
	public static void removeSalary(ArrayList<Year> yearsList) {
		//I check if the year exists, if then Year.remove(ret_value). 
		//Then we check the array_existing_months (Yet to be implemented)
		System.out.println("\n  Remove Salary: ");
		
 		int retValue=0;
 		do {
 			System.out.println(">>>>>>>> Month:    ");
			Scanner scannerIncome2 = new Scanner(System.in);
	 		String month= scannerIncome2.nextLine();
	 		month=month.toUpperCase();
	 		retValue=Utils.MonthToInt(month);
	 		//ret_value (key) is used to remove the month (value) in the year hashmap
	 		if(retValue != -1) {
	 			Scanner scannerIncome3 = new Scanner(System.in);
	 			System.out.println("  >>>>>>> Year:    ");
	 			String yearInput = scannerIncome3.nextLine();
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean yearFound=false;
	 			for(Year yCurr:yearsList) {
	 				if(yCurr.getYear().equals(yearInput)) {
	 					yearFound=true;
	 					//the year exists, then we try to remove the month (Value)
	 					
	 					/*
	 					 * array_existing_month check
	 					 * 
	 					 * */
	 					
	 					yCurr.removeMonth(retValue-1);
	 					
	 				}
	 			}
	 			if(yearFound==false) {
	 				//year not existing
	 				System.out.println(" The selected year does not exist, thus neither the month.");
	 			}
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			//it's critical to subtract -1 from ret value now because it may broke the do while
	 		}
 		}while(retValue==-1); //if error, prompts again the value
		
	}
	
	public static boolean addExpense(ArrayList<Year> yearsList, HashMap<String,String> categoriesMap) {
		boolean success=true;
		System.out.println("\n  >>     Add Expense");
		
		Double expense=0.0;
		String strExpense;
		boolean expenseValid=false;

		do {
			System.out.print("\n  >>>>>>>>> Expense:    ");
			Scanner scannerOutcome1=new Scanner(System.in);
			strExpense = scannerOutcome1.next();

			//income validation
	 		if(Utils.doubleIsNumeric(strExpense) && !(Utils.hasComma(strExpense))) {
	 			expense=Double.parseDouble(strExpense);
	 			if(expense>=0) {
	 				//Ok
	 				expenseValid=true;
	 			}else{
	 				//TODO: launch exception + log
		 			System.out.println("	Input expense is not valid.");
		 		}
	 		}else{
 				//TODO: launch exception + log
	 			System.out.println("	Input expense is not valid.");
	 		}
	 		
		}while(expenseValid == false);
 		
		System.out.print("  >>>>>>>>> Description:    ");
		Scanner scannerOutcome2=new Scanner(System.in);
		String description = scannerOutcome2.nextLine();
		
		Scanner scannerOutcome5=new Scanner(System.in);
		boolean categoryValid=false;
		String category="";
		do{
			System.out.print("  >>>>>>>>> Category:    ");
			category = scannerOutcome5.nextLine();
			if(!Utils.checkCategories(category)) {
				//category found, proceeding with other fields
				categoryValid=true;
			}else {
				//if the category does not exists (checkCategories returns false), the user can add it
				System.out.println("  				Category not found. You can add it now: ");
				categoriesMap = insertCategoriesIntoMap(categoriesMap,category);
				fHUtil.writeCategoriesToTXT(categoriesMap);
				categoryValid=true;
			}
		}while(categoryValid==false);		
		
		
		//int ret_value_month_to_pick=0;
		int retValue=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scannerOutcome3 = new Scanner(System.in);
	 		String month= scannerOutcome3.nextLine();
	 		month=month.toUpperCase();
	 		retValue=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(retValue != -1) {
	 			Scanner scannerOutcome4 = new Scanner(System.in);
	 			boolean yearValid=false;
	 			Integer year=0;
	 			String yearInput;
	 			do {
	 				System.out.print("  >>>>>>> Year:    ");
		 			yearInput = scannerOutcome4.next();
		 			if(Utils.integerIsNumeric(yearInput) && !Utils.hasComma(yearInput) && !Utils.hasDot(yearInput)) {
		 				year = Integer.parseInt(yearInput);
		 				if(year>0 && year<9999) {
		 					yearValid=true;
		 				}else{
			 				//TODO: launch exception + log
				 			System.out.println("	Year is not valid.");
				 		}
		 			}else{
		 				//TODO: launch exception + log
			 			System.out.println("	Year is not valid.");
			 		}
	 			}while(yearValid == false);
	 			
	 			
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean yearFound=false;
	 			for(Year yCurr:yearsList) {
	 				if(yCurr.getYear().equals(yearInput)) {
	 					yearFound=true;
	 					//the year already exists
	 					//if the month exists, we add the outcome. 
	 					// **** NB :::: If the above doesn't work, 
	 					// 				Do the check with the array created to solve the "Expense added to a non existent month"
	 					Salary s = yCurr.getMonths().get(retValue-1); //check if this does the intended aka Salary salary = Salary_2021.get(ret_value-1); 

	 					if(s!=null) {
	 						//months exists
	 						s.setSingleOutcome(expense, description,category);
		 					
	 					}else {
	 						// add exception
	 						System.out.println("	No month exists in this Year for this expense to be added in.\n"
	 								+ "		Create the salary for month: "+month+", year: "+yearInput);
	 					}
	 					
	 				}
	 			}
	 			if(yearFound == false) {
	 				// year was not found. This means no month exists for that Year.
	 				//launch Exception
	 				System.out.println("	No Year exists for this expense to be added in, which means any month.\n"
								+ "		Create the salary for "+month+" "+yearInput);
	 				success=false;
	 			}
	 			
	 			
	 		}
 		}while(retValue==-1); //if error, prompt again the value
 		return success;
	}
	
	//INTEGRATED IN modifyExpense
	public static void removeExpense(ArrayList<Year> yearsList) {
		System.out.println("\n  >>     Remove an expense:");
		Integer id;
		do {
			System.out.print("  >>>>>>>>> Expense ID:    ");
			Scanner scannerOutcome1=new Scanner(System.in);
			id = Integer.parseInt(scannerOutcome1.next());
			if(id<0) {
	 			System.out.println("	Input expense is not valid.");
			}
		}while(id<=0);
 		
		//int ret_value_month_to_pick=0;
		int retValue=0;
 		do {
 			System.out.print("  >>>>>>>> Month:    ");
			Scanner scannerOutcome3 = new Scanner(System.in);
	 		String month= scannerOutcome3.nextLine();
	 		month=month.toUpperCase();
	 		retValue=Utils.MonthToInt(month);
	 		//System.out.println("\n @@@ month is "+month+", ret_value is "+ret_value);
	 		if(retValue != -1) {
	 			Scanner scannerOutcome4 = new Scanner(System.in);
	 			System.out.print("  >>>>>>> Year:    ");
	 			String yearInput = scannerOutcome4.nextLine();
	 			
	 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
	 			
	 			//given the year, the YearsList is cycled
	 			Boolean yearFound=false;
	 			for(Year yCurr:yearsList) {
	 				if(yCurr.getYear().equals(yearInput)) {
	 					yearFound=true;
	 					//the year  exists. If the month exists, remove the outcome. 
	 					
	 					Salary s = yCurr.getMonths().get(retValue-1); // aka Salary salary = Salary_2021.get(ret_value-1); 

	 					if(s!=null) {
	 						//months exists
	 						s.removeSingleOutcome(id);
		 					
	 					}else {
	 						// add exception
	 						System.out.println("	No month exist in this Year for the expense.");
	 					}
	 					
	 				}
	 			}
	 			if(yearFound == false) {
	 				// year was not found. This means no month exists for that Year.
	 				//launch Exception
	 				System.out.println("	This Year doesn't exist.");
	 			}
	 			
	 			
	 		}
 		}while(retValue==-1); //if error, prompt again the value
	}
	
	/*
	 * Given an ArrayList<String>,
	 * displays all the data related to all the Years.
	 */
	public static void printAll(ArrayList<Year> yearsList) {
		Double yearsIncome = 0.00;
		Double yearsOutcome = 0.00;
		Double yearsGain = 0.00;
		for(Year yPrint : yearsList) {
			yearsIncome += yPrint.getTotIncome();
			yearsOutcome += yPrint.getTotOutcome();
		}
		yearsGain=yearsIncome - yearsOutcome;
		String sYearsIncome = Utils.convertDecimalFormat2(yearsIncome);
		String sYearsOutcome = Utils.convertDecimalFormat2(yearsOutcome);
		String sYearsGain= Utils.convertDecimalFormat2(yearsGain);
		
		/*
		 * Adding warning if gain is negative
		 */
		String warning="";
		if(yearsGain<0) {
			warning = " 	[WARNING]";
		}
		
		System.out.println(" 	--> 	$   	"+Utils.ANSI_GREEN+sYearsIncome+"      "+Utils.ANSI_RED+sYearsOutcome+Utils.ANSI_YELLOW+"       => "+sYearsGain+Utils.ANSI_RED+warning+Utils.ANSI_WHITE+"\n");
		
		for(Year yPrint : yearsList) {
			String yearIncome = Utils.convertDecimalFormat2(yPrint.getTotIncome());
			String yearOutcome = Utils.convertDecimalFormat2(yPrint.getTotOutcome());
			Double yearGain = yPrint.getTotIncome()-yPrint.getTotOutcome();
			String gain= Utils.convertDecimalFormat2(yearGain);
			
			/*
			 * Adding warning if gain is negative
			 */
			String warningYear="";
			if(yearGain<0) {
				warningYear = " 	[WARNING]";
			}
			
			System.out.print("\n # "+Utils.ANSI_YELLOW+yPrint.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________ "
					+Utils.ANSI_GREEN+"+"+yearIncome+Utils.ANSI_RED+" -"+yearOutcome+Utils.ANSI_YELLOW+" 	=> "+gain+Utils.ANSI_RED+warningYear+"\n");
			Collection<Salary> salaries = yPrint.getMonths().values();
			for(Salary s: salaries) { 	
				s.printSalary(); 	
			}
		}
	}
	
	public static void printMonthByCategories(ArrayList<Year> yearsList) {
		Double yearsIncome = 0.00;
		Double yearsOutcome = 0.00;
		Double yearsGain = 0.00;
		for(Year yPrint : yearsList) {
			yearsIncome += yPrint.getTotIncome();
			yearsOutcome += yPrint.getTotOutcome();
		}
		yearsGain=yearsIncome - yearsOutcome;
		String sYearsIncome = Utils.convertDecimalFormat2(yearsIncome);
		String sYearsOutcome = Utils.convertDecimalFormat2(yearsOutcome);
		String sYearsGain= Utils.convertDecimalFormat2(yearsGain);
		
		System.out.println(" 	--> 	$   	"+Utils.ANSI_GREEN+sYearsIncome+"      "+Utils.ANSI_RED+sYearsOutcome+Utils.ANSI_CYAN+"       => "+sYearsGain+Utils.ANSI_WHITE+"\n");
		
		for(Year yPrint : yearsList) {
			String yearIncome = Utils.convertDecimalFormat2(yPrint.getTotIncome());
			/*String year_outcome=null;
			if(Double.compare(y_print.getTotOutcome(),0)==0) {
				System.out.println("GOTCHA!");
				year_outcome = "0";
			}else {
				year_outcome = Utils.convertDecimalFormat(y_print.getTotOutcome());
			}*/
			String yearOutcome = Utils.convertDecimalFormat2(yPrint.getTotOutcome());
			String gain= Utils.convertDecimalFormat2(yPrint.getTotIncome()-yPrint.getTotOutcome());
			System.out.print("\n # "+Utils.ANSI_YELLOW+yPrint.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________ "
					+Utils.ANSI_GREEN+"+"+yearIncome+Utils.ANSI_RED+" -"+yearOutcome+Utils.ANSI_CYAN+" 	++ "+gain+"\n");
			Collection<Salary> salaries = yPrint.getMonths().values();
			for(Salary s: salaries) { 	
				s.printOutcomesHashMapGroupCat(); 	
			}
		}
	}
	
	public static void printYearsByCategories(ArrayList<Year> yearsList) {
		Double yearsIncome = 0.00;
		Double yearsOutcome = 0.00;
		Double yearsGain = 0.00;
		for(Year yPrint : yearsList) {
			yearsIncome += yPrint.getTotIncome();
			yearsOutcome += yPrint.getTotOutcome();
		}
		yearsGain=yearsIncome - yearsOutcome;
		String sYearsIncome = Utils.convertDecimalFormat2(yearsIncome);
		String sYearsOutcome = Utils.convertDecimalFormat2(yearsOutcome);
		String sYearsGain= Utils.convertDecimalFormat2(yearsGain);
		
		System.out.println(" 	--> 	$   	"+Utils.ANSI_GREEN+sYearsIncome+"      "+Utils.ANSI_RED+sYearsOutcome+Utils.ANSI_YELLOW+"       => "+sYearsGain+Utils.ANSI_WHITE+"\n");
		
		for(Year yPrint : yearsList) {
			String yearIncome = Utils.convertDecimalFormat2(yPrint.getTotIncome());
			/*String year_outcome=null;
			if(Double.compare(y_print.getTotOutcome(),0)==0) {
				System.out.println("GOTCHA!");
				year_outcome = "0";
			}else {
				year_outcome = Utils.convertDecimalFormat(y_print.getTotOutcome());
			}*/
			String yearOutcome = Utils.convertDecimalFormat2(yPrint.getTotOutcome());
			String gain= Utils.convertDecimalFormat2(yPrint.getTotIncome()-yPrint.getTotOutcome());
			System.out.print("\n # "+Utils.ANSI_YELLOW+yPrint.getYear()+Utils.ANSI_WHITE+" \\________________________________________________________________________ "
					+Utils.ANSI_GREEN+"+"+yearIncome+Utils.ANSI_RED+" -"+yearOutcome+Utils.ANSI_CYAN+" 	++ "+gain+"\n");
			Collection<Salary> salaries = yPrint.getMonths().values();
			
			List<Map<String, Double>> listMonthsOrderedGroupedByCat = new ArrayList<>();
			
			
			//	https://stackoverflow.com/questions/72220565/how-to-merge-different-hashmaps-with-same-keys-but-different-values
			
			for(Salary s: salaries) { 	
				listMonthsOrderedGroupedByCat.add(s.getOutComeHashMapGroupCategories());			
			}
		
			Map<String, Double> mergedMap = new HashMap<>();
	
			listMonthsOrderedGroupedByCat.forEach(map -> map.forEach((k, v) -> mergedMap.merge(k, v, Double::sum)));
			
			// now i have to order this hashmap by value
			
			//ordering categoriesMap values (double)
			Collection<Double> categoriesValues = mergedMap.values();
			List<Double> listToOrder = new ArrayList(categoriesValues);
			Collections.sort(listToOrder);
			Collections.reverse(listToOrder); //order desc
			
			//creating an hashmap with order persistence (LinkedHashMap) to preserve order
			//populating this hashmap keys by looking for values in categoriesMap (unordered)
			Map<String,Double> orderedCategoriesMap = new LinkedHashMap<>();
			for(Double d:listToOrder) {	 //values
				for(String s:mergedMap.keySet()) { //categories
					if(((mergedMap.get(s)).compareTo(d))==0) {
						orderedCategoriesMap.put(s, d);
					}
				}
			}
			
			//print
			Collection<String> categoriesOrderedSet = orderedCategoriesMap.keySet();
			for(String s:categoriesOrderedSet) {
				System.out.println(" 	"+Utils.ANSI_MGNT+s+"\t\t"+Utils.ANSI_RED+Utils.convertDecimalFormat2(orderedCategoriesMap.get(s))+Utils.ANSI_WHITE);
			}
			
		}
	}
	
	public static ArrayList<Year> loadData(ArrayList<Year> yearsList, HashMap<String,String> categoriesMap) {
//		public static ArrayList<Year> loadData(ArrayList<Year> yearsList) {
		
		FileHandler fw=new FileHandler();
		System.out.println("  Loading file {"+fw.getMyFile()+"} ...");
		FileHandler fr=new FileHandler();
		yearsList = fr.readingFile(categoriesMap);
//		yearsList = fr.readingFile();
		System.out.println("\n  Data "+Utils.ANSI_GREEN+"loaded."+Utils.ANSI_WHITE);
		return yearsList;
	}

	public static void saveData(ArrayList<Year> yearsList) {
		FileHandler fw=new FileHandler();
		System.out.println("  Writing in file {"+fw.getMyFile()+"} ...");
		fw.writingFile(yearsList);
		String bkpFile = fw.backupFile(yearsList);
		System.out.println("\n  Backup "+Utils.ANSI_GREEN+"created: "+Utils.ANSI_WHITE+bkpFile);
		
		//only the most 5 recents backups are kept
		System.out.println("\n  Deleting older backup files..."+Utils.ANSI_WHITE);
		fw.deleteBackup();
		
		System.out.println("\n  Data "+Utils.ANSI_GREEN+"saved."+Utils.ANSI_WHITE);
	}
	
	/*
	 * Given an ArrayList<String>,
	 * displays all the data related to the input Year.
	 */
	public static void printYear(ArrayList<Year> yearsList) {
		System.out.print("  >>>>>>> Year:    ");
		Scanner scanner = new Scanner(System.in);
		String yearSel = scanner.next();
		Boolean yearExist=false;
			for(Year yCurr:yearsList) {
				if(yCurr.getYear().equals(yearSel)) {
					//the year already exists
					yearExist=true;
					
					Double yearsIncome = yCurr.getTotIncome();
					Double yearsOutcome = yCurr.getTotOutcome();
					Double yearsGain=yearsIncome - yearsOutcome;
					String sYearsIncome = Utils.convertDecimalFormat2(yearsIncome);
					String sYearsOutcome = Utils.convertDecimalFormat2(yearsOutcome);
					String sYearsGain= Utils.convertDecimalFormat2(yearsGain);
					
					/*
					 * Adding warning if gain is negative
					 */
					String warningYear="";
					if(yearsGain<0) {
						warningYear = " 	[WARNING]";
					}
					
					System.out.println(" 	--> 	$   	"+Utils.ANSI_GREEN+sYearsIncome+"      "+Utils.ANSI_RED+sYearsOutcome+Utils.ANSI_YELLOW+"       => "+sYearsGain+Utils.ANSI_RED+warningYear+Utils.ANSI_WHITE+"\n");
					
					//print this year
					System.out.print("\n # "+Utils.ANSI_YELLOW+yCurr.getYear()+
							Utils.ANSI_WHITE+" \\________________________________________________________________________\n");
					Collection<Salary> salaries = yCurr.getMonths().values();
					for(Salary s: salaries) { 	
						s.printSalary(); 	
					}
				}
			}
			if(yearExist==false) {
				System.out.println("  Year "+yearSel+" does not exist... nothing to see here!");
			}
	}

	/*
	 * Given an ArrayList<Year>,
	 * displays stats for each month, giving a percentage representation for expenses and gains.
	 */
	public static void showStats(ArrayList<Year> yearsList) {
		System.out.println(Utils.ANSI_RED+"\t\t\t\t\tEXPENSES"+Utils.ANSI_GREEN+"\t\tGAINS\n");
		for(Year y : yearsList) {
			Collection<Salary> salaries = y.getMonths().values();
			for(Salary s: salaries) { 	
				System.out.print(Utils.ANSI_WHITE+"     	"+s.getMonth()+" "+y.getYear()+ " 		");
				int gain = (int)((s.getIncome()-s.getTotalOutcome())/50);
				int out = (int)(s.getTotalOutcome()/50);
				System.out.print(Utils.ANSI_RED);
				for(int i=0;i<out;i++) {
					System.out.print("#");
				}
				
				System.out.print(Utils.ANSI_GREEN);
				for(int i=0;i<gain;i++) {
					System.out.print("#");
				}
				
				double gainTot = s.getIncome()-s.getTotalOutcome();
				double outTot = s.getTotalOutcome();
				
				double gainPerc = gainTot/(gainTot+outTot) * 100;
				double outPerc = outTot/(gainTot+outTot) * 100;
				
				String gainPercStr = Utils.convertDecimalFormat1(gainPerc);
				String outPercStr = Utils.convertDecimalFormat1(outPerc);
				
				if(outPercStr.equals(",0")) {
					outPercStr = "";
				}else {
					outPercStr.replace(",", ".");
					outPercStr = outPercStr + "%";
				}
				gainPercStr.replace(",", ".");
				gainPercStr = gainPercStr + "%";
				
				
				System.out.println("\n   	   			"+Utils.ANSI_RED+outPercStr+"	   	   "+Utils.ANSI_GREEN+gainPercStr);
				System.out.println();
				
			}
		}
	}
	
	/*
	 * Given an HashMap<String,String> and the input entry
	 * returns the HashMap with the new element added
	 */
	public static HashMap<String,String> insertCategoriesIntoMap(HashMap<String,String> categoriesMap, String existentCode){
		System.out.println("	Adding category to Categories\n");
		String 	categoryCode; 

		if(existentCode != null) {
			System.out.print("		Category Code (existing): "+existentCode);
			categoryCode = existentCode;
		}else {
			System.out.print("		Category Code (new): ");
	        Scanner scannerCategoryCode = new Scanner(System.in);
	        categoryCode= scannerCategoryCode.next(); 
		}
		    
        System.out.print("		Category Description: ");
	 	Scanner scannerCategoryDescr = new Scanner(System.in);
	 	String 	categoryDescr= scannerCategoryDescr.nextLine();
	 	
		categoriesMap.put(categoryCode,categoryDescr);
		return categoriesMap;
	}

	/*
	 * Given an HashMap<String,String>, which is populated by reading CategoriesCFG.txt,
	 * all the entries CODE | DESCRIPTION are displayed.
	 */
	public static void readCategories(HashMap<String, String> categoriesMap) {
		System.out.println("	Reading categories\n");
		categoriesMap = fHUtil.readCategoriesFromTXT();
		for(String code: categoriesMap.keySet()) {
			System.out.println("\t\t"+Utils.ANSI_MGNT+code+"\t"+"|"+"\t"+categoriesMap.get(code));
		}
	}
	
	/*
	 * Given the expense id, the year and the month,
	 * edits an expense with several options: 
	 * 	1	Edit expense outcome
	 * 	2	Edit expense description
	 *  3	Edit expense category
	 *  4	Remove expense
	 */
	public static void modifyExpense(ArrayList<Year> yearsList,HashMap<String,String> categoriesMap) {
			System.out.println("\n  >>     Edit an expense:");
			Integer id;
			do {
				System.out.print("  >>>>>>>>> Expense ID:    ");
				Scanner scannerOutcome1=new Scanner(System.in);
				id = Integer.parseInt(scannerOutcome1.next());
				if(id<0) {
		 			System.out.println("	Input expense is not valid.");
				}
			}while(id<=0);
	 		
			int retValue=0;
	 		do {
	 			System.out.print("  >>>>>>>> Month:    ");
				Scanner scannerOutcome3 = new Scanner(System.in);
		 		String month= scannerOutcome3.nextLine();
		 		month=month.toUpperCase();
		 		retValue=Utils.MonthToInt(month);
		 		if(retValue != -1) {
		 			Scanner scannerOutcome4 = new Scanner(System.in);
		 			System.out.print("  >>>>>>> Year:    ");
		 			String yearInput = scannerOutcome4.nextLine();
		 			
		 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
		 			
		 			//given the year, the YearsList is cycled
		 			Boolean yearFound=false;
		 			for(Year yCurr:yearsList) {
		 				if(yCurr.getYear().equals(yearInput)) {
		 					yearFound=true;
		 					//the year  exists. If the month exists, remove the outcome. 
		 					
		 					Salary s = yCurr.getMonths().get(retValue-1); // aka Salary salary = Salary_2021.get(ret_value-1); 

		 					if(s!=null) {
		 						//months exists
		 						
		 						//switch for the expense operation
		 						Scanner scannerOutcome5 = new Scanner(System.in);
		 			 			System.out.println("  >>>>>>> What do you want to do with this expense?");
		 			 			System.out.println("			"+Utils.ANSI_WHITE+"\t"+Utils.ANSI_CYAN+s.getOutcomes().get(id).getCategory()+"\t"+Utils.ANSI_RED+s.getOutcomes().get(id).getOutcome()+"\t"+Utils.ANSI_WHITE+s.getOutcomes().get(id).getDescription());
		 			 			Integer operationChoice;
		 			 			do {
		 			 				System.out.println("\t\t\t\t"+Utils.ANSI_YELLOW+"1"+Utils.ANSI_WHITE+" Edit expense outcome\t\t"+Utils.ANSI_YELLOW+"2"+Utils.ANSI_WHITE+" Edit expense description	\n\t\t\t\t"+Utils.ANSI_YELLOW+"3"+Utils.ANSI_WHITE+" Edit expense category"+"\t\t"+Utils.ANSI_YELLOW+"4"+Utils.ANSI_WHITE+" Remove expense\t\t"+Utils.ANSI_YELLOW+"0"+Utils.ANSI_WHITE+" Exit");
		 			 				System.out.print(Utils.ANSI_YELLOW+"\n -> "+Utils.ANSI_WHITE);
		 			 				operationChoice = scannerOutcome5.nextInt();
			 			 			switch(operationChoice) {
				 			 			case 1:
				 			 				Double expense=0.0;
				 			 				String strExpense;
				 			 				boolean expenseValid=false;
	
				 			 				do {
				 			 					System.out.print("\n  >>>>>>>>> New Expense:    ");
				 			 					Scanner scannerOutcome51=new Scanner(System.in);
				 			 					strExpense = scannerOutcome51.next();
	
				 			 					//income validation
				 			 			 		if(Utils.doubleIsNumeric(strExpense) && !(Utils.hasComma(strExpense))) {
				 			 			 			expense=Double.parseDouble(strExpense);
				 			 			 			if(expense>=0) {
				 			 			 				//Ok
				 			 			 				expenseValid=true;
				 			 			 			}else{
				 			 			 				//TODO: launch exception + log
				 			 				 			System.out.println("	Input expense is not valid.");
				 			 				 		}
				 			 			 		}else{
				 			 		 				//TODO: launch exception + log
				 			 			 			System.out.println("	Input expense is not valid.");
				 			 			 		}
				 			 			 		
				 			 				}while(expenseValid == false);
				 			 				s.editSingleOutcome(id, expense);
				 			 				System.out.println("			Edited expense outcome successfully");
				 			 				break;
				 			 			case 2:
				 			 				String description;	
				 			 				System.out.print("\n  >>>>>>>>> New Description:    ");
				 			 				Scanner scannerOutcome52=new Scanner(System.in);
				 			 				description = scannerOutcome52.nextLine();
				 			 				if(description!=null) {
					 			 				s.editSingleOutcomeDescription(id, description);
					 			 				System.out.println("			Edited expense description successfully");
				 			 				}else {
					 			 				System.out.println("			Edit expense description failed");
				 			 				}
				 			 				break;
				 			 			case 3:
				 			 				boolean categoryValid=false;
				 			 				String category="";
				 			 				do{
				 			 					System.out.print("  >>>>>>>>> New Category:    ");
					 			 				Scanner scannerOutcome53=new Scanner(System.in);
				 			 					category = scannerOutcome53.nextLine();
				 			 					if(!Utils.checkCategories(category)) {
				 			 						//category found, proceeding with other fields
				 			 						categoryValid=true;
				 			 					}else {
				 			 						//if the category does not exists (checkCategories returns false), the user can add it
				 			 						System.out.println("  				Category not found. You can add it now: ");
				 			 						categoriesMap = insertCategoriesIntoMap(categoriesMap,category);
				 			 						fHUtil.writeCategoriesToTXT(categoriesMap);
				 			 						categoryValid=true;
				 			 					}
				 			 				}while(categoryValid==false);
				 			 				s.editSingleOutComeCategory(id, category);
				 			 				System.out.println("			Edited expense category successfully");	
				 			 				break;
				 			 			case 4:
					 						s.removeSingleOutcome(id);
				 			 				System.out.println("			Removed expense successfully");	
					 						break;
				 			 			case 0:
				 			 				break;
				 			 			default:
				 			 				break;
			 			 			}
		 			 			}while(operationChoice!=0);
		 			 						 					
		 					}else {
		 						// add exception
		 						System.out.println("	No month exist in this Year for the expense.");
		 					}
		 					
		 				}
		 			}
		 			if(yearFound == false) {
		 				// year was not found. This means no month exists for that Year.
		 				//launch Exception
		 				System.out.println("	This Year doesn't exist.");
		 			}
		 			
		 			
		 		}
	 		}while(retValue==-1); //if error, prompt again the value
		}
}
