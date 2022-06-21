:: ** PROVIDED BY https://www.codeproject.com/Tips/5255355/How-to-Put-Color-on-Windows-Console ** 
:: to add colors to cmd
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f
:: to undo add colors to cmd
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000000 /f
