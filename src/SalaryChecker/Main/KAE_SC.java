package SalaryChecker.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import SalaryChecker.Class.FileHandler;
import SalaryChecker.Class.Salary;
import SalaryChecker.Class.Utils;
import SalaryChecker.Class.Year;
import SalaryChecker.Class.SwitchManagerUtils;

public class KAE_SC {

	public static void main(String[] args) {
		
		Utils.intro();
		ArrayList<Year> yearsList = new ArrayList<>();		
		Utils.menu();
		
		FileHandler fHUtil = new FileHandler();
		HashMap<String,String> categoriesMap = fHUtil.readCategoriesFromTXT();

		
		String scelta="END";
		do {
			System.out.print(Utils.ANSI_YELLOW+"\n -> "+Utils.ANSI_WHITE);
			Scanner scannerSwitch = new Scanner(System.in);
			scelta=scannerSwitch.next().toUpperCase();
			
			categoriesMap = fHUtil.readCategoriesFromTXT();
			
			switch(scelta) {
				case "1":
					SwitchManagerUtils.addSalary(yearsList);
					break;
				case "1E":
					SwitchManagerUtils.modifySalary(yearsList);
					System.out.println();
					SwitchManagerUtils.saveData(yearsList);
					yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "2":
					boolean expenseAddedSuccessfully = SwitchManagerUtils.addExpense(yearsList, categoriesMap);
					if(expenseAddedSuccessfully) {
						System.out.println();
						SwitchManagerUtils.saveData(yearsList);
						yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					}
					break;
				case "2E":
					SwitchManagerUtils.modifyExpense(yearsList,categoriesMap);
					System.out.println();
					SwitchManagerUtils.saveData(yearsList);
					yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "5":
					SwitchManagerUtils.printAll(yearsList);
					break;
				case "5Y":
					SwitchManagerUtils.printYear(yearsList);
					break;
				case "3":
					yearsList = SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "4":
					SwitchManagerUtils.saveData(yearsList);
					break;
				case "5S":
					SwitchManagerUtils.showStats(yearsList);
					break;
				case "6M":
					SwitchManagerUtils.printMonthByCategories(yearsList);
					break;
				case "6Y":
					SwitchManagerUtils.printYearsByCategories(yearsList);
					break;	
				case "7I":
					String existentCode=null;
					categoriesMap = SwitchManagerUtils.insertCategoriesIntoMap(categoriesMap,existentCode);
					fHUtil.writeCategoriesToTXT(categoriesMap);
					break;
				case "7":
					SwitchManagerUtils.readCategories(categoriesMap);
					break;
				case "5C":
					Scanner scanCategory = new Scanner(System.in);
					System.out.print(Utils.ANSI_WHITE+"\n  Category to filter: "+Utils.ANSI_WHITE);
					String category = scanCategory.nextLine().trim().toUpperCase();
					System.out.println("\n");
					SwitchManagerUtils.printAllFilters(SwitchManagerUtils.filterByCategories(yearsList,category));
					break;
				case "5D":
					Scanner scanDescription = new Scanner(System.in);
					System.out.print(Utils.ANSI_WHITE+"\n  Keyword to look for in outcomes descriptions: "+Utils.ANSI_WHITE);
					String description = scanDescription.nextLine().trim().toUpperCase();
					System.out.println("\n");
					SwitchManagerUtils.printAllFilters(SwitchManagerUtils.filterByDescr(yearsList,description));
					break;
				case "m":
				case "M": 
					Utils.menu();
					break;
			}
		}while(!("0".equals(scelta)));
	}
}
