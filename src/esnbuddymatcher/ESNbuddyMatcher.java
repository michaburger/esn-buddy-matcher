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
public class ESNbuddyMatcher {
    
    public static Window window;
    public static ImportDialog imp;
    public static final int ERROR = 77;
    public static final int SUCCESS = 42;
    public static final int LOCAL = 11;
    public static final int EXCHANGE = 12;
    
    //how many interests the list proposes
    public static final int NB_INTERESTS = 17;
    
    public static String NO = "Not";
    public static String MAYBE = "fun";
    public static String YES = "love";
    
    public enum Interests { 
        BACKPACKING, CINEMA, COOKING, CONCERTS, DANCING, HIKING, MUSIC, PARTY,
        PHOTO, READING, TEAMSPORTS, VOLLEY, BASKET, FOOT, WATER, SKI, VOLUNTEERING
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        window = new Window();
        window.setVisible(true);
        imp = new ImportDialog();
        imp.setVisible(false);
    }


    
    
}
