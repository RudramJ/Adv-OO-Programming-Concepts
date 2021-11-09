
NumberCounter.java -> HW 8.1
The input(File name) to this program can be given using command line arguements or the standard input
The corresponding input files are also provided in this folder:
"Lottery_Pick_10_Winning_Numbers__Beginning_1987.csv" and "Lottery_Pick_10_Winning_Numbers__Beginning_1987.csv.gz"


Visual.java -> HW 8.2
The input(File name) to this program can be given using command line arguements or the standard input
The corresponding input files are also provided in this folder:
"pi6.txt" and "pi6.gz"


Password.java -> HW 8.3
PasswordWrite.java -> HW 8.3
PasswordRead.java -> HW 8.3
Modify.java -> HW 8.3

To run HW 8.3 follow the excution in the following order:
1. Compile and run the PasswordWrite.java (This creates serialized file named "1234.ser" which stores the password)
2. Compile and run the PasswordRead.java (This reads from serialized file named "1234.ser" which contains password)
The password is read successfully
3. Compile and run the Modify.java (This creates serialized file named "Modify.ser" which stores the modified password)
4. Run the .cmd file "modify.cmd" (This copies the content of file "Modify.ser" to "1234.ser")
5. Now, again Compile and run the PasswordRead.java (This reads from serialized file named "1234.ser" which now contains modified password)
The password is read successfully
(If the password did not matched with the original password then password will be printed as "")

Compile all the files using javac and also "-Xlint" for HW 8.1 and run the files referring from this readme file
