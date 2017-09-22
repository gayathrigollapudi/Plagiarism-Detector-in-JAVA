import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Reading_Frequency
{
	public static String words_array[]=new String[50];
	public static ArrayList<String> al=new ArrayList<>();
	public static HashMap<String,Integer> hm=new HashMap<String,Integer>();
	public static float e_norm;

	public static void Finding_words(File f) 
	{
		try
		{
			Scanner sc=new Scanner(f);
			String str,word;			
			al.clear();
			while(sc.hasNextLine())       
			{				
				str=sc.nextLine();     //reading a line from file
				str=str.toLowerCase();    //converting string to lowercase
				//System.out.println(str);
				word="";
				for(int i=0;i<str.length();i++)
				{
					if((str.charAt(i)>96 && str.charAt(i)<123) || (str.charAt(i)>47 && str.charAt(i)<58) || str.charAt(i)=='_')
					{
						word+=""+str.charAt(i);   //removing special characters
					}
					else if(str.charAt(i)==' ')
					{
						al.add(word);             //adding each word to arraylist
						//System.out.println(word);
						word="";
					}
				}	
				al.add(word);
			}
			//System.out.println(al);
			//Finding_Frequency();
			
			sc.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}				
	}
	public static HashMap Finding_Frequency(ArrayList<String> al)     //finding frequency of words
	{
		hm.clear();
		for(int i=0;i<al.size();i++)
		{
			//System.out.println(al.get(i));
			hm.computeIfPresent(al.get(i).toString(),(k,v)->v+1);   
			hm.computeIfAbsent(al.get(i).toString(),k->1);
		}
		return hm;
	}
	public static float E_Norm(HashMap<String,Integer> hm) //calculating euclidean norm for each file
	{
		int sum=0;
		e_norm=0;
		for(Integer i: hm.values())
		{
			sum+=(i*i);
		}
		//System.out.println(sum);
		e_norm=(float)Math.sqrt(sum);
		//System.out.println(e_norm);
		return e_norm;
	}
}
