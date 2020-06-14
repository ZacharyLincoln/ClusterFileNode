package com.zacharylincoln.ClusterDataStorageNode;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.sasl.SaslServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
public class ClusterDataStorageNodeApplication {
	
	
	public static void main(String[] args) throws IOException {
		//TODO tell master node that this node is online and to add to node cluster on the database.

		SpringApplication.run(ClusterDataStorageNodeApplication.class, args);
		setup("456456");
	}

	public static void setup(String ip) throws IOException {

	    String masterNodeIp = "http://localhost:8080" + "/setUpNode";

		URL api = new URL(masterNodeIp);

		URLConnection apiConnection = api.openConnection();
		apiConnection.getInputStream();


    }



}
