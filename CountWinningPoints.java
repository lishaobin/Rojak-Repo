package com.Stiw3054.groupProject.groupProject;

import java.util.HashMap;
import java.util.List;

class CountWinningPoints{
    
    HashMap<String,HashMap<String,Double>> MainMap = new HashMap<>();
    
    public void Count(List<List> data){
        for(List Row:data){
            String State = Row.get(6).toString().trim();
            String Cat = Row.get(11).toString().trim();
            Double Pts = Double.parseDouble(Row.get(7).toString().trim());
            HashMap<String,Double> SubMap = new HashMap<>();
            if(MainMap.get(State) == null){
                SubMap.put(Cat, Pts);
                MainMap.put(State, SubMap);
            }else if(MainMap.get(State).get(Cat) == null){
                SubMap = MainMap.get(State);
                SubMap.put(Cat, Pts);
                MainMap.put(State, SubMap);
            }else{
                SubMap = MainMap.get(State);
                Double Count = SubMap.get(Cat) + Pts;
                SubMap.put(Cat, Count);
                MainMap.put(State, SubMap);
            }
        }
        display(MainMap);
    }
    
    public void display(HashMap<String,HashMap<String,Double>> Map){
        double Total = 0;
        System.out.println("\n\n\t| State         | Category | Total |");
        System.out.println("\t------------------------------------");
        for (HashMap.Entry<String,HashMap<String,Double>> entry1 : MainMap.entrySet()) {
            double total = 0;
            for(HashMap.Entry<String,Double> entry2 : entry1.getValue().entrySet()){
                System.out.printf("\t| %-14s| %-8s | %-5s |\n", entry1.getKey(),entry2.getKey(),entry2.getValue());
                total += entry2.getValue();
            }
            System.out.printf("\t| %-14s| %-8s | %-5s |\n", "","TOTAL",total);
            Total += total;
        }
        System.out.printf("\t| %-14s| %-8s | %-5s |\n\n\n", "GRAND TOTAL","",Total);
    }
}
	                    
	                        
	                
	         
	        
	   
	   


