
public class Plagirism {

	public static void main(String[] args)
	{
		Reading_files.BOW_plagiarism();		//Calling bag of words
		Lcs.Lcs_plagiarism();               //Calling lcs
		FingerPrint fp=new FingerPrint();
		fp.Fingerprint_plagiarism();        //Calling fingerprint
		
	}

}
