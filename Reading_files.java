import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Reading_files 
{
	public static String file_names[]=new String[10];   //to store file names from directory
	public static int nooffiles;                        //no of text files in directory
	public static File[] files_path=new File[10];
	public static HashMap<String,Integer> hm1;          //to store word and frequency of word of first file
	public static HashMap<String,Integer> hm2;          //to store word and frequency of word of second file
	public static int row,col;
	public static int dp;
	
	public static void BOW_plagiarism()
	{
		Finding_Files();							//to find text files in directory
		Nooffiles();								//to find number of text files in directory
		//System.out.println(nooffiles);
		//Store_File_Object();
		float plagiarism;
		System.out.println("********************************* BAG OF WORDS *********************");
		System.out.printf("--files--");
		for(int i=0;i<nooffiles;i++)         
		{
			System.out.printf(" %11.8s",file_names[i]);     //printing file names in a row
		}
		//System.out.println();	
		float e1,e2;
		for(row=0;row<nooffiles;row++)
		{
			System.out.println();
			System.out.printf("%8s",file_names[row]);     //printing file names in a column
	        for(col=0;col<nooffiles;col++)
	        {
	        	if(row==col)
	        	{
	        		System.out.printf("%12s","100");        //if row column files are same print plagiarism percent 100
	        	}
	        	else
	        	{
	        		Reading_Frequency.Finding_words(files_path[row]);			//finding words in first file
	        		HashMap<String,Integer> hm1=new HashMap<>();
	        		//System.out.println(Reading_Frequency.al);
	        		hm1.putAll(Reading_Frequency.Finding_Frequency(Reading_Frequency.al));	//finding frequency of each word in first file
	        		e1=Reading_Frequency.E_Norm(hm1);          //calculating euclidean norm for first file
	        		
	        		Reading_Frequency.Finding_words(files_path[col]);      //finding words in second file
	        		HashMap<String,Integer> hm2=new HashMap<>();
	        		hm2.putAll(Reading_Frequency.Finding_Frequency(Reading_Frequency.al));	  //finding frequency of each word in second file
	        		e2=Reading_Frequency.E_Norm(hm2);        //calculating euclidean norm for second file
	        		dp=dot_product(hm1,hm2);             //dot product of two files
	        		plagiarism=dp/(e1 * e2);
	        		//System.out.print(e1+" "+e2+" "+dp);
	        		System.out.printf(" %11.2f",plagiarism*100);  //printing plagiarism percent
	        	}
	        }
		}
		System.out.println();
	}
	/*public static void Store_File_Object()
	{
		for(int i=0;i<files_path.length;i++)
		{
			Reading_Frequency rf=new Reading_Frequency();
			rf.Finding_words(files_path[i]);
			hm_array.add(rf.hm);
			norm_array.add(rf.e_norm);
			
		}
		for(int i=0;i<hm_array.size();i++)
		{
			//System.out.println(hm_array.get(i));
			//System.out.println(norm_array.get(i));
		}
		
	}*/
	public static void Finding_Files()
	{
		Scanner sc=new Scanner(System.in);     
		String path=sc.nextLine();           //taking path from user
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();   //taking list of files from directory
		int k=0;		
		//System.out.println(Arrays.toString(listOfFiles));
		for (int i = 0; i < listOfFiles.length; i++)
		{
		  File file = listOfFiles[i];
		  //System.out.println(file);
		  if (file.isFile() && file.getName().endsWith(".txt"))
		  {
			  file_names[k]=file.getName();			  //seperating text file names
			  files_path[k]=file;                   //seperating text files with path
			  //System.out.println(files_path[k]);
			  k++;
		  } 
		}
		//System.out.println(Arrays.toString(files_path));
		//System.out.println(file_names.length);
	}
	public static void Nooffiles()         //counting number of text files in directory
	{
		nooffiles=0;
		for(int i=0;files_path[i]!=null;i++)
		{
			nooffiles++;
		}
		//System.out.println(count);
	}
	public static int dot_product(HashMap<String,Integer> hm1,HashMap<String,Integer> hm2)
	{
		dp=0;
		for(String key1: hm1.keySet())          //finding dot product of two files
		{
			if(hm2.containsKey(key1))
			{
				dp+=(hm1.get(key1) * hm2.get(key1));
			}
		}
		return dp;
	}
}
