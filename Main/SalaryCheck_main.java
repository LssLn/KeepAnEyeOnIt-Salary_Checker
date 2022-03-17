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
		ArrayList<Year> yearsList = new ArrayList<>();		
		Utils.menu();
		
		int scelta=0;
		do {
			System.out.print(Utils.ANSI_YELLOW+"\n -> "+Utils.ANSI_WHITE);
			Scanner scanner_scelta = new Scanner(System.in);
			scelta=scanner_scelta.nextInt();
			switch(scelta) {
				case 1:
					Utils.addSalary(yearsList);
					break;
				case 2:
					Utils.addExpense(yearsList);
					break;
				case 3:
					Utils.removeExpense(yearsList);
					Utils.saveData(yearsList);
					yearsList=Utils.loadData(yearsList);
					break;
				case 4:
					Utils.printAll(yearsList);
					break;
				case 5:
					Utils.printYear(yearsList);
					break;
				case 6:
					yearsList = Utils.loadData(yearsList);
					break;
				case 7:
					Utils.saveData(yearsList);
					break;
				case 8:
					Utils.showStats(yearsList);
					break;
				case 9: 
					Utils.menu();
					break;
			}
		}while(scelta!=0);
	}
}
