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
public class LocalBuddy extends Buddy{
    
    private int max_buddies;
    private String wish1;
    private String wish2;
    private String wish3;
    private String gender;
    private ArrayList<ExchangeBuddy> correlationList;
    private double[] correlations;
    private int[] indexArray;
    private ArrayList<ExchangeBuddy> assignedBuddies;
    private int proposalPointer = 0;

    
    public LocalBuddy(String firstName, String lastName, String gender, String country, String mail, String phone, int age, int[] interests, String facebook, String studyLevel, String languages, int max_buddies, String wish1, String wish2, String wish3) {
        super(firstName, lastName, country, mail, phone, age, interests, facebook, studyLevel, languages);
        this.max_buddies = max_buddies;
        this.wish1 = wish1;
        this.wish2 = wish2;
        this.wish3 = wish3;
        this.gender = gender;
        assignedBuddies = new ArrayList<ExchangeBuddy>();
        
    }
    
    public int nbBuddies() {return assignedBuddies.size();}
    public int getMaxBuddies() {return max_buddies;}

    public int getPreferred(){
        return indexArray[proposalPointer];
    }
    
    public void checkNextBuddy(){
        proposalPointer++;
        if(proposalPointer==correlations.length) proposalPointer = 0;
    }
    
    public double getCorrelationWithPreferred(){
        return correlations[proposalPointer];
    }
    
    public void assignBuddy(ExchangeBuddy e){
        assignedBuddies.add(e);
    }
    
    public String[] wishlist (){
        return new String[] {shortCountryFormat(wish1),shortCountryFormat(wish2),shortCountryFormat(wish3)};
    }
    
    public String getExchangeBuddies(String fs, String ls){
        String output = "";
        Iterator<ExchangeBuddy> it = assignedBuddies.iterator();
        int counter = 0;
        while(it.hasNext())
        {
            if(counter!=0) output += ESNbuddyMatcher.window.emptyLine(fs);
            ExchangeBuddy b = it.next();
            output += b.getOutputFormat(fs) + ls;
            counter++;
        }
        return output;
    }
    
    protected void createCorrelationList(ArrayList<ExchangeBuddy> source, CorrelationCalculator m){
        correlationList = new ArrayList<ExchangeBuddy>(source);
        correlations = new double[correlationList.size()];
        
        
        //fill in correlation table for each buddy
        int counter = 0;
        Iterator<ExchangeBuddy> it = correlationList.iterator();
        while(it.hasNext())
        {
            ExchangeBuddy b = it.next();
            correlations[counter]=m.calculateCorrelation(this,b);
            counter++;
        }
        
        
        //create empty index array
        indexArray = new int[correlations.length];
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
    
    public void printCorrelations(){
        System.out.println("Sorted correlations for buddy " + this.mail());
        for(int i=0;i<correlations.length;i++){
            System.out.print(correlations[i]+" "+indexArray[i]+"\n");
        }
    }
    
}
