<h1>Keep an Eye on It: SALARY CHECKER</h1>
<p align="center">
<img src="https://i.imgur.com/xkLdpZI.png">
</p>
<section>Don't want to drain your salary as quick as you use to do?<br><b>Keep an Eye on It: Salary Checker</b> is a Java project developed to better manage your monthly expenses.<br>
<br>
<ul>
	<li>Classes</li>
	<li>Exceptions</li>
	<li>Main & ShowCase</li>
</ul>

<br>
<h2>CLASS</h2>
<ul>
	<li>Outcome<br>
	Each expense has an amount and a description.</li>
	<li>Salary<br>
	Each Salary has a single income, and several outcomes.<br>All the outcomes are stored in an HashMap with key: Integer (auto incrementing id) and a value (outcome).
	<br>|<br>|<br>
	<em>Future_Feature</em>:	
		<ul>
			<li>add automatic conversion from PDF (paycheck) to setting Salary values.</li>
			<strike><li>Modify the HashMap to key Integer, value Outcome; where OutCome is a class with inside Double prize, Description, Date. Integer is solely the id used to identify that outcome.</li></strike><br>
			<li>Insert controls: 
				<ul>
					<strike><li>Months check: all the input month strings are converted to UpperCase, and if an entered month is not valid, the console asks for it again.--> Solved: month.toUpperCase is used after I/O, and a do/while is used to be sure the MonthToInt method is taken care of in case it returns -1 (invalid string parameter)</li></strike>
					<strike><li>Only a Salary can exist with the same month and year.</li><br>--> Solved: An element with the same slot into the HashMap will replace the old one, deleting the old expenses too. The "Save data (menù's 6th option) exists in order to avoid data loss due to unintentionally overwriting.</strike>
					<li>Expenses must be added only to existing months.<br> Mabe add an Integer array for months that exist and check if the expenses has a month "existing" (expenses month is not contained in Integer array)</li>
				</ul>
			</li>
		</ul>	
	</li>
	<li>FileReaderAndWriter<br>
	It gives persistence to the program by loading all the already saved values and by storing the new ones into files that can be used as the project storage system.
	<br>It has 2 methods:
		<ul>
			<li><h4>writingHashMap</h4>
			Given an HashMap, the method iterates through it and saves all its data in a txt file, created in order to save the data and making therefore possible for the readingFile method to load the already existing data.
			<br>Everytime it's called, it overwrites the txt file deleting the old content.</li>
			<li>readingFile
			Given an already existing txt file written by the previous "writingHashMap" method, it saves the txt file data into the HashMap which contains its entries (the month-income and its related expenses).</li>
		</ul>
		</li>
	<strike><li>monthsUtil<br>
		This class has the "MonthToInt" method used by the main and the fileHandler class.
	</li></strike>
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
	<li>SalaryChecker_Test
	Has an <b>HashMap</b> for every year (12 months, <Integer> key (1 aka January, 2 February and so on...), <Salary> value))<br>
	Has a method (monthToInt) used to convert String month names to Integers (which returns -1 if the month name is not valid), and a menù method to show all the options.<br>
		The above methods are both static, as they are called inside the main.<br>
		<h3>MENU'</h3>
	<br>The menù contains several options:
		<ol start="1">
			<li><b>Add Salary</b><br>
			Given a month name (e.g. November), through the MonthToInt method, the main allocates the Salary in its position in the HashMap(e.g. November = 11):
			<p align="center">
					<img src="https://i.imgur.com/fMQwb6s.png">
				</p>
			If the inserted month already exists (AKA: the place into the HashMap is already taken), it will be resetted with the new parameters.
			<br>In other words, if a month needs to be erased,  the add functionality can be used in order to reinitialize it.</li>
			<li>Add Expense<br>
			Given an amount, a description and a month, the expense is added (subtracted) to the Salary which has the same month.
			<p align="center">
					<img src="https://i.imgur.com/sPX0DYc.png">
				</p></li>
			<li>Print all the months for the year:
			<p align="center">
					<img src="https://i.imgur.com/SLdMbQL.png">
				</p></li>
			<li>Menù<br>
			Prints all the possibilities</li>
			<li>Read from file<br>
				Read a txt file importing all the previously saved elements.</li>
			<li>Write file<br>
			Writes all the data in a txt file, in order to retrieve those.
				<p align="center">
					<img src="https://i.imgur.com/1dsmeBR.png">
				</p>
			</li>
		</ol>
	</li>
</ul>
</section>
	
