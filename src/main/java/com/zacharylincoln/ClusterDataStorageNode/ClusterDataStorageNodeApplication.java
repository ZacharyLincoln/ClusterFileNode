package com.zacharylincoln.ClusterDataStorageNode;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.security.sasl.SaslServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;

@SpringBootApplication
public class ClusterDataStorageNodeApplication {

	public static long sizeInBytes;

	public static int port;

	
	public static void main(String[] args) throws IOException {
		MasterNode.masterNodeIp = "http://localhost:8080";

		Random rand = new Random();
		port = rand.nextInt(1000) - 1;

		sizeInBytes = 10000;

		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", port);

		new SpringApplicationBuilder()
				.sources(ClusterDataStorageNodeApplication.class)
				.properties(props)
				.run(args);


		//SpringApplication.run(ClusterDataStorageNodeApplication.class, args);
		// tell master node that this node is online and to add to node cluster on the database.





	}

	@PostConstruct
	private void init() throws IOException, InterruptedException, ParseException {
		ClusterDataStorageNodeApplication.setup(port);
	}




	//
	public static void setup(int port) throws IOException, InterruptedException, ParseException {

	    String masterNodeIp = MasterNode.masterNodeIp + "/setUpNode?totalSpaceInBytes=" + sizeInBytes + "&port=" + port;

		URL master = new URL(masterNodeIp);

		URLConnection masterConnection = master.openConnection();
		masterConnection.getInputStream();


	}



}
