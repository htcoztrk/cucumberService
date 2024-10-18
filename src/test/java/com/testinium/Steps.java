package com.testinium;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * The type Steps.
 */
public class Steps{

    protected Logger log = Logger.getLogger(getClass());

    /**
     * The Response.
     */
    Response response;
    /**
     * The Headers.
     */
    Map<String, Object> headers = new HashMap<>();
    /**
     * The JSONObject.
     */
    JSONObject object = null;

    /**
     * Gets request method.
     *
     * @param url the url
     */
    ArrayList<String> array = new ArrayList<>();


    @Before
    public void before(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
    }

    /**
     * Gets request method.
     *
     * @param url the url
     */
    @And("get request to {string}")
    public void getRequestMethod(String url) {
        log.info("get request to " + url);
        response = given().when().get(url);

    }

    /**
     * Check status code.
     *
     * @param code the status code
     */
    @And("check status code {string}")
    public void checkStatusCode(String code) {
        log.info("checked " + code + " equals " + response.getStatusCode());
        Assert.assertEquals(code, String.valueOf(response.getStatusCode()));

    }

    /**
     * Check response method.
     *
     * @param type  the type of response body:headers
     * @param key   the key
     * @param value the value
     */
    @And("check response {string}: {string},{string}")
    public void checkResponseMethod(String type, String key, String value) {
        log.info("check response " + type + ": " + key + "," + value);
        if (type.equals("body")) {
            Assert.assertTrue(value.equals(response.jsonPath().getString(key)), value + " değeri ile" + response.jsonPath().getString(key) + " değeri uyuşmuyor");
        } else {
            Assert.assertEquals(value + " değeri ile" + response.jsonPath().getString(key) + " değeri uyuşmuyor", value.equals(response.headers().getValue(key)));
        }
    }

    /**
     * Add map.
     *
     * @param type  the type
     * @param key   the key
     * @param value the value
     */
    @And("add hash map {string}: {string},{string}")
    public void addMap(String type, String key, String value) {
        log.info("add hash map " + type + ": " + key + "," + value);
        if (type.equals("s")) {
            headers.put(key, value);
        } else if (type.equals("i")) {
            headers.put(key, Integer.parseInt(value));
        } else if (type.equals("b")) {
            headers.put(key, Boolean.parseBoolean(value));
        } else {
            log.info("addMap type bulunamadı.");
        }
    }

    /**
     * Add array.
     *
     * @param str the str
     */
    @And("add array {string}")
    public void addArray(String str) {
        log.info("addArray " + str);
        array.add(str);
    }

    /**
     * Add array to json object.
     *
     * @param str the str
     */
    @And("add {string} array to JsonObject")
    public void addArrayToJsonObject(String str) {
        log.info("add" + str + " array to JsonObject");
        object.put(str, array);
    }

    /**
     * Clear array.
     */
    @And("clear array")
    public void clearArray() {
        log.info("clear array");
        array.clear();
    }

    /**
     * Clear hash map.
     */
    @And("clear hash map")
    public void clearHashMap() {
        log.info("clearHashMap");
        headers.clear();
    }


    /**
     * Put token for authorization.
     *
     * @param type  the type
     * @param key   the key
     * @param value the value
     */
    @And("add header {string}: {string},{string}")
    public void putHeaders(String type, String key, String value) {
        log.info("add bearer " + type + ": " + key + "," + value);
        if (type.equals("Bearer")) {
            headers.put(key, "Bearer " + response.jsonPath().getString(value));
        } else if (type.equals("Basic")) {
            headers.put(key, "Basic " + response.jsonPath().getString(value));
        } else {
            log.info("putHeaders type can not be find.");
        }

    }

    /**
     * Post request.
     *
     * @param url the url
     */
    @And("post to {string}")
    public void postRequest(String url) {
        log.info("post to " + url);
        JSONObject obj = new JSONObject();
        obj.put("asd", "aSD");
        obj.put("BDD", "bdd");
        object.put("ozan", obj);
        log.info("post body:" + object.toString());
        response = given().headers(headers).body(object.toString()).when().post(url);
    }

    /**
     * Create Json object for body.
     */
    @And("create JSONObject")
    public void createJSONObject() {
        log.info("create JSONObject");
        object = new JSONObject();
    }

    /**
     * Put object with type.
     *
     * @param type  the type
     * @param key   the key
     * @param value the value
     */
    @And("put object {string}: {string},{string}")
    public void putObject(String type, String key, String value) {
        log.info("put object " + type + ": " + key + "," + value);
        if (type.equals("s")) {
            object.put(key, value);
        } else if (type.equals("i")) {
            object.put(key, Integer.parseInt(value));
        } else if (type.equals("b")) {
            object.put(key, Boolean.parseBoolean(value));
        } else {
            log.info("putObject type bulunamadı.");
        }
    }


}
