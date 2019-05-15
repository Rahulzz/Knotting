package com.foolishworks.knotting.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
@PropertySource("classpath:config.properties")
public class UrlShortenerUtil {

	@Autowired
	private Environment environment;

	private final Logger slf4jLogger = LoggerFactory.getLogger(UrlShortenerUtil.class);

	public String shortenUrl(String url) throws UnirestException, JSONException{

		slf4jLogger.debug("Entering function {}", "shortenUrl");

		HttpResponse<String> response = Unirest.post("https://www.googleapis.com/urlshortener/v1/url?key="+environment.getProperty("google.url.shortener.key")).header("Content-Type", "application/json").body("{\"longUrl\": \""+url+"\"}").asString();
		JSONObject deliveryResponse = new JSONObject(response.getBody());
		String shortUrl = deliveryResponse.getString("id");

		slf4jLogger.debug("Exiting function {}", "shortenUrl");

		return shortUrl;
	}

}
