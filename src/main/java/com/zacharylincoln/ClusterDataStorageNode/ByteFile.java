package com.zacharylincoln.ClusterDataStorageNode;

import java.io.*;

public class ByteFile {

    // Path of a file
    static String currentDirectory = System.getProperty("user.dir");

    static String FILEPATH = currentDirectory + "/test.txt";
    static File file = new File(FILEPATH);

    static byte[] fileToByteArray(String fileName, String fileExtension) throws Exception {
        File file = new File(currentDirectory + "/" + fileName + "." + fileExtension);
        //init array with file length
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();

        return bytesArray;
    }


    // Method which write the bytes into a file
    static void stringToFile(String bytes, String fileName, String fileExtension) throws FileNotFoundException, UnsupportedEncodingException {

        createFile(fileName, fileExtension);

        PrintWriter writer = new PrintWriter(fileName + "." + fileExtension, "UTF-8");
        writer.println(bytes);
        writer.close();
    }

    public static void createFile(String name, String extension){
        try {
            File myObj = new File(currentDirectory + name + extension);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
