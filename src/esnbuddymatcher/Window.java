/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esnbuddymatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author michaburger
 */
public class Window extends javax.swing.JFrame {

    
    FileHandler fileHandler = new FileHandler();
    private boolean localsImported = false;
    private boolean exchangeImported = false;
    private final ArrayList<ExchangeBuddy> exchangeBuddies = new ArrayList<>();
    private final ArrayList<LocalBuddy> localBuddies = new ArrayList<>();
    
    private final int MAX_TRYS = 10;
    
    private int availableExchangeBuddies;
    
    
    public Window() {
        initComponents();
        saveButton.setEnabled(false);
        
    }
    
    public FileHandler getFileHandler(){
        return fileHandler;
    }
    
    public boolean exists(ExchangeBuddy b){
        Iterator<ExchangeBuddy> it = exchangeBuddies.iterator();
        while(it.hasNext())
        {
            ExchangeBuddy i = it.next();
            if(b.mail().equals(i.mail())) return true;
        }
        return false;  
    }
    
    public boolean exists(LocalBuddy b){
        Iterator<LocalBuddy> it = localBuddies.iterator();
        while(it.hasNext())
        {
            LocalBuddy i = it.next();
            if(b.mail().equals(i.mail())) return true;
        }
        return false;  
    }
    
    public void addExchangeBuddy(ExchangeBuddy b){
        exchangeBuddies.add(b);
    }
    
    public void addLocalBuddy(LocalBuddy b){
        localBuddies.add(b);
    }
    
    public void importExchange(String fileContent){
        fileHandler.readExchangeBuddies(fileContent);
                exchangeImported = true;
                exchangeValue.setText(exchangeBuddies.size()+"");
    }
    
    public void localsImported(){
        localsImported = true;
        localsValue.setText(localBuddies.size()+"");
    }
    
    
    
    private void calculateCorrelation(){
        CorrelationCalculator m = new CorrelationCalculator(ageSlider.getValue(),interestSlider.getValue(),countrySlider.getValue());
        
        //for every local and exchange buddy, calculate the correlation to each
        Iterator<LocalBuddy> it = localBuddies.iterator();
        while(it.hasNext())
        {
            LocalBuddy b = it.next();
            b.createCorrelationList(exchangeBuddies,m);
        }
        
        Iterator<ExchangeBuddy> ex = exchangeBuddies.iterator();
        while(ex.hasNext())
        {
            ExchangeBuddy b = ex.next();
            b.createCorrelationList(localBuddies,m);
        }
        
        //every buddy has now a list with his preferences. Now we can go on with matching!
        
    }
    
    private void stableMarriageAlgorithm(){
        availableExchangeBuddies = exchangeBuddies.size();
        
        //first round with all the local buddies, next round only with locals
        //who want more than 1 buddy
        for (int round = 1; round <= 10; round++){
            //System.out.println("Round "+round);
            //parcourir local list until every local has "round" buddies, or there are no more buddies
            while(!correctBuddyNumber(round)){
                Iterator<LocalBuddy> it = localBuddies.iterator();
                while(it.hasNext())
                {
                    LocalBuddy l = it.next();
                    //System.out.println("It's the turn of "+l.mail());
                    proposeMarriage(l,round);
                }
            }   
            
        }
        
        //System.out.println("All Buddies assigned, remaining exchange buddies: "+availableExchangeBuddies);

        
    }
    
    private String getOutputHeader(String fs){
        String output = "";
        output += "First name" + fs;
        output += "Last name" + fs;
        output += "Country"+fs;
        output += "Mail"+fs;
        output += "Phone"+fs;
        output += "Facebook"+fs;
        return output;
    }
    
    public String emptyLine(String fs) {return (fs+fs+fs+fs+fs+fs+fs);}
    
    private String prepareOutputData(){
        
        String fs = ","; //field separator
        String ls = "\n"; //line separator
        String output = "Local Buddies"+emptyLine(fs)+"Exchange Buddies"+emptyLine(fs)+ls;
        output += getOutputHeader(fs);
        output += fs;
        output += getOutputHeader(fs); //make a second table for Exchange Buddies
        output += "Correlation" + fs+ls;

        
        Iterator<LocalBuddy> it = localBuddies.iterator();
        while(it.hasNext())
        {
            LocalBuddy b = it.next();
            output += b.getOutputFormat(fs);
            output += fs;
            
            output += b.getExchangeBuddies(fs,ls);
        }
        return output;
    }
    
    private void proposeMarriage(LocalBuddy l, int round){
        for (int i=0; i<MAX_TRYS;i++){
            int preferredIndex = l.getPreferred();
            ExchangeBuddy best = exchangeBuddies.get(preferredIndex);
            // if free --> marriage
            if(best.isFree()){
                if(l.nbBuddies()<round){
                    assignBuddies(l,best);
                    l.checkNextBuddy();
                    i= MAX_TRYS;
                }
                
            }
            else {
                l.checkNextBuddy();
            }
        
        }       
                // else --> if better correlation than actual marriage --> 
                    //marriage and set old couple free
    }
    
    private void assignBuddies(LocalBuddy l, ExchangeBuddy e){
        e.setMatched(l.getCorrelationWithPreferred());
        l.assignBuddy(e);
        //System.out.println(l.mail()+" is assigned to "+e.mail());
        availableExchangeBuddies--;
    }
    
    private boolean correctBuddyNumber(int round){
        Iterator<LocalBuddy> it = localBuddies.iterator();
        
        if(availableExchangeBuddies == 0)return true;
        
        while(it.hasNext())
        {
            LocalBuddy l = it.next();
            if(round == 1 && l.nbBuddies()!=1) return false;
            if(round == 2 && l.getMaxBuddies()>1 && l.nbBuddies()!=2) return false;
            if(round >= 3 && l.getMaxBuddies()>2 && l.nbBuddies()<round) return false;
        }
        //System.out.println("Everybody has its buddy, go to next round!");
        return true;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        importButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        localsValue = new javax.swing.JTextField();
        exchangeValue = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ageSlider = new javax.swing.JSlider();
        interestSlider = new javax.swing.JSlider();
        countrySlider = new javax.swing.JSlider();
        startMatching = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_pack/buddy_logo.jpg"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("ESN buddy matcher");

        importButton.setText("import CSV list");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        localsValue.setEditable(false);

        exchangeValue.setEditable(false);

        jLabel3.setText("# Locals:");

        jLabel4.setText("# Exchange: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Matching weights");

        jLabel6.setText("age");

        jLabel7.setText("interests");

        jLabel8.setText("country preference");

        ageSlider.setMaximum(10);
        ageSlider.setValue(2);

        interestSlider.setMaximum(10);
        interestSlider.setValue(8);

        countrySlider.setMaximum(10);
        countrySlider.setValue(6);

        startMatching.setText("start matching");
        startMatching.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startMatchingActionPerformed(evt);
            }
        });

        saveButton.setText("save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel9.setText("ESNbuddyMatcher © Micha Burger ESN EPFL, Switzerland ");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_pack/88x31.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 8)); // NOI18N
        jLabel11.setText("IMPORTANT: The file has to be tab-separated. Multiple languages have to be separated by commas.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(interestSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(countrySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(ageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(importButton, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(exchangeValue)
                                    .addComponent(localsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startMatching)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(ageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(interestSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(countrySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel8))))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importButton)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startMatching)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        String filename = fileHandler.chooseCSVfile();
        String fileContent = fileHandler.readFromFile(filename);
        if(fileContent.equals("")) output.setText("Error reading File\n");
        //int fileFormat = fileHandler.checkFormat(fileContent);
        else{
            ESNbuddyMatcher.imp.setVisible(true);
            fileHandler.startImport(fileContent);
        }
        
        
        /*
        switch (fileFormat) {
            case ESNbuddyMatcher.ERROR:
                output.setText("Error: Format of the file. Please check info page!\n");
                break;
            case ESNbuddyMatcher.LOCAL:
                fileHandler.readLocalBuddies(fileContent);
                localsImported = true;
                localsValue.setText(localBuddies.size()+"");
                break;
            case ESNbuddyMatcher.EXCHANGE:
                fileHandler.readExchangeBuddies(fileContent);
                exchangeImported = true;
                exchangeValue.setText(exchangeBuddies.size()+"");
                break;
            default:
                break;
        }
        */
        
    }//GEN-LAST:event_importButtonActionPerformed

    private void startMatchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startMatchingActionPerformed
        //check if lists are imported
        if(localBuddies.isEmpty()||exchangeBuddies.isEmpty()){
            output.setText("Error: Please import CSV lists and try again");
        }
        else{
            //make preference list for each buddy
            calculateCorrelation();
            //apply stable marriage problem
            stableMarriageAlgorithm();
            saveButton.setEnabled(true);
            startMatching.setEnabled(false);
            
        }
    }//GEN-LAST:event_startMatchingActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String filename = fileHandler.chooseDirectory() + ".csv";
        String fileContent = prepareOutputData();
        fileHandler.exportToFile(fileContent,filename);
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider ageSlider;
    private javax.swing.JSlider countrySlider;
    private javax.swing.JTextField exchangeValue;
    private javax.swing.JButton importButton;
    private javax.swing.JSlider interestSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField localsValue;
    private javax.swing.JTextArea output;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton startMatching;
    // End of variables declaration//GEN-END:variables
}
