/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.util.HashMap;

/**
 *
 * @author aditya
 */
public class Node {
    private String NId;
    private double pageRank;
    private HashMap<String, Double> neighbourHashMap;
    private String neighbours;
    
    public Node(String[] equation){
        NId=equation[0];
        pageRank = Double.parseDouble(equation[1]);
        neighbours = equation[2];
        neighbourHashMap = new HashMap<>();
    }
    public HashMap getHashMap(){
        return neighbourHashMap;
    }
    public void putInNeighbourVal(String key, Double val){
        neighbourHashMap.put(key, val);
    }
    public Double getNeighbourVal(String key){
        return neighbourHashMap.get(key);
    }
    
    public void setPageRank(double pg){
        pageRank = pg;
    }
    public Double getPageRank(){
        return pageRank;
    }
    
    public void setID(String id){
        NId = id;
    }
    public String getID(){
        return NId;
    }
    
    public void setNeighbours(String neighbours1){
        neighbours = neighbours1;
    }
    public String getNeighbours(){
        return neighbours;
    }
}
