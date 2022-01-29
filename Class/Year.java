package SalaryChecker.Class;

import java.util.HashMap;

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
}
