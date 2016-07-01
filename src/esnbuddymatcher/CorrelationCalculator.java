/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esnbuddymatcher;

/**
 *
 * @author michaburger
 */
public class CorrelationCalculator {
    
    //----- SETTINGS ----- //
    //at which age difference the correlation will be 0
    private static final int AGE_DECORR = 10;
    //how many options for the interests are available
    private static final int STEP_INTERESTS = 3;
    //how many choices for the country are available
    private static final int NB_CHOICES = 3;
    //the value for the country correlation when no country is chosen
    private static final double CONTRIB_NO_COUNTRY_CHOICE = 0.5;
    
    private int age;
    private int intr;
    private int coun;
    private int totalWeight;
    
    public CorrelationCalculator(int age, int intr, int coun){
        this.age = age;
        this.intr = intr;
        this.coun = coun;
        totalWeight = age+intr+coun;
    }
    
    public double calculateCorrelation(LocalBuddy l, ExchangeBuddy e){
        double correlation = 0.;
        
        correlation += age * ageCorrelation(l.age(),e.age());
        
        correlation += intr * interestsCorrelation(l.interests(),e.interests());
        
        correlation += coun * countryCorrelation(l.wishlist(),e.country());

        //norm the result
        return correlation/totalWeight;
    }
    
    private double ageCorrelation(int age1, int age2){
        int ageDiff = Math.abs(age1-age2);
        if(ageDiff>AGE_DECORR) return 0;
        else return 1-(1./AGE_DECORR)*ageDiff;
    }
    
    private double interestsCorrelation(int[] list1, int[] list2){
        if(list1.length != list2.length) return 0;
        
        //the value for a correlation of 0
        double normFactor = (double) list1.length * (STEP_INTERESTS-1);
        int sum = 0;
        
        for (int i=0; i<list1.length; i++){
            //System.out.print(list1[i]+"-"+list2[i]+" ");
            sum += Math.abs(list1[i]-list2[i]);
        }
        
        //norm to obtain value between 0 and 1
        return 1-(1./normFactor)*sum;
    }
    
    private double countryCorrelation(String[] wishlist, String country){
        
        //System.out.print("\nCountry: "+country+" Wishlist: "+wishlist[0]+","+wishlist[1]+","+wishlist[2]);
        
        //if no country is selected for all of the three choices
        if(wishlist[0].equals("XX")&&wishlist[1].equals("XX")&&wishlist[2].equals("XX")) return CONTRIB_NO_COUNTRY_CHOICE;
        
        //if one of the countries is a match, full correlation
        for(int i=0; i<(NB_CHOICES-1); i++){
            if(country.equals(wishlist[i])) return 1;
        }
        
        return 0;
    }
    
}
