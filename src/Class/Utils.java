package SalaryChecker.Class;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {

	/*
	 * ANSI codes for colors
	 */
	public static String ANSI_GREEN="\033[92m"; // green = grass
	public static String ANSI_RED="\033[31m";
	public static String ANSI_YELLOW="\033[33m";
	public static String ANSI_WHITE="\033[37m";
	public static String ANSI_GRASS="\033[92m";
	public static String ANSI_MGNT="\033[36m";
	public static String ANSI_BRIGHT_YELLOW="\033[93m";

//	public static String ANSI_BG_GREEN="\033[42m";
//	public static String ANSI_BG_RED="\033[41m";
//	public static String RESET="\033[0m";
//	public static String ANSI_RESET = "\u001B[0m";
//	public static String ANSI_BLACK = "\u001B[30m";
	public static String ANSI_BLUE = "\u001B[34m";
	public static String ANSI_PURPLE = "\u001B[35m";
	public static String ANSI_CYAN = "\u001B[36m";
	
	/*
	 * Prints the application logo
	 */
	public static void intro() {
		System.out.println();
		System.out.println(Utils.ANSI_RED+	 "	 	#  #     #     #####    #####  ##### ");
		System.out.println(Utils.ANSI_YELLOW+"	 	# #     # #    #        #      #     ");
		System.out.println(Utils.ANSI_GREEN+ "	 	##     #####   ##### -- #####  #     ");
		System.out.println(Utils.ANSI_MGNT+  "	 	# #   #     #  #            #  #     ");
		System.out.println(Utils.ANSI_PURPLE+"	 	#  # #       # #####    #####  ##### ");
		System.out.println(Utils.ANSI_GREEN+"\n	 	KEEP AN EYE ON IT: SALARY CHECKER"+Utils.ANSI_WHITE+" 	v 1.5.0"+"  released on 11/09/2022");
	}

	/*
	 * Prints the menu' options
	 */
	public static void menu() {
		System.out.println();
		System.out.println(Utils.ANSI_YELLOW+"	 	1]\t"+Utils.ANSI_WHITE+"Adds a Month Salary ");
		System.out.println(Utils.ANSI_YELLOW+"	 	2]\t"+Utils.ANSI_WHITE+"Adds an expense to a specific Month");
		System.out.println(Utils.ANSI_YELLOW+"	 	3]\t"+Utils.ANSI_WHITE+"Removes an expense given its ID");
		System.out.println(Utils.ANSI_YELLOW+"	 	4]\t"+Utils.ANSI_WHITE+"Prints all the data related to all the Years");
		System.out.println(Utils.ANSI_YELLOW+"	 	5]\t"+Utils.ANSI_WHITE+"Prints all the data related to a specific Year");
		System.out.println(Utils.ANSI_YELLOW+"	 	6]\t"+Utils.ANSI_WHITE+"Loads from write.txt file (overwrites actual data)");
		System.out.println(Utils.ANSI_YELLOW+"	 	7]\t"+Utils.ANSI_WHITE+"Saves current data into write.txt and also creates a backup file each time");
		System.out.println(Utils.ANSI_YELLOW+"		8]\t"+Utils.ANSI_WHITE+"Displays stats for each month, giving a percentage representation for expenses and gains");
		System.out.println(Utils.ANSI_YELLOW+"		9]\t"+Utils.ANSI_WHITE+"Displays expenses grouped by Categories, per month, ordered by amount");
		System.out.println(Utils.ANSI_YELLOW+"		10]\t"+Utils.ANSI_WHITE+"Displays expenses grouped by Categories, per year, ordered by amount");
		System.out.println(Utils.ANSI_YELLOW+"		11]\t"+Utils.ANSI_WHITE+"Insert category into CategoriesCFG.txt");
		System.out.println(Utils.ANSI_YELLOW+"		12]\t"+Utils.ANSI_WHITE+"Displays all the categories from CategoriesCFG.txt");
		System.out.println(Utils.ANSI_YELLOW+"	 	100]\t"+Utils.ANSI_WHITE+"Menu'");
		System.out.println(Utils.ANSI_YELLOW+"	 	0]\t"+Utils.ANSI_WHITE+"Exit\n");
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

	/*
	 * Given a double,
	 * returns a String which represents the input value with 1 or 2 values for decimals
	 * 
	 */
	public static String convertDecimalFormat2(Double input) {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		return numberFormat.format(input);
	}
	
	public static String convertDecimalFormat1(Double input) {
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		return numberFormat.format(input);
	}
	
	
	/*
	 * Given an input,
	 * returns true if it is a number, false otherwise.
	 * https://www.baeldung.com/java-check-string-number#plain-java
	 */
	public static boolean doubleIsNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	/*
	 * Given an input,
	 * returns true if it is a number (Integer), false otherwise.
	 */
	public static boolean integerIsNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int i = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	// developed to handle the situation where a decimal input is given with comma "," (exception) format and not dot "." 
	/*
	 * Given a number input,
	 * returns true if it's in the format with dot separator "."
	 */
	public static boolean hasComma(String strNum) {
		if(strNum.contains(",")) {
			return true;
		}else {
			return false;
		}
	}
	// developed to handle the situation where a decimal input is given with comma "," (exception) format and not dot "." 
	/*
	 * Given a number input,
	 * returns true if it's in the format with dot separator "."
	 */
	public static boolean hasDot(String strNum) {
		if(strNum.contains(".")) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * Given a category code,
	 * returns true if categoriesMap has no equal code to categoryCode
	 * returns false if categoriesMap has at least a code equal to categoryCode
	 */
	public static boolean checkCategories(String category) {
		FileHandler fh = new FileHandler();
		HashMap<String,String> categoriesMap = fh.readCategoriesFromTXT();
		
		boolean noMatches=false;
		for(String s:categoriesMap.keySet()) {
			if(!(s.equals(category))) {
				noMatches = true;
			}
			else {
				//found a match
				return false;
			}
		}
		if(noMatches == true) {
			// no matches - category is new
			return true;
		}
		return false;
	}
}
