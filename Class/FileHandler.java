package SalaryChecker.Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/* 
 * 	It gives persistence to the program by:
 * 	loading all the already saved values and 
 * 	by storing the new ones into files that 
 * 	can be used as the project storage system.
 * 
 * 	AFN writing is WIP. reading has yet to be implemented	
 * 
 * 	Everytime the program run it reads the values from the file already existing (readingFile) and stores those values into the main HashMap.
 *  The user then works with those values, and when the user ends the program saves all of the data into the file (it overwrites the old file, 
 *  nothing is lost because what there was is in the program, what the user adds is still added to what it will save in the end.
 *  
 *  If the program is closed without any warning, the user still has its data saved into the old file 
 *  	(Maybe should i implement a "backup" feature?)
 */
public class FileHandler {
	
	
	private String myFile="...";

	
	public String getMyFile() {
		return myFile;
	}

	/*
	 * The method is called by passing as argument the HashMap which contains all the salary regarding a Year
	 * */
	public void writingHashMap(HashMap<Integer,Salary> salary_YY) {
		try {
            File file = new File(myFile);
    		file.createNewFile();

            FileWriter fw=new FileWriter(file); 
            BufferedWriter bw=new BufferedWriter(fw);  
            for (Salary s : salary_YY.values()) {
    		    // reads all the Salaries in the hashmap
            	bw.write(s.getMonth()+ " | " +s.getIncome()+" | "+s.getTotal_outcome()+"\n"); //when reading, set up the regex for |
            	//to write the outcomes too, i added a new method in Salary: getHashMap (returns the HashMap)
            	HashMap<Integer, Outcome> outcomes = s.getOutcomes();
            	for(Outcome o : outcomes.values()){
            		bw.write(o.getOutcome()+" | "+o.getDescription()+"\n");
                    bw.flush();
            	}
    		}
        }catch(IOException e) {
        	System.out.println("\nFile non trovato: \n"+e.getStackTrace());
        }
		
	}
	
	
	/*
	public void readingFile() {
		
	}
	*/
}
