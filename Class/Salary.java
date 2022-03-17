package SalaryChecker.Class;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

/*
 * Has an add and remove method: monthly and positive for add, negative for each purchase or expense;
 * getHashMap returns the expenses hashmap;
 * print and incomeGraphics are to print the Salary.
 */
public class Salary {

	
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
//			System.out.println(ANSI_WHITE+"	  Expense: "+" "+i+"	- "+ANSI_RED+outcome_returned.getOutcome()+ANSI_WHITE+"			"+ANSI_WHITE+outcome_returned.getDescription()+"");	
			System.out.println(Utils.ANSI_WHITE+"		"+i+"\t"+outcome_returned.getDescription()+" -- "+Utils.ANSI_RED+Utils.convertDecimalFormat2(outcome_returned.getOutcome())+Utils.ANSI_WHITE);	
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
		String tot_outc_formatted=Utils.convertDecimalFormat2(total_outcome);
		if(tot_outc_formatted.equals(",00")) {
			tot_outc_formatted="0";
		}
		System.out.println(Utils.ANSI_WHITE+ "\n"+Utils.ANSI_GRASS+"   > " +
				Utils.ANSI_WHITE+month+" [ "+
				Utils.ANSI_GREEN+  "  +  " + Utils.convertDecimalFormat2(income) + 
				Utils.ANSI_RED+"      -   " + tot_outc_formatted + 
				Utils.ANSI_GRASS+"     ==  " +(Utils.convertDecimalFormat2(income-total_outcome))+
				Utils.ANSI_WHITE+"   ]");
		incomeGraphics(income,total_outcome);
		System.out.println();
		printOutcomesHashMap();
	}
	
	public static void incomeGraphics(Double income, Double outcome) {

		Integer num_divider=20;

		Double nblocksI=(income/num_divider);
		Double nblocksO=(outcome/num_divider);
		System.out.print(Utils.ANSI_WHITE+"      ");
		for(int i=0;i<nblocksI-nblocksO;i++) {
			System.out.print(Utils.ANSI_GREEN+"+");
		}
		for(int i=0;i<nblocksO;i++) {
			System.out.print(Utils.ANSI_RED+"-"); //•
		}			
		System.out.print(Utils.ANSI_WHITE);
	}

	public void removeSingle_outcome(Integer id) {
		outcomes.remove(id);
	}
	
}
