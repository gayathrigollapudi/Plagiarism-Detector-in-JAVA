import java.util.ArrayList;

public class FingerPrint
{
	public void Fingerprint_plagiarism()
	{
		Reading_files rf=new Reading_files(); // creating object to Reading_files class
		Lcs l=new Lcs();				//creating object to Lcs class
		float plagiarism;	//variable to store plagiarism percentage
		String s1,s2;       // to store string of each file after removing spaces
		int common;			// to store no of values common for two files
		System.out.println("******************************* FINGERPRINT ******************************");
		System.out.printf("--files--");
		for(int i=0;i<rf.nooffiles;i++)         //printing file names in a row
		{
			System.out.printf(" %11.8s",rf.file_names[i]);
		}
		int row,col;						//variable to represent rwo and column numbers of matrix
		for(row=0;row<rf.nooffiles;row++)
		{
			System.out.println();
			System.out.printf("%8s",rf.file_names[row]);     //printing file names in a column
	        for(col=0;col<rf.nooffiles;col++)
	        {
	        	if(row==col)									//if row column files are same print plagiarism percent 100 
	        	{
	        		System.out.printf("%12s","100");
	        	}
	        	else
	        	{
	        		s1="";
	        		s2="";
	        		s1=l.Remove_spaces(rf.files_path[row]);  //removing spaces and special characters in first file
	        		s2=l.Remove_spaces(rf.files_path[col]);  //removing spaces and special characters in second file
	        		//System.out.println(s1);
	        		//System.out.println(s2);
	        		ArrayList<Long> a1=new ArrayList<>();	
	        		a1.clear();
	        		a1.addAll(Hashing(s1));						//to store hash values of first file
	        		ArrayList<Long> a2=new ArrayList<>();
	        		a2.clear();
	        		a2.addAll(Hashing(s2));						//to store hash values of second file
	        		//System.out.println(a1);
	        		//System.out.println(a2);
	        		common=0;
	        		common=Common_Values(a1,a2);
	        		//System.out.print(common);
	        		plagiarism=(float)(2*common)/(a1.size()+a2.size());		//calculating plagiarism percent for two files
	        		System.out.printf(" %11.2f",plagiarism*100); 
	        	}
	        }
		}
		System.out.println();
	}
	public ArrayList Hashing(String s) //finding hash values for each file
	{
		ArrayList<Long> a=new ArrayList<>();    //to store hash values
		int i=0;
		a.clear();
		String word;          //to store word of length k_gram
		int k_gram=10;
		long sum;
		//long k;
		while(i<s.length()-9)   
		{
			word=s.substring(i,i+10); //taking substring of k_gram length
			sum=0;
			for(int j=0;j<10;j++)
			{
				sum+=(int)(word.charAt(j))*(Math.pow(k_gram, k_gram-(j+1))); //finding hash value for each word
			}
			a.add(sum%10007);
			i++;
			word="";
		}
		//System.out.println(a);
		return a;
	}
	public int Common_Values(ArrayList<Long> a1,ArrayList<Long> a2)
	{
		int c=0;
		for(int i=0;i<a1.size();i++)      //finding number of common hash values in two files
		{
			if(a2.contains(a1.get(i)))
			{
				c++;
			}
		}
		return c;
	}
}
