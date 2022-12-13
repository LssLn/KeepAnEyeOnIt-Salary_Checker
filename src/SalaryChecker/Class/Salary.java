package SalaryChecker.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Has an add and remove method: monthly and positive for add, negative for each purchase or expense;
 * getHashMap returns the expenses hashmap;
 * print and incomeGraphics are to print the Salary.
 */
public class Salary implements Serializable{
	private static final long serialVersionUID = -3341892028820186658L;
	
	private Double income;
	//it's the amount of all the single expenses
	private Double totalOutcome; 
	private String month;
	//double is the amount of the expense, String the description.
	private HashMap<Integer, Outcome> outcomes = new HashMap<>();

	Integer outcomeId=0;

	//Constructor with income and month
	//implement an Exception handler for IllegalArgumentException in the main
	public Salary(Double income, String month) {
		this.income = income;
		this.month = month;
		this.totalOutcome=0.0; //init to 0
	}

	public Salary() {
		//no-args for placeholders
	}

	public Double getIncome() {
		return income;
	}
	public String getMonth() {
		return month;
	}
	public Double getTotalOutcome() {
		return totalOutcome;
	}

	/*
	 * Given outcomes HashMap
	 * Cycles it and add every outcome to the totalOutcome value 
	 */
	//public void setTotal_outcome(HashMap<Double, String> outcomes) {
	public void setTotalOutcome() {
		Set<Integer> setkeysOutcomes = outcomes.keySet();
		for(Integer i: setkeysOutcomes) {
			Outcome outcomeReturned = outcomes.get(i);
			totalOutcome = totalOutcome+outcomeReturned.getOutcome();
		}
	}
	
	public void setTotalOutcomeByParam(Double totalOutcome) {
		this.totalOutcome=totalOutcome;
	}
	
	public void setIncome(Double income) {
		this.income=income;
	}
	
	/* 
	 * Given outcomes HashMap, and the single outcome quantity and description.
	 * Adds the single outcome to the outcomes HashMap
	 */
	//public void setSingle_outcome(HashMap<Double,String> outcomes,Double outcome,String outcome_description) {
	public void setSingleOutcome(Double outcomeExpense,String outcomeDescription, String category) {
		Outcome outcome = new Outcome(outcomeExpense,outcomeDescription,category);
		outcomeId++; //id increment
		outcomes.put(outcomeId, outcome);
		totalOutcome = totalOutcome + outcomeExpense;	
	}

	public void printOutcomesHashMap() {
		Set<Integer> setkeysOutcomes = outcomes.keySet();
		for(Integer i: setkeysOutcomes) {
			Outcome outcomeReturned = outcomes.get(i);
//			System.out.println(ANSI_WHITE+"	  Expense: "+" "+i+"	- "+ANSI_RED+outcome_returned.getOutcome()+ANSI_WHITE+"			"+ANSI_WHITE+outcome_returned.getDescription()+"");	
//			System.out.println(Utils.ANSI_WHITE+"		"+i+"\t"+outcome_returned.getDescription()+" -- "+Utils.ANSI_RED+Utils.convertDecimalFormat2(outcome_returned.getOutcome())+Utils.ANSI_WHITE + " 	[" +Utils.ANSI_YELLOW+outcome_returned.getCategory()+Utils.ANSI_WHITE+"]");	
			System.out.println(Utils.ANSI_WHITE+"		"+i+"\t"+Utils.ANSI_MGNT+outcomeReturned.getCategory()+Utils.ANSI_WHITE+"\t\t"+Utils.ANSI_RED+Utils.convertDecimalFormat2(outcomeReturned.getOutcome())+"\t"+" "+Utils.ANSI_WHITE+outcomeReturned.getDescription());	
		}
	}
	
	public void printOutcomesHashMapFilter() {
		Set<Integer> setkeysOutcomes = outcomes.keySet();
		for(Integer i: setkeysOutcomes) {
			Outcome outcomeReturned = outcomes.get(i);
//			System.out.println(ANSI_WHITE+"	  Expense: "+" "+i+"	- "+ANSI_RED+outcome_returned.getOutcome()+ANSI_WHITE+"			"+ANSI_WHITE+outcome_returned.getDescription()+"");	
//			System.out.println(Utils.ANSI_WHITE+"		"+i+"\t"+outcome_returned.getDescription()+" -- "+Utils.ANSI_RED+Utils.convertDecimalFormat2(outcome_returned.getOutcome())+Utils.ANSI_WHITE + " 	[" +Utils.ANSI_YELLOW+outcome_returned.getCategory()+Utils.ANSI_WHITE+"]");	
			System.out.println(Utils.ANSI_WHITE+"		"+Utils.ANSI_MGNT+outcomeReturned.getCategory()+Utils.ANSI_WHITE+"\t\t"+Utils.ANSI_RED+Utils.convertDecimalFormat2(outcomeReturned.getOutcome())+"\t"+" "+Utils.ANSI_WHITE+outcomeReturned.getDescription());	
		}
	}
	
	/*
	 * Given outcomes (Outcome Hashmap)
	 * displays the expenses for the salary grouped by Category, ordered by amount (total outcome)
	 * returns void
	 */
	public void printOutcomesHashMapGroupCat() {
		String totOutcFormatted=Utils.convertDecimalFormat2(totalOutcome);
		if(totOutcFormatted.equals(",00")) {
			totOutcFormatted="0";
		}
		System.out.println(Utils.ANSI_WHITE+ "\n"+Utils.ANSI_GRASS+"   > " +
				Utils.ANSI_WHITE+month+" [ "+
				Utils.ANSI_GREEN+  "  +  " + Utils.convertDecimalFormat2(income) + 
				Utils.ANSI_RED+"      -   " + totOutcFormatted + 
				Utils.ANSI_BRIGHT_YELLOW+"   ->  " +(Utils.convertDecimalFormat2(income-totalOutcome))+
				Utils.ANSI_WHITE+"   ]");
		
		
		Set<Integer> setkeysOutcomes = outcomes.keySet();
		Set<Integer> setkeysOutcomes2 = outcomes.keySet();
		
		Set<String> categories = new HashSet<>();

		//getting all the categories
		for(Integer i: setkeysOutcomes) {
			Outcome outcomeReturned = outcomes.get(i);
			
			String category = outcomeReturned.getCategory();
			categories.add(category);
			
		}
		//hashmap set up (n elements for n categories)
		Map<String, Double> categoriesMap = new HashMap<>();
		for(String c: categories) {
			categoriesMap.put(c, 0.0);
		}
		//hashmap populated with double value
		for(String c: categories) {
			for(Integer k: setkeysOutcomes2) {
				Outcome outcomeReturned2 = outcomes.get(k);
				if(outcomeReturned2.getCategory().equals(c)) {
					categoriesMap.put(c,categoriesMap.get(c)+outcomeReturned2.getOutcome()); //https://stackoverflow.com/a/4158002/8422732
				}
			}
		}
		
		//ordering categoriesMap values (double)
		Collection<Double> categoriesValues = categoriesMap.values();
		List<Double> listToOrder = new ArrayList(categoriesValues);
		Collections.sort(listToOrder);
		Collections.reverse(listToOrder); //order desc
		
		//creating an hashmap with order persistence (LinkedHashMap) to preserve order
		//populating this hashmap keys by looking for values in categoriesMap (unordered)
		Map<String,Double> orderedCategoriesMap = new LinkedHashMap<>();
		for(Double d:listToOrder) {	
			for(String s:categories) {
				if(((categoriesMap.get(s)).compareTo(d))==0) {
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

	
	
	public Map<String, Double> getOutComeHashMapGroupCategories() {
		Set<Integer> setkeysOutcomes = outcomes.keySet();
		Set<Integer> setkeysOutcomes2 = outcomes.keySet();
		
		Set<String> categories = new HashSet<>();

		//getting all the categories
		for(Integer i: setkeysOutcomes) {
			Outcome outcomeReturned = outcomes.get(i);
			
			String category = outcomeReturned.getCategory();
			categories.add(category);
			
		}
		//hashmap set up (n elements for n categories)
		Map<String, Double> categoriesMap = new HashMap<>();
		for(String c: categories) {
			categoriesMap.put(c, 0.0);
		}
		//hashmap populated with double value
		for(String c: categories) {
			for(Integer k: setkeysOutcomes2) {
				Outcome outcomeReturned2 = outcomes.get(k);
				if(outcomeReturned2.getCategory().equals(c)) {
					categoriesMap.put(c,categoriesMap.get(c)+outcomeReturned2.getOutcome()); //https://stackoverflow.com/a/4158002/8422732
				}
			}
		}
		
		//ordering categoriesMap values (double)
		Collection<Double> categoriesValues = categoriesMap.values();
		List<Double> listToOrder = new ArrayList(categoriesValues);
		Collections.sort(listToOrder);
		Collections.reverse(listToOrder); //order desc
		
		//creating an hashmap with order persistence (LinkedHashMap) to preserve order
		//populating this hashmap keys by looking for values in categoriesMap (unordered)
		Map<String,Double> orderedCategoriesMap = new LinkedHashMap<>();
		for(Double d:listToOrder) {	
			for(String s:categories) {
				if(((categoriesMap.get(s)).compareTo(d))==0) {
					orderedCategoriesMap.put(s, d);
				}
			}
		}
		
		//print
//		Collection<String> categoriesOrderedSet = orderedCategoriesMap.keySet();
//		for(String s:categoriesOrderedSet) {
//			System.out.println(" 	"+Utils.ANSI_PURPLE+s+"\t\t"+Utils.ANSI_RED+Utils.convertDecimalFormat2(orderedCategoriesMap.get(s))+Utils.ANSI_WHITE);
//		}
		return orderedCategoriesMap;
	}
	
	
	/*
	 * Implemented for the FileHandler, it's used to iterate any outcome for any Salary.
	 * returns the outcomes HashMap
	 */
	public HashMap<Integer, Outcome> getOutcomes(){
		return outcomes;
	}
	//UNUSED
	@Override
	public String toString() {
		return "Salary - "+month+"[ income= " + income + "	 total outcome= " + totalOutcome + " ]";
	}
		
	public void printSalary() {
		String totOutcFormatted=Utils.convertDecimalFormat2(totalOutcome);
//		if(totOutcFormatted.equals(",00")) {
		if(totOutcFormatted.equals(",00") || totOutcFormatted.equals(".00")) {
			totOutcFormatted="0";
		}
		/*
		 * Adding warning if gain is negative
		 */
		String warningMonth="";
		if((income-totalOutcome)<0) {
			warningMonth = " 	[WARNING]";
		}
		System.out.println(Utils.ANSI_WHITE+ "\n"+Utils.ANSI_GRASS+"   > " +
				Utils.ANSI_WHITE+month+" [ "+
				Utils.ANSI_GREEN+  "  +  " + Utils.convertDecimalFormat2(income) + 
				Utils.ANSI_RED+"      -   " + totOutcFormatted + 
				Utils.ANSI_BRIGHT_YELLOW+"   ->  " +(Utils.convertDecimalFormat2(income-totalOutcome))+
				Utils.ANSI_RED+warningMonth+
				Utils.ANSI_WHITE+"   ]");
		incomeGraphics(income,totalOutcome);
		System.out.println();
		printOutcomesHashMap();
	}
	
	public void printSalaryFiltered() {
//		String totOutcFormatted=Utils.convertDecimalFormat2(totalOutcome);
//		if(totOutcFormatted.equals(",00") || totOutcFormatted.equals(".00")) {
//			totOutcFormatted="0";
//		}
//		
		System.out.println(Utils.ANSI_WHITE+ "\n"+Utils.ANSI_GRASS+"   > " +
				Utils.ANSI_WHITE+month
//				+ " [ "+
//				Utils.ANSI_RED+"      -   " + totOutcFormatted + 
//				Utils.ANSI_WHITE+"   ]"
				);
//		System.out.println();
		printOutcomesHashMapFilter();
	}
	
	public static void incomeGraphics(Double income, Double outcome) {

		Integer numDivider=20;

		Double nblocksI=(income/numDivider);
		Double nblocksO=(outcome/numDivider);
		System.out.print(Utils.ANSI_WHITE+"      ");
		for(int i=0;i<nblocksI-nblocksO;i++) {
			System.out.print(Utils.ANSI_GREEN+"+");
		}
		for(int i=0;i<nblocksO;i++) {
			System.out.print(Utils.ANSI_RED+"-"); //•
		}			
		System.out.print(Utils.ANSI_WHITE);
	}

	public void removeSingleOutcome(Integer id) {
		outcomes.remove(id);
	}
	
	public void editSingleOutcomeDescription(Integer id, String newDescription) {
		outcomes.get(id).setDescription(newDescription);
	}
	
	public void editSingleOutcome(Integer id, Double newOutcome) {
		outcomes.get(id).setOutcome(newOutcome);
	}
	
	public void editSingleOutComeCategory(Integer id, String newCategory) {
		outcomes.get(id).setCategory(newCategory);
	}

	public void editSalary(Double newIncome) {
		this.income=newIncome;
	}
	
}
