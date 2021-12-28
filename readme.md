<h1>Keep an Eye on It: SALARY CHECKER</h1>
<section>Don't want to drain your salary as quick as you use to do?<br>Keep an Eye on It is a Java project developed to better manage your monthly expenses.<br>
<br>
<ul>
	<li>Classes</li>
	<li>Exceptions</li>
	<li>Main</li>
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
				<strike><li>Months check: all the months string are converted toUpperCase, and if an entered month is not valid, the console asks for it again.<br>
					--> Solved: month.toUpperCase is used after I/O, and a do/while is used to be sure the MonthToInt method is taken care of in case it returns -1 (invalid string parameter)</li></strike>
				<li>Only a Salary can exist with the same month and year.</li>
				<li>Expenses must be added only to existing months.</li>
				<li></li>

			</ul></li>
		</ul>	
	</li>
	<!-- <li></li> -->
	<li><em>FileReaderAndWriter</em><br>
	It gives persistence to the program by loading all the already saved values and by storing the new ones into files that can be used as the project storage system.</li>
</ul>

<h2>EXCEPTION</h2>
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
	<br>The menù contains several options:
		<ol start="1">
			<li>Add Salary<br>
			Given a month name (e.g. November), through the MonthToInt method, the main allocates the Salary in its position in the HashMap(e.g. November = 11).</li>
			<li>Add Expense<br>
			Given an amount, a description and a month, the expense is added (subtracted) to the Salary which has the same month.</li>
			<li>Print all the months for the year</li>
			<li>Menù<br>
			Prints all the possibilities</li>
			<li>Read from file<br>
			Read a txt file importing all the previously saved elements.</li>
			<li>Write file<br>
			Writes all the data in a txt file, in order to retrieve those.</li>
		</ol>
	</li>
</ul>
</section>
