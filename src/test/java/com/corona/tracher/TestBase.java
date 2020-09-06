package com.corona.tracher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public static Properties properties;
    public TestBase() {
	 try 
	 {
		 properties=new Properties();
		 FileInputStream fis=new FileInputStream(".\\src\\test\\java\\Config.properties");
		 properties.load(fis);
	 }
	 catch(FileNotFoundException e)
	 {
		 e.printStackTrace();
	 }
	 catch(IOException e)
	 {
		 e.printStackTrace();
	 }
    }
}
