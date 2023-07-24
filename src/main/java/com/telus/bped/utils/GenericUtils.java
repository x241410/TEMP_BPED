package com.telus.bped.utils;

import com.test.files.interaction.ReadJSON;
import com.test.logging.Logging;
import com.test.utils.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ***************************************************************************
 * DESCRIPTION: This class contains reusable Java methods AUTHOR: x241410
 * ***************************************************************************
 */

public class GenericUtils {

	private GenericUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Description: The purpose of this method is to fetch the json key value for
	 * given parameters
	 *
	 * @param fileName - name of the json file
	 * @param node     - json node
	 * @param key      - json key
	 * @return String output value
	 */
	public static String getKeyValueFromJsonNode(String fileName, String node, String key) {
		JSONObject obj = null;
		String output = null;

		try {
			JSONObject jsonFile = new JSONObject(ReadJSON.parse(fileName));
			obj = jsonFile.getJSONObject(node);
			output = obj.getString(key);
		} catch (JSONException e) {
			Logging.logReporter(Status.DEBUG, "JSON_EXCEPTION " + e);
		}

		return output;
	}

	/**
	 * Description: The purpose of this method is to get the json object from a json
	 * file
	 *
	 * @param fileName
	 * @return jsonObj
	 */
	public static JSONObject getJSONObjectFromJSONFile(String fileName) {
		JSONObject jsonObj = null;

		try {
			jsonObj = new JSONObject(ReadJSON.parse(fileName));

		} catch (JSONException e) {
			Logging.logReporter(Status.DEBUG, "JSON_EXCEPTION " + e);
		}

		return jsonObj;
	}

	/**
	 * Description: The purpose of this method is to return PST Date time
	 *
	 * @return current system date in mm/dd/yyyy format
	 */
	public static String getDateInMMDDYYYYHHMMSSInPST() {
		Instant instant = Instant.now();
		ZonedDateTime zdtNewYork = instant.atZone(ZoneId.of("America/Los_Angeles"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return zdtNewYork.format(formatter);
	}

	/**
	 * @param jObj
	 * @param val
	 * @return
	 */
	public static int getIndexFromJsonObject(JSONArray jObj, String val) {

		int c = 0;
		for (int i = 0; i < jObj.length(); i++) { // this will iterate through key1 - key3
			String str = jObj.get(i).toString().split(",")[0].split(",")[0].replace("\"", "").replace("[", "");
			if (str.equalsIgnoreCase(val)) {
				c = i + 1;
				break;
			}
		}
		return c;
	}

}
