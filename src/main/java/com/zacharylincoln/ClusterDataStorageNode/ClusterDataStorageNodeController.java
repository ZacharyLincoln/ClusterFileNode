package com.zacharylincoln.ClusterDataStorageNode;

import net.iharder.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

@RestController
public class ClusterDataStorageNodeController {

    @Value("${server.address}")
    public static String serverIp;

    @LocalServerPort
    public static String serverPort;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public boolean uploadFile(@RequestParam Map<String,String> requestParams) throws IOException, ParseException {
        String bytesStr = requestParams.get("bytes").toString();
        int fileID = Integer.valueOf(requestParams.get("fileID"));
        int sizeInBytes = Integer.valueOf(requestParams.get("sizeInBytes"));
        String deletionCode = requestParams.get("deletionCode").toString();

        System.out.println("Received File");

        //ODO Convert bytes to a text file with the name of the file id
        ByteFile.stringToFile(bytesStr,fileID + "." + deletionCode,"txt");

        //Update Database Record with the file name ie: id.deletionCode 153.237836.txt
        MasterNode.setUpFileNode(fileID + "." + deletionCode, sizeInBytes + "");
        System.out.println("Finished Upload");

        return false;
    }

    @RequestMapping("/downloadFile")
    public JSONObject downloadFile(@RequestParam Map<String,String> requestParams){
        int fileID = Integer.valueOf(requestParams.get("fileID"));
        String deletionCode = requestParams.get("deletionCode").toString();
        //convert file to bytes
        JSONObject json = new JSONObject();
        String file = ClusterDataStorageNodeController.readFile(fileID + "", deletionCode);
        json.put("Base64", file);
        //send file
        return json;
    }

    @RequestMapping("/checkForSpace")
    public boolean checkForSpace(@RequestParam Map<String,String> requestParams) {
        int sizeInBytes = Integer.valueOf(requestParams.get("sizeInBytes"));
        //TODO check to see if this node has enough space to host this file.



        return false;
    }

    @RequestMapping("/checkIfUp")
    public boolean checkIfUp(@RequestParam Map<String,String> requestParams){
        return true;
    }

    public static String readFile(String id, String deletionCode){
        String data = "";
        try {
            File myObj = new File(id + "." + deletionCode + ".txt");
            Scanner myReader = new Scanner(myObj);

            int i = 0;
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                System.out.println(i);
                return data;
                //i++;
            }

            myReader.close();
            return data;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}


