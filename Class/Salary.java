package SalaryChecker.Class;

import java.util.HashMap;
import java.util.Set;

/*
 * Has an add and remove method: 
 * monthly and positive for add, 
 * negative for each purchase or expense.
 */
public class Salary {
	final String ANSI_GREEN="\u001b[32m";
	final String ANSI_RED="\u001b[31m";
	final String ANSI_YELLOW="\u001b[33m";
	final String ANSI_WHITE="\u001b[37m";

	
	private Double income;
	//it's the amount of all the single expenses
	private Double total_outcome; 
	private String month;
	//double is the amount of the expense, String the description.
	private HashMap<Integer, Outcome> outcomes = new HashMap<>();

	Integer outcome_id=0;

	
	
	//Constructor with income and month
	//implement an Exception handler for IllegalArgumentException in the main
	public Salary(Double income, String month) {
		this.income = income;
		this.month = month;
		this.total_outcome=0.0; //init to 0
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
	public Double getTotal_outcome() {
		return total_outcome;
	}

	/*
	 * Given outcomes HashMap
	 * Cycles it and add every outcome to the total_outcome value 
	 */
	//public void setTotal_outcome(HashMap<Double, String> outcomes) {
	public void setTotal_outcome() {
		Set<Integer> setkeys_outcomes = outcomes.keySet();
		for(Integer i: setkeys_outcomes) {
			Outcome outcome_returned = outcomes.get(i);
			total_outcome = total_outcome+outcome_returned.getOutcome();
		}
		//System.out.println("\nThe total Outcome, as for now, is: "+total_outcome);
//		this.total_outcome=total_outcome;
	}
	
	/* 
	 * Given outcomes HashMap, and the single outcome quantity and description.
	 * Adds the single outcome to the outcomes HashMap
	 */
	//public void setSingle_outcome(HashMap<Double,String> outcomes,Double outcome,String outcome_description) {
	public void setSingle_outcome(Double outcome_expense,String outcome_description) {
		Outcome outcome = new Outcome(outcome_expense,outcome_description);
		outcome_id++; //id increment
		outcomes.put(outcome_id, outcome);
		total_outcome = total_outcome + outcome_expense;	
	}

	//public void printOutcomesHashMap(HashMap<Double,String> outcomes) {
	public void printOutcomesHashMap() {
		Set<Integer> setkeys_outcomes = outcomes.keySet();
		//System.out.println("\nReading all the expenses:\n");
		for(Integer i: setkeys_outcomes) {
			Outcome outcome_returned = outcomes.get(i);
			System.out.println(ANSI_WHITE+"\t Expense: "+" "+i+" | "+ANSI_RED+outcome_returned.getOutcome()+ANSI_WHITE+" | "+ANSI_WHITE+outcome_returned.getDescription()+".");	
		}
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
		return "Salary - "+month+"[ income= " + income + "	 total_outcome= " + total_outcome + " ]";
	}
	
	public void printSalary() {
		System.out.println(ANSI_WHITE+ "\nSalary - "+month+" [ "+ANSI_GREEN+"income= " + income + ANSI_RED+" || total_outcome= " + total_outcome + ANSI_YELLOW+" || left= " +(income-total_outcome)+ANSI_WHITE+"]");
		incomeGraphics(income,total_outcome);
		System.out.println();
		printOutcomesHashMap();
	}
	
	public static void incomeGraphics(Double income, Double outcome) {
		final String ANSI_WHITE="\u001b[37m";
		final String ANSI_GREEN="\u001b[32m";
		final String ANSI_RED="\u001b[31m";
		final String ANSI_YELLOW="\u001b[33m";
		
		Integer num_divider=10;

		Double nblocksI=(income/num_divider);
		System.out.print("  ");
		for(int i=0;i<nblocksI;i++) {
			System.out.print(ANSI_GREEN+"#");
		}
		System.out.println(ANSI_WHITE);
		
		Double nblocksO=(outcome/num_divider);
		System.out.print("  ");
		for(int i=0;i<nblocksO;i++) {
			System.out.print(ANSI_RED+"#"); //•
		}
		System.out.print(ANSI_WHITE);
		
		Double nblocksL=(income-outcome)/num_divider;
		for(int i=0;i<nblocksL;i++) {
			System.out.print(ANSI_WHITE+"#");
		}
		System.out.print(ANSI_WHITE);

	}
	
}
