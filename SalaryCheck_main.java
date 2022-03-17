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

	public static void main(String[] args) {
		
		Utils.intro();
		ArrayList<Year> YearsList = new ArrayList<>();		
		Utils.menu();
		
		int scelta=0;
		do {
			System.out.print(Utils.ANSI_YELLOW+"\n -> "+Utils.ANSI_WHITE);
			Scanner scanner_scelta = new Scanner(System.in);
			scelta=scanner_scelta.nextInt();
			switch(scelta) {
				case 1:
					Utils.addSalary(YearsList);
					break;
				case 2:
					Utils.addExpense(YearsList);
					break;
				case 3:
					Utils.removeExpense(YearsList);
					Utils.saveData(YearsList);
					YearsList=Utils.loadData(YearsList);
					break;
				case 4:
					Utils.printAll(YearsList);
					break;
				case 5:
					Utils.printYear(YearsList);
					break;
				case 6:
					YearsList = Utils.loadData(YearsList);
					break;
				case 7:
					Utils.saveData(YearsList);
					break;
				case 8:
					Utils.showStats(YearsList);
					break;
				case 9: 
					Utils.menu();
					break;
			}
		}while(scelta!=0);
	}
}
