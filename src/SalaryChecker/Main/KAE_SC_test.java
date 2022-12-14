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

public class KAE_SC_test {

	public static void main(String[] args) {
		//delete backups
//		FileHandler fh = new FileHandler();
//		fh.deleteBackup();
		
		//capitalize the first letter in a string
		String word = "word";
		System.out.println(word +" -> "+Utils.capitalizeFirstLetter(word));
		word = "wOrD";
		System.out.println(word+" -> "+Utils.capitalizeFirstLetterAfterLowerCase(word));
		
	}
}
