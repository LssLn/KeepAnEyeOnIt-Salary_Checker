package SalaryChecker.Class;

import java.io.Serializable;

public class Outcome implements Serializable{
	private static final long serialVersionUID = -7392453123307708141L;

	private Double outcome;
	private String description;
	private String category;
	
	public Outcome(Double outcome, String description,String category) {
		this.outcome = outcome;
		this.description = description;
		this.category=category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
