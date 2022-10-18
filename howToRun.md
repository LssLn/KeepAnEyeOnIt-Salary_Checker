<h2>HOW TO RUN KAE-SC</h2>
KAE-SC can be run via jar or exe file.
KAE-SC is developed to be used as a command-line application, and needs to have ANSI colors enabled. In windows you can enable colors in CMD with this simple registry edit: <br>
<code>reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f</code><br>
To know more on how to enable colors in CMD, see <em>/scripts/</em>
<ul>
  <li><h3>JAR</h3>
    After having imported the project in your IDE of choice, export a runnable JAR.
    <br>
    Thus make sure the jar is located at the root folder of the project (in the same folder where <em>/src/</em> and <em>readme.md</em> are located).
    <br>Open CMD and then launch the jar with the following command:
    <code>java -jar file.jar</code>
  </li>
  <li><h3>EXE</h3>
    After having downloaded the project and the EXE, make sure the jar is located at the root folder of the project (in the same folder where <em>/src/</em> and <em>readme.md</em> are located).
    <br>Then launch the EXE file.
  </li></ul>
