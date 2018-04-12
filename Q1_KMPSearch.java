package Ass4;

/**
 * @author: Vedanshi Patel
 * Due Date: Sunday, Dec 10th 2017
 * 
 * Purpose: Using the KMP search method, find the string pattern from a text file and show the result that contains that string pattern.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Q1_KMPSearch {
	/**
	 * Method implements KMPSearch algorithm.
	 * @param pattern
	 * @param array
	 */
	public static void KMPSearch(String pattern, String[] array)
	 {
	    String pat=pattern.toLowerCase();
		int m=0;
		for(int z=0; z<array.length; z++) 
	    {
	    	String txt = array[z].toLowerCase();
		 	int M = pat.length();
	     	int N = txt.length();
	     	int lps[] = new int[M];
	     	int j = 0;  
	     	computeLPSArray(pat,M,lps);
	     	int i = 0;  
	     	while (i < N){
	         	if (pat.charAt(j) == txt.charAt(i)){
	             	j++;
	             	i++;
	         	}
	         	if (j == M){
	             	m++;
	             	System.out.println("Search value found in entry :: \t" + array[z]);
	             	break;
	         	}
	         	else if (i < N && pat.charAt(j) != txt.charAt(i)){
	             	if (j != 0)
	                 	j = lps[j-1];
	             	else
	                 	i = i+1;
	         	}
	     	}
	   }
	     if(m==0){
	    	 System.out.println("Search value not found");
	     }
	     
	 }
	 
	/**
	 * Method to create array for LP search.
	 * @param pat
	 * @param M
	 * @param lps
	 */
	 public static void computeLPSArray(String pat, int M, int lps[])
	 {
	     int len = 0;
	     int i = 1;
	     lps[0] = 0; 
	     while (i < M){
	         if (pat.charAt(i) == pat.charAt(len)){
	             len++;
	             lps[i] = len;
	             i++;
	         }
	         else {
	             if (len != 0){
	                 len = lps[len-1];
	             }
	             else{
	                 lps[i] = len;
	                 i++;
	             }
	         }
	     }
	 }
	
	 /**
	  * Main method. 
	  * @param args
	  * @throws FileNotFoundException
	  */
	public static void main(String[] args)throws FileNotFoundException{
		
		File file= new File("C:\\Users\\Ved\\Documents\\Workspace\\COMP5511\\src\\Ass4\\dataToSort.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Scanner scan = new Scanner(file);    
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the Input String to search: ");
		String searchvalue = input.nextLine();
		searchvalue=searchvalue.toLowerCase();
		
		int ctr=0;
		
		try{
			
			while(br.readLine()!=null){
				ctr++;
			}
		String[] words= new String[ctr];	
	  	for(int i=0; i<ctr; i++){
	  		words[i]=scan.nextLine();
//	  		words[i]=words[i].toLowerCase();
	  	}
		KMPSearch(searchvalue,words);
		input.close();
		scan.close();
		br.close();

	}
		catch (Exception e)
		{
			System.out.println("File Doesn't have any Names");
		}
	}

}
