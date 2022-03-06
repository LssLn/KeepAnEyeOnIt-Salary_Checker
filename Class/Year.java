package SalaryChecker.Class;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Year implements Comparable<Year>{
	private String year;
	private HashMap<Integer,Salary> months = new HashMap<>(12);
	
	public Year(String year) {
		this.year = year;
	}
	
	public String getYear() {
		return year;
	}
	
	public HashMap<Integer,Salary> getMonths(){
		return months;
	}
	
	public void addMonth(Integer index, Salary salary) {
		months.put(index,salary);
		
	}
	
	/*
	 * Removes a Salary from the HashMap, given its corrispective month Index.
	 * Returns the Salary (removal successfull) or null.
	 */
	public Salary removeMonth(Integer index) {
		return months.remove(index);
	}

	/*
	 * Comparator used to sort YearsList based on String year
	 */
	@Override
	public int compareTo(Year y) {
		// TODO Auto-generated method stub
		return getYear().compareTo(y.getYear());
	}
	
	/*
	public static Comparator<Year> perYear = new Comparator<Year>() {
		@Override
		public int compare(Year y1, Year y2) {
			String s=y1.getYear();
			return s.compareTo(y2.getYear());
		}
	};
	*/
	
	public double getTotIncome() {
		double tot_income=0.00;
		Collection<Salary> salaries = months.values();
		for(Salary s: salaries) {
			tot_income=tot_income+s.getIncome();
		}
		return tot_income;
	}
	
	public double getTotOutcome() {
		double tot_outcome=0.00;
		Collection<Salary> salaries = months.values();
		for(Salary s: salaries) {
			tot_outcome=tot_outcome+s.getTotal_outcome();
		}
		return tot_outcome;
	}
}
