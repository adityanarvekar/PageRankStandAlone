/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aditya
 */
public class FileIO {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static ArrayList<Map<String, String>> prolist = new ArrayList<Map<String, String>>();
    public static int fileLines = 0;
    public static void main(String[] args) {
        try {
            // TODO code application logic here
          mapperFunction("inputpagerank.txt");
            
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void mapperFunction(String fileName) throws FileNotFoundException, IOException {
        BufferedReader ins = new BufferedReader(new FileReader(fileName));
        ArrayList<Node> arrayNode = new ArrayList<>();
        while (ins.ready()) {
            String s = ins.readLine();

            String[] equation = s.split(" ");

            Node bigNode = new Node(equation);

            HashMap newHashMap = bigNode.getHashMap();

            if (equation[2].contains(",")) {
                String[] nodelist = equation[2].split(",");
                double pgVar = Double.parseDouble(equation[1]) / nodelist.length;
                for (String node : nodelist) {
                    newHashMap.put(node, pgVar);
                }
            } else {

                newHashMap.put(equation[2], Double.parseDouble(equation[1]));

            }
            arrayNode.add(bigNode);
        }
        ins.close();
        //DecimalFormat df2 = new DecimalFormat("#.##");
        for (int j = 0; j < arrayNode.size(); j++) {
            String arrayNodeId = arrayNode.get(j).getID();
            String neighbours = arrayNode.get(j).getNeighbours();
            HashMap<String, Double> neh = arrayNode.get(j).getHashMap();
            Collection<?> keys = neh.keySet();
            for (Object key : keys) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("NID", arrayNodeId);
                datanum.put("KEY", String.valueOf(key));
                datanum.put("PG", String.valueOf(neh.get(key)));
                datanum.put("N", neighbours);
                prolist.add(datanum);
            }
        }

        reducerFunction();

    }

    public static void reducerFunction() throws IOException {
        BufferedWriter outs = new BufferedWriter(new FileWriter("inputpagerank2.txt"));
        String previousNID = "";
        for (int i = 0; i < prolist.size(); i++) {
            String nid = prolist.get(i).get("NID");
            String neighbours = prolist.get(i).get("N");
            if (previousNID.equals(nid)) {

            } else {
                double valueofnid = 0;
                for (int j = 0; j < prolist.size(); j++) {
                    if (prolist.get(j).get("KEY").equals(nid)) {
                        valueofnid += Double.parseDouble(prolist.get(j).get("PG"));
                    }
                }
                outs.write(nid+" "+valueofnid+" "+neighbours+"\n");
            }
           

            previousNID = nid;

        }
        outs.close();
    }
}
