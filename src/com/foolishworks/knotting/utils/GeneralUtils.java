package com.foolishworks.knotting.utils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.foolishworks.knotting.constants.KnottingConstants;

@Component
public class GeneralUtils extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(GeneralUtils.class);

	public String encryptString(String unencryptedData) throws NoSuchAlgorithmException{

		slf4jLogger.debug("Entering function {}", "encryptString");

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(unencryptedData.getBytes());	        
		byte byteData[] = md.digest();
		StringBuffer encryptedBuffer = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			encryptedBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		slf4jLogger.debug("Exiting function {}", "encryptString");

		return encryptedBuffer.toString();
	}

	public String encryptStringUsingPassword(String unencryptedData){

		slf4jLogger.debug("Entering function {}", "encryptStringUsingPassword");

		String encrtyptedData;

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(encryptionPassword);
		encrtyptedData = encryptor.encrypt(unencryptedData);

		slf4jLogger.debug("Exiting function {}", "encryptStringUsingPassword");

		return encrtyptedData;
	}

	public String decryptStringUsingPassword(String encryptedData){

		slf4jLogger.debug("Entering function {}", "decryptStringUsingPassword");

		String unenctyptedData;

		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword(encryptionPassword);
		unenctyptedData = decryptor.decrypt(encryptedData);

		slf4jLogger.debug("Exiting function {}", "decryptStringUsingPassword");

		return unenctyptedData;
	}

	public void deleteFolder(File file) throws IOException{

		slf4jLogger.debug("Entering function {}", "deleteFolder");

		if(file.isDirectory()){
			if(file.list().length==0){
				file.delete();
				slf4jLogger.debug("Directory is deleted : " + file.getAbsolutePath());

			}else{

				//list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					//construct the file structure
					File fileDelete = new File(file, temp);

					//recursive delete
					deleteFolder(fileDelete);
				}

				//check the directory again, if empty then delete it
				if(file.list().length==0){
					file.delete();
					slf4jLogger.debug("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		}else{
			//if file, then delete it
			file.delete();
			slf4jLogger.debug("File is deleted : " + file.getAbsolutePath());
		}

		slf4jLogger.debug("Exiting function {}", "deleteFolder");
	}

	public Integer[] generateRandomNumber(int length){

		slf4jLogger.debug("Entering function {}", "generateRandomNumber");
		
		Integer[] arr = new Integer[length];
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = i;
	    }
	    Collections.shuffle(Arrays.asList(arr));

		slf4jLogger.debug("Exiting function {}", "generateRandomNumber");
		
		return arr;
	}

	public List<Long> convertStringToLongList(String listSource){

		slf4jLogger.debug("Entering function {}", "convertStringToLongList");
		
		List<Long> longList = null;
		
		if(listSource != null && !"".equals(listSource)){
			longList = new ArrayList<Long>();
			String[] items = listSource.split(",");
			for(String item : items){
				try{
					Long longItem = new Long(item);
					longList.add(longItem);
				}
				catch(Exception exception){}
			}
		}

		slf4jLogger.debug("Exiting function {}", "convertStringToLongList");
		
		return longList;
	}

	public String generateRandomNumericPassword(int length){

		slf4jLogger.debug("Entering function {}", "generateRandomNumericPassword");
		
		StringBuilder password = new StringBuilder();
		
		// Using numeric values
        String numbers = "0123456789";
 
        // Using random method
        Random rndm_method = new Random();
 
        for (int i = 0; i < length; i++)
        {
        	password.append(String.valueOf(numbers.charAt(rndm_method.nextInt(numbers.length()))));
        }

		slf4jLogger.debug("Exiting function {}", "generateRandomNumericPassword");
		
		return password.toString();
	}
	
}
