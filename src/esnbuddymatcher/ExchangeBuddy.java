/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esnbuddymatcher;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author michaburger
 */

public class ExchangeBuddy extends Buddy{
    
    private String genderWish;
    private boolean free = true;
    private int correlationWithMate;
    
    private ArrayList<LocalBuddy> correlationList;
    private double[] correlations;
    
    public ExchangeBuddy(String firstName, String lastName, String country, String mail, String phone, int age, int[] interests, String facebook, String studyLevel,String languages,String genderWish) {
        super(firstName, lastName, country, mail, phone, age, interests, facebook, studyLevel,languages);
        this.genderWish = genderWish;
    }
    
    public boolean isFree() {return free;}
    public void setMatched(double correlation) {
        correlationWithMate = (int)(correlation * 100);
        free = false;
    }
    
    @Override
    protected String getOutputFormat(String fs){
        String output = super.getOutputFormat(fs);
        output += correlationWithMate +"%"+ fs;
        return output;
    }
    
    
    protected void createCorrelationList(ArrayList<LocalBuddy> source, CorrelationCalculator m){
        correlationList = new ArrayList<LocalBuddy>(source);
        correlations = new double[correlationList.size()];
        
        
        //fill in correlation table for each buddy
        int counter = 0;
        Iterator<LocalBuddy> it = correlationList.iterator();
        while(it.hasNext())
        {
            LocalBuddy b = it.next();
            correlations[counter]=m.calculateCorrelation(b,this);
            counter++;
        }
        
        
        //create empty index array
        int[] indexArray = new int[correlations.length];
        for (int i=0;i<indexArray.length;i++){
            indexArray[i]=i;
        }
        
        /*
        System.out.println("Unsorted correlations for buddy " + this.mail());
        for(int i=0;i<correlations.length;i++){
            System.out.print(correlations[i]+" "+indexArray[i]+"\n");
        }
        */
        
        
        //create preference list
        for (int i=1; i<correlations.length; i++){
            if(correlations[i]>correlations[i-1]){
                swapEntries(correlations,i);
                swapEntries(indexArray,i);
                i=0; //begin again
            }
        }
        /*
        System.out.println("Sorted correlations for buddy " + this.mail());
        for(int i=0;i<correlations.length;i++){
            System.out.print(correlations[i]+" "+indexArray[i]+"\n");
        }
        */
        
    }
    
    
}
