<h1>Keep an Eye on It: SALARY CHECKER</h1>
<p align="center">
<img src="https://i.imgur.com/9Fbddhi.png">
</p>
<section>Don't want to drain your salary as quick as you use to do?<br><b>Keep an Eye on It: Salary Checker</b> is a Java project developed to better manage your monthly expenses through the years.<br>
<br>
<ul>
	<li>Classes</li>
	<li>Exception</li>
	<li>Output</li>
	<li>Main</li>
</ul>

<br>
<h2>CLASS</h2>
<ul>
	<li><h3>Outcome</h3>Each expense has an amount, a category, a n_ID and a description.</li>
	<li><h3>Salary</h3>Each Salary has a single income, and several outcomes.<br>All the outcomes are stored in an HashMap with key: Integer (auto incrementing id) and a value (outcome).<br>It has these methods worth mentioning:
		<ul>
			<li><h4>printOutcomeHashMap</h4>Prints the outcomes stored in the Salary Hashmap.</li>
			<li><h4>setSingleOutcome</h4></li>
			<li><h4>incomeGraphics</h4>Used to better display the Salary data.</li>
			<li><h4>printSalary</h4>Displays all the infos related to a Salary, such as income, gain and expenses value, info, n_ID</li>
			<li><h4>printOutcomesHashMapGroupCat</h4>Displays expenses grouped by category and ordered by amount.</li>
		</ul>
	</li>
	<li><h3>Year</h3>It has a String year and an HashMap<Integer,Salary> for the year months.
		<ul>
			<li><h4>Add/Remove Month</h4></li>
		<li><h4>getMonths</h4>Returns the HashMap<Integer,Salary> which represents the year months.</li>
		<li><h4>compareTo</h4>Year implements Comparable<Year>, compareTo is used to set the year String as parameter to order the ArrayList in the main.</li>
		</ul>
	</li>
	<li><h3>FileReaderAndWriter</h3>
	It gives persistence to the program by loading all the already saved values and by storing the new ones into files that can be used as the project storage system.
	<br>It has 2 methods:
		<ul>
			<li><h4>writingFile</h4>
			Given a YEARS ArrayList, the method iterates through it and saves all its data in a txt file, created in order to save the data and making therefore possible for the readingFile method to load the already existing data.
			<br>Everytime it's called, it overwrites the txt file deleting the old content.</li>
			<li><h4>backupFile</h4>
			Given a YEARS ArrayList, the method iterates through it and saves all its data in a txt file, created in order to backup the data and making therefore possible for write.txt to have a backup. The .txt is called after the actual time when the function is called.
			<br>Everytime it's called, it creates a new file.<br>Future rework: delete the older backups, keep a maximum number of files, where the maximum is represented by X: it can be defined by the user, by default should be a value of 5 or 10.</li>
			<li><h4>readingFile</h4>
			Given an already existing txt file written by the previous "writingFile" method, it loads the data retrieved from the txt file into the YEARS ArrayList.</li>
		</ul>
	</li>
	<li><h3>Utils</h3>
		This class has static methods and attributes used without any instance for this class by all the others Classes. 
		<ul>
			<li>ANSI_COLOR_CODES</li>
			<li><h4>MonthToInt</h4>Given a string, returns an integer from 0 to 11, or -1.</li>
			<li><h4>Intro</h4>KAE-SC logo, displayed on the boot-up.</li>
			<li><h4>Menu</h4>the main's possible choices.</li>
			<li><h4>convertDecimalFormat</h4> converts a double value to a string, cutting it to 2 or 1 decimal values</li>
		</ul>
		It also has the <em>ANSI colour codes</em> used by either Salary and Salarycheck_main.
	</li>
	<li><h3>switch Manager Utils</h3>
	This class has static methods used by the switch manager, these are the main features.
		<ul>
			<li><h4>addSalary</h4></li> 	Adds a Month Salary 
			<li><h4>removeSalary</h4></li> 	Removes a Month Salary //Unused
			<li><h4>addExpense</h4></li> 	Adds an expense to a specific Month
			<li><h4>removeExpense</h4>	Removes an expense given its ID</li>
			<li><h4>printAll</h4>		Prints all the data related to all the Years</li>
			<li><h4>printYear</h4>		Prints all the data related to a specific Year</li>
			<li><h4>loadData</h4> 		Loads from write.txt file (overwrites actual data)</li>
			<li><h4>saveData</h4> 		Saves actual data into write.txt (overwritten each time) and also creates a unique backup file each time</li>
			<li><h4>showStats</h4> 		Displays stats for each month, giving a percentage representation for expenses and gains</li>
			<li><h4>printByCategories</h4>	Displays expenses grouped by Categories, per month, ordered by amount</li>
		</ul>
	</li>
</ul>

<h2>EXCEPTION</h2>
		WORK IN PROGRESS
<ul>
	<li>GenericException
	GenericException description<br>
	</li>
	<!-- <li></li> -->

</ul>
<h2>MAIN</h2>
<ul>
	<li>SalaryChecker_main
	Has an <b>ArrayList</b> for every year (12 months, <Integer> key (1 aka January, 2 February and so on...), <Salary> value))<br>
	Has a method (monthToInt) used to convert String month names to Integers (which returns -1 if the month name is not valid), and a menù method to show all the options.<br>
		The above methods are both static, as they are called inside the main.<br>
		<h3>MENU'</h3>
	<br>The menù contains several options:
				<p align="center">
					<img src="https://i.imgur.com/6CDSsgU.png">
				</p>
		<ul>
			<li><b>Add Salary</b><br>
			Given a month name (e.g. November), an amount and an Year, through the Utils.MonthToInt method, the main allocates the Salary in its position in the HashMap(e.g. November = 11):
			<p align="center">
					<img src="https://i.imgur.com/vqnhD3J.png">
				</p>
			If the inserted month already exists (the place into the HashMap is already assigned), it will be resetted with the new parameters.
			<br>In other words, if a month needs to be erased,  the add functionality can be used in order to reinitialize it.</li>
			<li>Add Expense<br>
			Given an amount, a description, a month and an year, the expense is added (subtracted) to the Salary which has the same month and year.
			<p align="center">
					<img src="https://i.imgur.com/ATIkRPh.png">
				</p></li>
			<li>Remove Expense<br>
				Given a month name (and a Year), followed by an ID (visible in the Print), the specific expense is deleted.
			<br>To snchronize all the data, after the remove it follows a write into the file, to save the occured changes.
			<p align="center">
					<img src="https://i.imgur.com/ujqRg8n.png">
				</p></li>
			<li>Print all the months for all the registered years, also giving data for expenses and gains in total and for each year.
			<p align="center">
					<img src="https://i.imgur.com/SmHqdSf.png">
				</p></li>
			<li>Print all the months for the selected year
			<p align="center">
					<img src="https://i.imgur.com/hE2PXzW.png">
				</p></li>
			<li>Loads from write.txt file (overwrites actual data)<br>
				Read a txt file importing all the previously saved elements.
				<p align="center">
					<img src="https://i.imgur.com/4uyujaS.png">
				</p>
			</li>
			<li>Saves actual data into write.txt (overwritten each time) and also creates a unique backup file each time<br>
			Writes all the data in a txt file, in order to retrieve those through the readingFile method.<br>Also creates a backup file each time
				<p align="center">
					<img src="https://i.imgur.com/wEVjkU7.png">
				</p>
			</li>
			<li>Show stats for each month, giving a percentage representation for expenses and gains<br>
			Displays the percentages associated with each month, in terms of gain and expenses, through the showStats method.	
			<p align="center">
					<img src="https://i.imgur.com/Mz4cc1c.png">
				</p></li>
			<li>Expenses per mmonth, grouped by Categories and ordered by amount<br>
			Displays expenses grouped by categories ordering for amount, for each month.	
			<p align="center">
					<img src="https://i.imgur.com/iysaLYL.png">
				</p></li>
			<li>Expenses per year, grouped by Categories and ordered by amount<br>
			Displays expenses grouped by categories ordering for amount, for each year.	
			<p align="center">
					<img src="https://i.imgur.com/butBDOo.png">
				</p></li>
			<li>Menù<br>
			Prints all the available options
			</li>
		</ul>
	</li>
</ul>
</section>
	
