<h1>Keep an Eye on It: SALARY CHECKER</h1>
<p align="center">
<img src="https://i.imgur.com/Y4x7mPJ.png">

<img src="https://i.imgur.com/c7N21W6.png">
</p>

<section>Don't want to drain your salary as quick as you use to do?<br><b>Keep an Eye on It: Salary Checker</b> is a Java project developed to better manage your monthly expenses through the years.<br>
<br>
<ul>
	<li>Class</li>
	<li>Config</li>
	<li>Exception</li>
	<li>Output</li>
	<li>Main</li>
</ul>

<br>
<h2>CLASS</h2>
<ul>
	<li><h3>Outcome</h3>Each expense has an amount, a category, an id and a description.</li>
	<li><h3>Salary</h3>Each Salary has a single income, and several outcomes.<br>All the outcomes are stored in an HashMap with key: Integer (auto incrementing id) and a value (outcome).<br>It has these methods worth mentioning:
		<ul>
			<li><h4>printOutcomeHashMap</h4>Prints the outcomes stored in the Salary Hashmap.</li>
			<li><h4>setSingleOutcome</h4></li>
            <li><h4>editSingleOutcome</h4>Several methods to edit a single expense description, outcome or category</li>
			<li><h4>incomeGraphics</h4>Used to better display the Salary data.</li>
			<li><h4>printSalary</h4>Displays all the infos related to a Salary, such as income, gain and expenses value, description, n_ID, category</li>
			<li><h4>printSalaryFiltered</h4>Displays all the infos related to a Salary, such as expenses value, description, category</li>
			<li><h4>printOutcomesHashMapFilter</h4>Displays all the infos related to the outcome</li>
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
			<li><h4>deleteBackup</h4>
			Given a folder with different txt files, each named in the format YYYY_MM_DD_hh_mm_ss.txt, a list is created with all the files name. <br>The list is then reverse-sorted, and with the help of the list all the files, except for the first N (most recents), are deleted.</li>
			<li><h4>writeCategoriesToTXT</h4>
			Given HashMap<String,String> categoriesMap, writes CategoriesCFG file with CODE, DESCR entries.</li>
			<li><h4>readCategoriesFromTXT</h4>
			Given a txt file CATEGORIES, which consists of entries of the type CODE(<String>) DESCR(<String>). Returns an HashMap (key: CODE <String>, value: DESCR <String>)</li>
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
			<li><h4>doubleIsNumeric</h4>returns true if the String is a valid double, false otherwise.</li>
			<li><h4>integerIsNumeric</h4>returns true if the String is a valid integer, false otherwise.</li>
			<li><h4>hasComma</h4>returns true if the String contains "comma" character, false otherwise.</li>
			<li><h4>hasDot</h4>returns true if the String contains "dot" character, false otherwise.</li>
			<li><h4>checkCategories</h4> Given a category code, returns true if categoriesMap has no equal code to categoryCode, or returns false if categoriesMap has at least a code equal to categoryCode</li>
			<li><h4>capitalizeFirstLetter</h4>returns the string with the first letter capitalized</li>
			<li><h4>capitalizeFirstLetterAfterLowerCase</h4>returns the string with the first letter capitalized after lower case</li>
			<li><h4>cursor</h4>prints the cursor for the menu input choice (unused as of now)</li>
		</ul>
	</li>
	<li><h3>switch Manager Utils</h3>
	This class has static methods used by the switch manager, these are the main features.
		<ul>
			<li><h4>addSalary</h4></li> 	Adds a Month Salary 
            <li><h4>modifySalary</h4>Given the year and the month, edits a salary income with a new value</li>
			<li><h4>addExpense</h4></li> 	Adds an expense to a specific Month
			<li><h4>modifyExpense</h4>	Given the year and the month, edits a salary income with a new value</li>
			<li><h4>printAll</h4>		Prints all the data related to all the Years</li>
			<li><h4>printYear</h4>		Prints all the data related to a specific Year</li>
			<li><h4>loadData</h4> 		Loads from write.txt file (overwrites actual data)</li>
			<li><h4>saveData</h4> 		Saves current data into write.txt and it creates a backup file, for a maximum of 5 backups (through the FileHandler method deleteBackup)</li>
			<li><h4>showStats</h4> 		Displays stats for each month, giving a percentage representation for expenses and gains</li>
			<li><h4>printByCategories</h4>	Displays expenses grouped by Categories, per month, ordered by amount</li>
			<li><h4>insertCategoriesIntoMap</h4>Given an HashMap<String,String> and the input entry, returns the HashMap with the new element added</li>
            <li><h4>readCategories</h4>Given an HashMap<String,String>, which is populated by reading CategoriesCFG.txt, all the entries CODE DESCRIPTION are displayed.</li>
			<li><h4>modifyExpense</h4>		Given the expense id, the year and the month, edits an expense with several options: 
				<ol>
					<li>Edit expense outcome</li>
					<li>Edit expense description</li>
					<li>Edit expense category</li>
					<li>Remove expense</li>
				</ol>
			</li>
			<li><h4>filterByCategories</h4>		Given the year arraylist and a category in input,returns the same collection but filtered by expenses with the selected category.</li>
			<li><h4>filterByDescription</h4>	Given the year arraylist and a description in input, returns the same collection but filtered by expenses which contains the description in input in their descriptions.</li>
			<li><h4>printAllFilters</h4>		Given an ArrayList<String>, displays all the data related to all the Years but with a different format from printAll</li>
		</ul>
	</li>
</ul>
<h2>CONFIG</h2>
	<ul>
		<li><h3>CategoriesCFG.txt</h3>TXT file in which are stored the categories Code and Description</li>
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
	<li><h2>KAE-SC</h2>
	Has an <b>ArrayList</b> for every year (12 months, <Integer> key (1 aka January, 2 February and so on...), <Salary> value))<br>
	Has a method (monthToInt) used to convert String month names to Integers (which returns -1 if the month name is not valid), and a menù method to show all the options.<br>
		The above methods are both static, as they are called inside the main.<br>
		<h3>Welcome page</h3>
		The menù contains several options:
				<p align="center">
					<img src="https://i.imgur.com/FtTyszs.png">
				</p>
		<ul>
			<li>Add Salary<br>
			Given a month name (e.g. November), an amount and an Year, through the Utils.MonthToInt method, the main allocates the Salary in its position in the HashMap(e.g. November = 11):
			    <p align="center">
					<img src="https://i.imgur.com/0WD0A4C.png">
				</p>
			If the inserted month already exists (the place into the HashMap is already assigned), it will be resetted with the new parameters.
			<br>In other words, if a month needs to be erased,  this functionality can be used in order to reinitialize it.</li>
            <li>Edit Salary<br>
			Given a month and an year, the income is updated with the new value.
			    <p align="center">
					<img src="https://i.imgur.com/w3eqE27.png">
				</p></li>
			<li>Add Expense<br>
			Given an amount, a description,, a category, a month and an year, the expense is added to the Salary which has the same month and year.
			    <p align="center">
					<img src="https://i.imgur.com/UXSmzOB.png">
				</p></li>
			<li>Edit Expense<br>
				Given a month name (and a Year), followed by an ID (visible in the Print), the specific expense can be edited or deleted.
			<br>To snchronize all the data, after the remove it follows a write into the file, to save the occured changes.
			<p align="center">
					<img src="https://i.imgur.com/el6orQC.png">
				</p></li>
			<li><h4>Print all the Years</h4>Print all the months for all the registered years, also giving data for expenses and gains in total and for each year.<br>If a month or a year has a negative ingain, a warning is displayed.
			<p align="center">
					<img src="https://i.imgur.com/TpmHKyr.png">
				</p></li>
				</p></li>
			<li><h4>Print all the Years, filtering by Category</h4>Print all the months for all the registered years, filtering by the input Category.
			<p align="center">
					<img src="https://i.imgur.com/IVNeUaa.png">
				</p></li>
				</p></li>
			<li><h4>Print all the Years, filtering by description</h4>Print all the months for all the registered years, filtering by the given words: each expense which contains the input in the description is displayed.
			<p align="center">
					<img src="https://i.imgur.com/Hal8K5R.png">
				</p></li>
			<li><h4>Print a Year</h4>Print all the months for the selected year.<br>If a month or the year has a negative ingain, a warning is displayed. 
			<p align="center">
					<img src="https://i.imgur.com/RYGm3KU.png">
				</p></li>
			<li><h4>Load data</h4>Loads from write.txt file (overwrites actual data)<br>
				Read a txt file importing all the previously saved elements.
				<p align="center">
					<img src="https://i.imgur.com/EwCX3QE.png">
				</p>
			</li>
			<li>Saves actual data into write.txt (overwritten each time) and also creates a unique <b>backup</b> file each time, to minimize data loss risk.<br>When saving the backup, it also checks if there are already more than seven backups: if that's the case, the older are deleted.<br>
			Writes all the data in a txt file, in order to retrieve those through the readingFile method.
				<p align="center">
					<img src="https://i.imgur.com/wEVjkU7.png">
				</p>
			</li>
			<li><h4>Save current data</h4>Show stats for each month, giving a percentage representation for expenses and gains<br>
			Displays the percentages associated with each month, in terms of gain and expenses, through the showStats method.	
			<p align="center">
					<img src="https://i.imgur.com/DcQtdQi.png">
				</p></li>
			<li><h4>Display statistics</h4>Displays stats for each month, giving a percentage representation for expenses and gains.	
			<p align="center">
					<img src="https://i.imgur.com/1HKFJjv.png">
				</p></li>
            <li><h4>Group months by Categories</h4>Expenses per month, grouped by Categories and ordered by amount<br>
			Displays expenses grouped by categories ordering for amount, for each month.	
			<p align="center">
					<img src="https://i.imgur.com/cUccNla.png">
				</p></li>
			<li><h4>Group years by Categories</h4>Expenses per month, grouped by Categories and ordered by amount<br>
			Displays expenses grouped by categories ordering for amount, for each year.	
			<p align="center">
					<img src="https://i.imgur.com/aq39imF.png">
				</p></li>
			<li><h4>Insert category</h4>Adds a category to the CategoriesCFG.txt file, which is used to store the categories.	
            <li><h4>Display all the categories</h4>Display all the categories stored in the CategoriesCFG.txt file.	
		</ul>
	</li>
</ul>
</section>
