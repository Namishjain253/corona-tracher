package com.corona.tracher;

public class ConvertStringToInteger {
	
	 // Function to return the modified string 
public String extractInt(String str) 
    { 
        // Replacing every non-digit number 
        // with a space(" ") 
        str = str.replaceAll("[^\\d]", ""); 
  
        // Remove extra spaces from the beginning 
        // and the ending of the string 
        str = str.trim(); 
  
        // Replace all the consecutive white 
        // spaces with a single space 
        str = str.replaceAll(" +", " "); 
        //int integer = Integer.parseInt(str);
        if (str.equals("")) 
            return "-1"; 
  
        return str; 
    } 

}
