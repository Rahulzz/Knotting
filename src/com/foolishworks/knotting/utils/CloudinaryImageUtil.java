package com.foolishworks.knotting.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.foolishworks.knotting.constants.KnottingConstants;

@Component
public class CloudinaryImageUtil extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(CloudinaryImageUtil.class);

	Map<String, String> cloudinaryConfig = new HashMap<String, String>();

	public CloudinaryImageUtil(){
		cloudinaryConfig.put("cloud_name", cloudinaryName);
		cloudinaryConfig.put("api_key", cloudinaryKey);
		cloudinaryConfig.put("api_secret", cloudinarySecret);
	}

	@SuppressWarnings("rawtypes")
	public String uploadServiceImage(File file) throws IOException {

		slf4jLogger.debug("Entering function {}", "uploadServiceImage");
		
		Cloudinary cloudinary = new Cloudinary(cloudinaryConfig);
		Map<String,Object> addtionalOption = new HashMap<String,Object>();
		Map uploadResult = cloudinary.uploader().upload(file, addtionalOption);

		slf4jLogger.debug("Exiting function {}", "uploadServiceImage");
		
		return uploadResult.get("url").toString();
	}
	
	@SuppressWarnings("unchecked")
	public boolean deleteImage(String url) throws IOException{

		slf4jLogger.debug("Entering function {}", "deleteImage");

		Map<String, Boolean> deleteConfig = new HashMap<String, Boolean>();
		deleteConfig.put("invalidate", true);
		
		String[] imageUrlParts = url.split("/");
		String publicId = imageUrlParts[imageUrlParts.length - 1].split("\\.")[0];
		
		Cloudinary cloudinary = new Cloudinary(cloudinaryConfig);
		Map<String, String> deleteResult = cloudinary.uploader().destroy(publicId, deleteConfig);
		
		for(Map.Entry<String, String> entry : deleteResult.entrySet()){
			System.out.println("Key : "+entry.getKey()+"  -  Value : "+entry.getValue());
		}

		slf4jLogger.debug("Exiting function {}", "deleteImage");
		
		return true;
	}
	
	public String thumbnailUrl(String imageUrl) {

		slf4jLogger.debug("Entering function {}", "thumbnailUrl");
		
		StringBuilder transformedImage = new StringBuilder();
		String[] imageUrlComponents = imageUrl.split(imageTransformationDelimiter);
		transformedImage.append(imageUrlComponents[0]).append(imageTransformationDelimiter).append(transformationAlgorithm).append(transformationSeparator).append(imageUrlComponents[1]);

		slf4jLogger.debug("Exiting function {}", "thumbnailUrl");
		
		return transformedImage.toString();
		
	}
}
