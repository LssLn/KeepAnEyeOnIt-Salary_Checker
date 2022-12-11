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
			scelta=scannerSwitch.next();
			
			categoriesMap = fHUtil.readCategoriesFromTXT();
			
			switch(scelta) {
				case "1":
					SwitchManagerUtils.addSalary(yearsList);
					break;
				case "2":
					SwitchManagerUtils.modifySalary(yearsList);
					System.out.println();
					SwitchManagerUtils.saveData(yearsList);
					yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "3":
					boolean expenseAddedSuccessfully = SwitchManagerUtils.addExpense(yearsList, categoriesMap);
					if(expenseAddedSuccessfully) {
						System.out.println();
						SwitchManagerUtils.saveData(yearsList);
						yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					}
					break;
				case "4":
					SwitchManagerUtils.modifyExpense(yearsList,categoriesMap);
					System.out.println();
					SwitchManagerUtils.saveData(yearsList);
					yearsList=SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "5":
					SwitchManagerUtils.printAll(yearsList);
					break;
				case "6":
					SwitchManagerUtils.printYear(yearsList);
					break;
				case "7":
					yearsList = SwitchManagerUtils.loadData(yearsList, categoriesMap);
					break;
				case "8":
					SwitchManagerUtils.saveData(yearsList);
					break;
				case "9":
					SwitchManagerUtils.showStats(yearsList);
					break;
				case "10":
					SwitchManagerUtils.printMonthByCategories(yearsList);
					break;
				case "11":
					SwitchManagerUtils.printYearsByCategories(yearsList);
					break;	
				case "12":
					String existentCode=null;
					categoriesMap = SwitchManagerUtils.insertCategoriesIntoMap(categoriesMap,existentCode);
					fHUtil.writeCategoriesToTXT(categoriesMap);
					break;
				case "13":
					SwitchManagerUtils.readCategories(categoriesMap);
					break;
				case "14":
					Scanner scanCategory = new Scanner(System.in);
					System.out.print(Utils.ANSI_WHITE+"\n  Category to filter: "+Utils.ANSI_WHITE);
					String category = scanCategory.nextLine().trim();
					SwitchManagerUtils.printAll(SwitchManagerUtils.filterByCategories(yearsList,category));
				case "m":
				case "M": 
					Utils.menu();
					break;
			}
		}while(!("0".equals(scelta)));
	}
}
