import javax.swing.JOptionPane;
public class PalindromeTest {

	private static boolean isPalin; //main boolean for palindrome test
	private static String palindrome; //strings for int and binary palindrome tests
	private static String newPalin=""; 
	private static int count1=0; //primitive operation counters
	private static int count2=0;
	private static int count3=0;
	
	public static void main(String[] args) 
	{
		String ans=JOptionPane.showInputDialog("Type '1' if you would like to check a number, "
				+ "Type '2' to run a count test"+" \nOr '3' to see how many times both a decimal and binary"
				+ " number occur \nas a palindrome in both representations");//asks user what they would like to check
		int choice=Integer.parseInt(ans); //converts the answer an int to choose which method
		if(choice==1) 
		{
			userInput(); //used if a user wants to test a specific number
		}
		else if(choice==2) 
		{
			String test=JOptionPane.showInputDialog("Enter number of tests to run");
			int no=Integer.parseInt(test);//used to take in a no. of tests for a user to run
			countTest(no);
		}
		else if(choice==3)
		{
			JOptionPane.showMessageDialog(null, "No. of palindromes in both instances= "+decBinPalin());
			//this counts the number of instances a palindrome exists in both integer and binary form
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Invalid choice, please choose 1,2 or 3");
			//ends if a valid option isn't chosen
		}
		
	}
	
	public static void userInput() 
	{
		palindrome=JOptionPane.showInputDialog("Please Enter string to test Palindrome");
		//asks user to enter an array to test if palindrome
		System.out.println("Entered:");
		System.out.println(palindrome);
		JOptionPane.showMessageDialog(null, "Method one result = "
				+methodOne(palindrome)+"\nMethod two result = "
				+methodTwo(palindrome)+"\nMethod three result= "
				+methodThree(palindrome));//sends methods and prints out results
		newPalin=decimalToBinary(palindrome);//converts to binary to check binary palindrome too
		JOptionPane.showMessageDialog(null,"Binary conversion ="+newPalin);
		JOptionPane.showMessageDialog(null, "Method one result = "
				+methodOne(newPalin)+"\nMethod two result = "
				+methodTwo(newPalin)+"\nMethod three result= "
				+methodThree(newPalin));//sends methods and prints out results
	}
	
	public static void countTest(int no) 
	{
		long start=System.currentTimeMillis();//takes start time
		for(int i=0;i<=no;i++)  //runs loop for method one test for operations and time
		{
			palindrome="";//need to empty string at the start of each loop
			palindrome+=i;//puts number on string
			newPalin=decimalToBinary(palindrome);//converts to binary
			methodOne(palindrome);
			methodOne(newPalin);
			
		}
		long afterMethodOne= System.currentTimeMillis();//takes current time and gets time taken
		System.out.println("Time taken for method one= "+(afterMethodOne-start));
		System.out.println("Number of primitive operations executed= "+count1);
		for(int i=0;i<=no;i++) //tests for method two
		{
			palindrome="";//need to empty string at the start of each loop
			palindrome+=i;//puts number on string
			newPalin=decimalToBinary(palindrome);//converts to binary
			methodTwo(palindrome);
			methodTwo(newPalin);
			
		}
		long afterMethodTwo= System.currentTimeMillis();//time taken for method two
		System.out.println("Time taken for method two= "+(afterMethodTwo-afterMethodOne));
		System.out.println("Number of primitive operations executed= "+count2);
		for(int i=0;i<=no;i++) //test for method three
		{
			palindrome="";//need to empty string at the start of each loop
			palindrome+=i;//puts number on string
			newPalin=decimalToBinary(palindrome);//converts to binary
			methodThree(palindrome);
			methodThree(newPalin);
		}
		long afterMethodThree= System.currentTimeMillis();//time taken for method 3
		System.out.println("Time taken for method three= "+(afterMethodThree-afterMethodTwo));
		System.out.println("Number of primitive operations executed= "+count3);
		long finish= System.currentTimeMillis();
		System.out.println("time taken for whole test in seconds = "+(finish-start));
	}
	
	public static int decBinPalin() //this method counts how many instances where both decimal and binary
	{//are both palindromes
		int no=0; //initialises required variables
		int t=1000000;
		boolean dec;
		boolean bin;
		for(int i=0;i<=t;i++) 
		{
			palindrome=""; //clears string and converts int to string
			palindrome+=i;
			newPalin=decimalToBinary(palindrome);//converts decimal to binary string
			dec=methodOne(palindrome); //sets holders to true or false
			bin=methodOne(newPalin);
			if((dec==true)&&(bin==true)) //if both true then counter increases
			{
				no++;
			}
		}
		//for this test I only used one method as it is only counting no. of instances so 
		//it doesn't matter which one is used
		return no;
	}
	
	public static boolean methodOne(String palin) //takes in string for testing
	{
		String test="";//initialise a test string for comparison
		count1++;
		isPalin=false; //set to false before hand and changes if it is a palindrome
		count1++;
		for(int i=palin.length()-1;i>=0;i--) 
		{
			count1=count1+i;//count for indexing, this is for worst case scenario
			test=test+palin.charAt(i); //reverses the array
			
		}
		if(palin.equals(test))
		{
			count1=count1+palin.length();//this would be worst case scenario for testing equality between strings
			//so we add the length of the string to the count for each check
			isPalin=true;//sets to true if the strings are equal ie.palindrome
			count1++;
		}
		count1++;
		return isPalin;
	}
	public static boolean methodTwo(String palin) 
	{
		int length = palin.length()-1;
		count2++;
		isPalin=true; //setting to true here because will go false if found to be false 
		count2++;
		for(int i=0;i<palin.length()-1;i++) 
		{
			char one = palin.charAt(i);//assigns the characters from beginning and start to these chars
			count2++;
			char two = palin.charAt(length-i);
			count2++;
			if(one!=two) 
			{
				isPalin = false; //if they are not equal boolean is set to false and the loop is broken
				count2++;
				break;
			}
		}
		count2++;
		return isPalin;
	}
	public static boolean methodThree(String palin) 
	{
		Stack st = new ArrayStack(100);
		count3++;
		Queue q = new ArrayQueue(100);
		count3++;
		isPalin=true;//set to false when a mismatch is found in if statement
		count3++;
		for(int i=0;i<palin.length();i++) 
		{
			q.enqueue(palin.charAt(i)); //pushes all chars onto the stack and queue
			count3++;
			st.push(palin.charAt(i));
			count3++;
		}
		for(int i=0;i<palin.length();i++) 
		{
			char one = (char) q.dequeue();//assigns chars popped to these char holders
			count3++;
			char two = (char) st.pop();
			count3++;
			
			if(one!=two) 
			{
				isPalin=false;//if they are not the same then boolean is set to false and loop broken
				count3++;
				break;
			}
		}
		count3++;
		return isPalin;
	}
	public static String decimalToBinary(String palin) //method for binary conversion
	{
		String binary="";
		int no = Integer.parseInt(palin); //converts to int 
		int binHolder[] = new int[100]; //array to hold binary
		int i = 0;
		
		while(no>0) 
		{
			binHolder[i]=no%2; //gets modulus of entered number
			i++;
			no=no/2;
		}
		for(int j=i-1;j>=0;j--) //loop to populate a string with the binary conversion
		{
			binary+=binHolder[j];
		}
		
		return binary;
	}

}
