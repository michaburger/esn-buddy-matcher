/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esnbuddymatcher;

import esnbuddymatcher.ESNbuddyMatcher.Interests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author michaburger
 */
public class FileHandler {
    
    private static final int NB_INTERESTS = ESNbuddyMatcher.NB_INTERESTS;
    
    
    private static final String HEADER_FORMAT_EXCHANGE = "\"webform_serial\"|\"webform_sid\"|\"webform_time\"|\"webform_draft\"|\"webform_ip_address\"|\"webform_uid\"|\"webform_username\"|\"first_name\"|\"last_name\"|\"country_of_origin\"|\"e_mail_address\"|\"phone_number\"|\"age\"|\"key_Backpacking	\"|\"key_Cinema	\"|\"key_Cooking	\"|\"key_Concerts	\"|\"key_Dancing \"|\"key_Hiking	\"|\"key_Music	\"|\"key_Partying	\"|\"key_Photography	\"|\"key_Reading \"|\"key_Team Sports	\"|\"key_Volleyball \"|\"key_Basketball \"|\"key_Football	\"|\"key_Water Sports	\"|\"key_Skiing/Snowboarding	\"|\"key_Volunteering \"|\"which_language_do_you_speak\"|\"facebook_contact\"|\"faculty_at_host_university\"|\"what_is_your_level_of_study\"|\"which_gender_would_you_like_your_buddy_to_be\"|\"when_will_you_arrive_in_lausanne\"";
    private static final String HEADER_FORMAT_LOCAL = "\"webform_serial\"|\"webform_sid\"|\"webform_time\"|\"webform_draft\"|\"webform_ip_address\"|\"webform_uid\"|\"webform_username\"|\"first_name\"|\"last_name\"|\"phone_number\"|\"e_mail_address\"|\"gender\"|\"age\"|\"key_Backpacking	\"|\"key_Cinema	\"|\"key_Cooking	\"|\"key_Concerts	\"|\"key_Dancing \"|\"key_Hiking	\"|\"key_Music	\"|\"key_Partying	\"|\"key_Photography	\"|\"key_Reading \"|\"key_Team Sports	\"|\"key_Volleyball \"|\"key_Basketball \"|\"key_Football	\"|\"key_Water Sports	\"|\"key_Skiing/Snowboarding	\"|\"key_Volunteering \"|\"facebook_contact\"|\"what_is_your_motivation_for_being_a_buddy\"|\"faculty_at_host_university\"|\"country_of_origin\"|\"what_is_your_level_of_study\"|\"which_language_do_you_speak\"|\"where_would_you_like_your_buddy_to_come_from_1st_choice\"|\"where_would_you_like_your_buddy_to_come_from_2nd_choice\"|\"where_would_you_like_your_buddy_to_come_from_3rd_choice\"|\"would_you_be_interested_in_having_more_than_one_buddy\"";
    
    protected String chooseCSVfile(){
        JFileChooser chooser = new JFileChooser();
        
        //only show .csv files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
        chooser.setFileFilter(filter);
        
        chooser.setDialogTitle("Choose a CSV file");
        if (chooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            System.out.print("\nError: File choosing aborted");
        File file = new File(chooser.getCurrentDirectory(), chooser.getSelectedFile().getName());

        //return filename
        return file.toString();

    }
    
    protected String chooseDirectory(){
        JFileChooser chooser = new JFileChooser();
        
        chooser.setDialogTitle("Choose a directory");
        if (chooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            System.out.print("\nError: Directory choosing aborted");
        File file = new File(chooser.getCurrentDirectory(), chooser.getSelectedFile().getName());

        //return filename
        return file.toString();

    }
    
    private int convertInterest(String answer){
        if (answer.contains(ESNbuddyMatcher.NO)) return 1;
        else if (answer.contains(ESNbuddyMatcher.MAYBE)) return 2;
        else if (answer.contains(ESNbuddyMatcher.YES)) return 3;
        else return 0;
    }
    
    protected int checkFormat(String fileContent){
        String[] lines = fileContent.split("\n");
        if(lines[2].equals(HEADER_FORMAT_EXCHANGE)) return ESNbuddyMatcher.EXCHANGE;
        else if (lines[2].equals(HEADER_FORMAT_LOCAL)) return ESNbuddyMatcher.LOCAL;
        else return ESNbuddyMatcher.ERROR;
    }
    
    private int decodeBuddyNb(String answer){
        if (answer.contains("love")) return 3;
        else if (answer.contains("two")) return 2;
        else if (answer.contains("one")) return 1;
        else return 0;
    }
    
    protected void readLocalBuddies(String fileContent){
        String[] lines = fileContent.split("\n");
        int firstBuddyOffset = 3;
        
        int[] interests = new int[NB_INTERESTS];
        
        for(int i = firstBuddyOffset; i<lines.length; i++){
            
            String[] answers = lines[i].split("\"\\|\"");
            String firstName = answers[7];
            String phone = answers[9];
            String lastName = answers[8];
            String mail = answers[10];
            String gender = answers[11];
            int age = Integer.parseInt(answers[12]);
            interests[Interests.BACKPACKING.ordinal()] = convertInterest(answers[13]);
            interests[Interests.CINEMA.ordinal()] = convertInterest(answers[14]);
            interests[Interests.COOKING.ordinal()] = convertInterest(answers[15]);
            interests[Interests.CONCERTS.ordinal()] = convertInterest(answers[16]);
            interests[Interests.DANCING.ordinal()] = convertInterest(answers[17]);
            interests[Interests.HIKING.ordinal()] = convertInterest(answers[18]);
            interests[Interests.MUSIC.ordinal()] = convertInterest(answers[19]);
            interests[Interests.PARTY.ordinal()] = convertInterest(answers[20]);
            interests[Interests.PHOTO.ordinal()] = convertInterest(answers[21]);
            interests[Interests.READING.ordinal()] = convertInterest(answers[22]);
            interests[Interests.TEAMSPORTS.ordinal()] = convertInterest(answers[23]);
            interests[Interests.VOLLEY.ordinal()] = convertInterest(answers[24]);
            interests[Interests.BASKET.ordinal()] = convertInterest(answers[25]);
            interests[Interests.FOOT.ordinal()] = convertInterest(answers[26]);
            interests[Interests.WATER.ordinal()] = convertInterest(answers[27]);
            interests[Interests.SKI.ordinal()] = convertInterest(answers[28]);
            interests[Interests.VOLUNTEERING.ordinal()] = convertInterest(answers[29]);
            
            String facebook = answers[30];
            String country = answers[33];
            String languages = answers[35];
            String studylevel = answers[34];
            String wish1 = answers[36];
            String wish2 = answers[37];
            String wish3 = answers[38];
            
            int nb_buddies = decodeBuddyNb(answers[39]);
            /*
            for(int j = 0; j<answers.length; j++){
            if(i==4) System.out.println(answers[j]);
            }
             */
            
            LocalBuddy buddy = new LocalBuddy(firstName,lastName,gender, country,mail,phone,age,interests,facebook,studylevel,languages,nb_buddies,wish1,wish2,wish3);
            if(!ESNbuddyMatcher.window.exists(buddy)) ESNbuddyMatcher.window.addLocalBuddy(buddy);
        }
        
    }
    
    protected void exportToFile(String output, String filename){
        exportToFile(output,filename,"csv");
    }
    
    protected void exportToFile(String output, String filename,String datatype){
        try {
            PrintWriter writer= null;
            
            writer = new PrintWriter(filename, "UTF-8");
            writer.print(output);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    protected void readExchangeBuddies(String fileContent){
        String[] lines = fileContent.split("\n");
        int firstBuddyOffset = 3;
        
        int[] interests = new int[NB_INTERESTS];
        
        for(int i = firstBuddyOffset; i<lines.length; i++){
            
            String[] answers = lines[i].split("\"\\|\"");
            
            String firstName = answers[7];
            String phone = answers[11];
            String lastName = answers[8];
            String mail = answers[10];
            String country = answers[9];
            int age = Integer.parseInt(answers[12]);
            
            interests[Interests.BACKPACKING.ordinal()] = convertInterest(answers[13]);
            interests[Interests.CINEMA.ordinal()] = convertInterest(answers[14]);
            interests[Interests.COOKING.ordinal()] = convertInterest(answers[15]);
            interests[Interests.CONCERTS.ordinal()] = convertInterest(answers[16]);
            interests[Interests.DANCING.ordinal()] = convertInterest(answers[17]);
            interests[Interests.HIKING.ordinal()] = convertInterest(answers[18]);
            interests[Interests.MUSIC.ordinal()] = convertInterest(answers[19]);
            interests[Interests.PARTY.ordinal()] = convertInterest(answers[20]);
            interests[Interests.PHOTO.ordinal()] = convertInterest(answers[21]);
            interests[Interests.READING.ordinal()] = convertInterest(answers[22]);
            interests[Interests.TEAMSPORTS.ordinal()] = convertInterest(answers[23]);
            interests[Interests.VOLLEY.ordinal()] = convertInterest(answers[24]);
            interests[Interests.BASKET.ordinal()] = convertInterest(answers[25]);
            interests[Interests.FOOT.ordinal()] = convertInterest(answers[26]);
            interests[Interests.WATER.ordinal()] = convertInterest(answers[27]);
            interests[Interests.SKI.ordinal()] = convertInterest(answers[28]);
            interests[Interests.VOLUNTEERING.ordinal()] = convertInterest(answers[29]);
            
            String languages = answers[30];
            String facebook = answers[31];
            String studylevel = answers[33];
            
            String genderWish = answers[34];
            
            /*
            for(int j = 0; j<answers.length; j++){
            if(i==4) System.out.println(answers[j]);
            }
            */
             
            ExchangeBuddy buddy = new ExchangeBuddy(firstName,lastName,country,mail,phone,age,interests,facebook,studylevel,languages,genderWish);
            if(!ESNbuddyMatcher.window.exists(buddy)) ESNbuddyMatcher.window.addExchangeBuddy(buddy);
            
            //buddy.print();
        }
        
    }
    
    protected String readFromFile(String filename){
        
        InputStream inputStream = null;
        String line;
        String data = "";
        
        try {
            //local buffers
            
            inputStream = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((line = br.readLine()) != null){
                data += line + "\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return data;
    }
    
}
