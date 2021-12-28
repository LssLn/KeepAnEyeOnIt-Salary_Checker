package SalaryChecker.Class;

public class Outcome {
	/*
	 * It's used as value for the Outcomes Hashmap.
	 * */
	private Double outcome;
	private String description;
	public Outcome(Double outcome, String description) {
		this.outcome = outcome;
		this.description = description;
	}
	public Double getOutcome() {
		return outcome;
	}
	public void setOutcome(Double outcome) {
		this.outcome = outcome;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Outcome [outcome=" + outcome + ", description=" + description + "]";
	}
	
	
}
