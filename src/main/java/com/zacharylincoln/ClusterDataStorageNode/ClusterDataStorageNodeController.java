package com.zacharylincoln.ClusterDataStorageNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class ClusterDataStorageNodeController {

    @Value("${server.address}")
    public static String serverIp;

    @LocalServerPort
    public static String serverPort;

    static {
        try {
            serverIp = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ;

    @RequestMapping("/uploadFile")
    public boolean uploadFile(@RequestParam Map<String,String> requestParams){
        int fileID = Integer.valueOf(requestParams.get("fileID"));
        int sizeInBytes = Integer.valueOf(requestParams.get("sizeInBytes"));

        //TODO checks to see if this node his enough space to host this file

        //TODO Convert bytes to a text file with the name of the file id

        //TODO substract the files' bytes from the available space on the node.

        return false;
    }

    @RequestMapping("/downloadFile")
    public String downloadFile(@RequestParam Map<String,String> requestParams){
        int fileID = Integer.valueOf(requestParams.get("fileID"));

        //TODO convert file to bytes

        //TODO send file

        return null;
    }

    @RequestMapping("/checkForSpace")
    public boolean checkForSpace(@RequestParam Map<String,String> requestParams) {
        int sizeInBytes = Integer.valueOf(requestParams.get("sizeInBytes"));
        //TODO check to see if this node has enough space to host this file.
        return false;
    }

    @RequestMapping("/checkIfUp")
    public boolean checkIfUp(){
        return true;
    }
}
