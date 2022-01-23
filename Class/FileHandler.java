package SalaryChecker.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

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
	
	
	private String myFile=System.getProperty("user.dir") + "\\src\\SalaryChecker\\Output\\" + "write.txt";
	//user.dir = D:\MegaSync\Developer\Angular Spring\Spring STS\workspace-spring-tool-suite-4-4.12.0.RELEASE\Esercizio 26 SalaryChecker
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
            	bw.write(s.getMonth()+ " " +s.getIncome()+" "+s.getTotal_outcome()+"\n"); //when reading, set up the regex for |
            	//to write the outcomes too, i added a new method in Salary: getHashMap (returns the HashMap)
            	
            	HashMap<Integer, Outcome> outcomes = s.getOutcomes();
            	int count=0;
            	for(Outcome o : outcomes.values()){
            		count++;
            	}
            	bw.write(count+"\n");
                bw.flush();
                
            	for(Outcome o : outcomes.values()){
            		bw.write(o.getOutcome()+" "+o.getDescription()+"\n");
                    bw.flush();
            	}
    		}
        }catch(IOException e) {
        	//throw new FileNotFoundException("The file described is nowhere to be found.");
        	System.out.println("\nFile non trovato: \n"+e.getStackTrace());
        }
	}
	
	
	/*
	 * Given a txt file, the method fills the HashMap with the read values and returns it.
	 */
	public HashMap<Integer,Salary> readingFile() {
		HashMap<Integer,Salary> Salary_2021 = new HashMap<>(12);
		int ret_value=0;
		try {
			File file = new File(myFile);
			Scanner scanner = new Scanner(file);
			String fileContent = "";
			while(scanner.hasNext()){ //reads the whole file
				try {
					String month = scanner.next(); 
					System.out.println("month::"+month+" loaded");
					month=month.toUpperCase();
					ret_value=MonthToInt(month);
					
					Double income=Double.parseDouble(scanner.next());
//					System.out.println(" - - "+income);
					Double outcome=Double.parseDouble(scanner.next());
//					System.out.println(" - - "+outcome);
					
					if(ret_value != -1) {
			 			Salary salary = new Salary(income,month);
			 			Salary_2021.put(ret_value-1, salary); 
			 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
			 			//it's critical to subtract -1 from ret value now because it may broke the do while
			 			
			 			Integer n_s_outcomes = Integer.parseInt(scanner.next());
//						System.out.println(" - - "+n_s_outcomes);
	                    for(int i=0;i<n_s_outcomes;i++) {
//	                    	System.out.println("-:: sub element "+i+1);
	                    	try {
	                    		Double s_outcome = Double.parseDouble(scanner.next());
//		                    	System.out.println("-:: :: expense "+s_outcome);
		                    	
		                        String description = scanner.nextLine();
//		                        System.out.println(" - "+description);

		                        salary.setSingle_outcome(s_outcome, description);

	                    	} catch (NumberFormatException e) {
	                    	    e.printStackTrace();
	                    	}
		                    	
	                    }
					}
				}catch(InputMismatchException ex){
                  //scanner.nextLine(); //skips the line if there is an error.
					ex.printStackTrace();
				}
//				catch(NoSuchElementException ex) {
//					System.out.println("launch \"NoSuchElementException\" "
//							+ "customized following the fix provided by hasNext instead of hasNextLine"
//							+ "https://stackoverflow.com/a/58853018/8422732");
//				}
				
			}	
			//reading file while ended
            return Salary_2021;

		}catch (Exception e) {
            e.printStackTrace();
        }
		
		return Salary_2021;
	}
	
	/* Used in readingFile, to convert month String to integer*/
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
					System.out.println("!!! This month is not an option. The format is (January, February, March, ...)\n");
					return -1;
		}
	}
}