package com.zacharylincoln.ClusterDataStorageNode;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MasterNode {

    public static String masterNodeIp = "http://localhost:8080";

    public static JSONObject setUpFileNode(String id, String bytes) throws IOException, ParseException {
        String masterNodeRequest = masterNodeIp + "/setUpFileNode?id=" + id + "&bytes=" + bytes + "&port=" + ClusterDataStorageNodeApplication.port;

        URL master = new URL(masterNodeRequest);

        org.json.simple.JSONObject json = new org.json.simple.JSONObject();
        JSONParser parser = new JSONParser();

        URLConnection masterConnection = master.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        masterConnection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            json = (org.json.simple.JSONObject) parser.parse(inputLine);
        in.close();

        return json;
    }

}
