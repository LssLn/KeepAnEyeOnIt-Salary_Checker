package SalaryChecker.Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/* 
 * 	It gives persistence to the program by loading all the already saved values and 
 * 	by storing the new ones into files that can be used as the project storage system.
 * 
 * 	Everytime the program execute the readingFile method it reads the values from the file already existing (readingFile) and stores those values into the main HashMap.
 *  The user then works with those values, and when the user ends, if the writingFile is executed, the program saves all of the data into the file.
 *  
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
	public void writingFile(ArrayList<Year> YearsList) {
		try {
            File file = new File(myFile);
    		file.createNewFile();

            FileWriter fw=new FileWriter(file); 
            BufferedWriter bw=new BufferedWriter(fw);  
            for(Year yCurrent : YearsList) {
            	bw.write(yCurrent.getYear()+"\n");
            	HashMap<Integer,Salary> Months = new HashMap<>(12);
            	Months=yCurrent.getMonths();
            	bw.write(Months.size()+"\n");
            	bw.flush();
            	for (Salary s : Months.values()) {
        		    // reads all the Salaries in the hashmap
                	bw.write(s.getMonth()+" "+s.getIncome()+" "+s.getTotalOutcome()+"\n");
                	//to write the outcomes too, i added a new method in Salary: getHashMap (returns the HashMap)
                	
                	HashMap<Integer, Outcome> outcomes = s.getOutcomes();
                	int count=0;
                	for(Outcome o : outcomes.values()){
                		count++;
                	}
                	bw.write(count+"\n");
                    bw.flush();
                    
                	for(Outcome o : outcomes.values()){
                		
                		bw.write(o.getOutcome()+" "+o.getCategory()+" "+o.getDescription().trim()+"\n");
                        bw.flush();
                	}
        		}//ends an year's months
            }//ends years
        }catch(IOException e) {
        	//throw new FileNotFoundException("The file described is nowhere to be found.");
        	System.out.println("\n	File non trovato: \n"+e.getStackTrace());
        }
	}
	
	/*
	 * The method is called by passing as argument the HashMap which contains all the salary regarding a Year
	 * It writes a file with filename being the call data time
	 * */
	public String backupFile(ArrayList<Year> YearsList) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");  
		LocalDateTime now = LocalDateTime.now();  
		String time = dtf.format(now);  
		String dataFile = System.getProperty("user.dir") + "\\src\\SalaryChecker\\Output\\" +time+".txt";
		try {
            File file = new File(dataFile);
    		file.createNewFile();

            FileWriter fw=new FileWriter(file); 
            BufferedWriter bw=new BufferedWriter(fw);  
            for(Year yCurrent : YearsList) {
            	bw.write(yCurrent.getYear()+"\n");
            	HashMap<Integer,Salary> Months = new HashMap<>(12);
            	Months=yCurrent.getMonths();
            	bw.write(Months.size()+"\n");
            	bw.flush();
            	for (Salary s : Months.values()) {
        		    // reads all the Salaries in the hashmap
                	bw.write(s.getMonth()+" "+s.getIncome()+" "+s.getTotalOutcome()+"\n");
                	//to write the outcomes too, i added a new method in Salary: getHashMap (returns the HashMap)
                	
                	HashMap<Integer, Outcome> outcomes = s.getOutcomes();
                	int count=0;
                	for(Outcome o : outcomes.values()){
                		count++;
                	}
                	bw.write(count+"\n");
                    bw.flush();
                    
                	for(Outcome o : outcomes.values()){
                		
                		bw.write(o.getOutcome()+" "+o.getCategory()+" "+o.getDescription().trim()+"\n");
                        bw.flush();
                	}
        		}//ends an year's months
            }//ends years
        }catch(IOException e) {
        	//throw new FileNotFoundException("The file described is nowhere to be found.");
        	System.out.println("\n	File non trovato: \n"+e.getStackTrace());
        }
		
		return dataFile;
	}
	
	
	/*
	 * Given a txt file, the method fills the HashMap with the read values and returns it.
	 */
	public ArrayList<Year> readingFile() {
		ArrayList<Year> YearsList = new ArrayList<>();		

		int retValue=0;
		try {
			File file = new File(myFile);
			Scanner scanner = new Scanner(file);
			String fileContent = "";
			while(scanner.hasNext()){ //reads the whole file
				try {
					String yearInput = scanner.next();
					Integer yNMonths = Integer.parseInt(scanner.next());
					Year year = new Year(yearInput);
					System.out.println("	Year "+yearInput+", loading "+yNMonths+" months ...");
					for(int yCont=0; yCont<yNMonths;yCont++) {
						String month = scanner.next(); 
						System.out.println("		"+month+Utils.ANSI_GREEN+" loaded"+Utils.ANSI_WHITE);
						month=month.toUpperCase();
						retValue=Utils.MonthToInt(month);
						
						Double income=Double.parseDouble(scanner.next());
//						System.out.println(" - - "+income);
						Double outcome=Double.parseDouble(scanner.next());
//						System.out.println(" - - "+outcome);
						
						if(retValue != -1) {
				 			Salary salary = new Salary(income,month);
				 			year.addMonth(retValue-1, salary); 
				 			//ret_value - 1 because index start from 0 up to 11, while months from 1 to 12
				 			//it's critical to subtract -1 from ret value now because it may broke the do while
				 			
				 			Integer nSOutcomes = Integer.parseInt(scanner.next());
//							System.out.println(" - - "+n_s_outcomes);
		                    for(int i=0;i<nSOutcomes;i++) {
//		                    	System.out.println("-:: sub element "+i+1);
		                    	try {
		                    		Double sOutcome = Double.parseDouble(scanner.next());
//			                    	System.out.println("-:: :: expense "+s_outcome);
		                    		String category = scanner.next();
			                        String description = scanner.nextLine();
//			                        System.out.println(" - "+description);
			                        description.trim();
			                        salary.setSingleOutcome(sOutcome, description,category);

		                    	} catch (NumberFormatException e) {
		                    	    e.printStackTrace();
		                    	}
			                    	
		                    }
						}
					} //ends the loading of year's months
					YearsList.add(year);
					//orders the YearsList
					YearsList.sort(null); 
					
					//here the loops starts again (inside the while), a new year is built until the end of file is reached
					
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
            return YearsList;

		}catch (Exception e) {
            e.printStackTrace();
        }
		return YearsList;
	}
}