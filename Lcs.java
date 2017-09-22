import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import sun.applet.Main;

class Lcs
{
	public static void Lcs_plagiarism()
	{
		Reading_files rf=new Reading_files();    // creating object to Reading_files class
		//rf.Finding_Files();
		//rf.Nooffiles();
		float plagiarism;				//variable to store plagiarism percentage
		String s1,s2;                   // to store string of each file after removing spaces
		System.out.println("******************************* LCS ******************************");
		System.out.printf("--files--");
		for(int i=0;i<rf.nooffiles;i++)         //printing file names in a row
		{
			System.out.printf(" %11.8s",rf.file_names[i]);
		}
		int row,col;                         //variable to represent rwo and column numbers of matrix
		for(row=0;row<rf.nooffiles;row++)
		{
			System.out.println();
			System.out.printf("%8s",rf.file_names[row]);      //printing file names in a column
	        for(col=0;col<rf.nooffiles;col++)
	        {
	        	if(row==col)               //if row column files are same print plagiarism percent 100
	        	{
	        		System.out.printf("%12s","100");
	        	}
	        	else
	        	{
	        		s1="";
	        		s2="";
	        		s1=Remove_spaces(rf.files_path[row]);     //removing spaces and special characters in first file
	        		s2=Remove_spaces(rf.files_path[col]);     //removing spaces and special characters in second file
	        		//System.out.println(s1);
	        		//System.out.println(s2);
	        		substring_String(s1,s2);	        		//finding longest substring of two files
	        	}
	        }
		}
		System.out.println();
	}
	public static String Remove_spaces(File f)    //removing spaces and special characters
	{
		String s="";
		try
		{
			Scanner sc=new Scanner(f);
			String str,word;				
			while(sc.hasNextLine())
			{				
				str=sc.nextLine();
				str=str.toLowerCase();     //converting string to lower case
				for(int i=0;i<str.length();i++)
				{
					if((str.charAt(i)>96 && str.charAt(i)<123) || (str.charAt(i)>47 && str.charAt(i)<58) || str.charAt(i)=='_')
					{
						s+=""+str.charAt(i);
					
					}
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return s;
	}
	public static void substring_String(String s1,String s2)
	{
		 int le,le1;
		 float plagiarism=0;
		 le=s1.length();   //length of first file string
		 le1=s2.length();	//length of second file string	   
		 int lcs=0;
		 ArrayList<Character> a=new ArrayList<>();
		 int i,j,t,k;
		 a.clear();
	    for(i=0;i<le;i++)
	    {
	        for(j=0;j<le1;j++) //finding highest substring

	        {
	            a.clear();
	            k=0;
	            t=i;
	            while(i<le && j<le1 && s1.charAt(i)==s2.charAt(j))
	            {
	              a.add(s1.charAt(i));
	                i++;
	                j++;
	            }
	            if(lcs<a.size())
	            {
	            	lcs=a.size();
	            }
	            i=t;
	        }
	    }
	    plagiarism=(int)(((float)(lcs*2)/(le+le1))*100);        //finding plagiarism percent
	    //plagiarism=lcs*2;
	    System.out.printf(" %11.2f",plagiarism);                
	}
}
