
package restsystem;
import java.awt.Color;
import java.io.*;
import java.io.ObjectInputStream;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class Customer {
    private Waiter mywaiter=new Waiter("ali",20,273);
    
    //RESERVATION FUNCTIONS
    
    void addreservation(){
        try{
                JFrame frame=new JFrame("Restaurants");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(800, 400);
            frame.getContentPane().setBackground( Color.PINK );
       JLabel lb1=new JLabel("CHOOSE THE SUITABLE RESTAURANT FROM THESE AVAILABLE RESTAURANTS:");
       lb1.setBounds(5,10,800,20);

       
       //READING DATA FROM CURRENT RESTAURANTS
       File file=new File("RestaurantsData.bin");
     if(file.exists()==false){
     throw new Exception("There are no restaurants");}
FileInputStream filein=new FileInputStream("RestaurantsData.bin");
ObjectInputStream read=new ObjectInputStream(filein);
int n=10;
while(filein.available()!=0){
Restaurant obj=(Restaurant)read.readObject();
JLabel label=new JLabel(obj.branchname+" - ");
label.setBounds(n,30,800,20);
frame.add(label);
n+=50;
}
read.close();


    JLabel lb2=new JLabel("ENTER THE RESTAURANT BRANCH NAME:");
    lb2.setBounds(5, 55,800 ,20);
    JTextField f1=new JTextField();
    f1.setBounds(5, 75, 300, 20);
   JLabel lb3=new JLabel("Enter THE NUMBER OF TABLES TO RESERVE:");
   lb3.setBounds(5, 95,800 ,20);
    JTextField f2=new JTextField();
    f2.setBounds(5, 115, 300, 20);
   JLabel lb4=new JLabel("Enter THE DATE OF RESERVATION:");
    lb4.setBounds(5, 135,800 ,20);
    JTextField f3=new JTextField();
     f3.setBounds(5, 155, 300, 20);
     //SUBMIT BUTTON
     JButton butt=new JButton("SUBMIT");
      butt.setBounds(5, 185, 90, 20);
      //ADDING TO FRAME
      frame.add(lb1);
      frame.add(lb2);
      frame.add(lb3);
      frame.add(lb4);
      frame.add(f1);
      frame.add(f2);
      frame.add(f3);
      frame.add(butt);
      frame.setVisible(true);
      
       
   class listener implements ActionListener{
       
   public void actionPerformed(ActionEvent action){
       
       if(action.getSource()==butt){
      mywaiter.addreservation(new Reservation(f1.getText(),Integer.parseInt(f2.getText()),f3.getText()));
      
      frame.setVisible(false);
        
   }
        
    
    }

        }
        
   //BUTTON LISTENER
        butt.addActionListener(new listener());
        }
        
        catch(Exception err){
        
       JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    
    void updatereservation(){
  
    
    String name=JOptionPane.showInputDialog("Enter the restaurant branch name to update the reservation:");
    
    mywaiter.updaterservation(name);
    
    
    }
    
    void deletereservation(){
    
 
    String name=JOptionPane.showInputDialog("Enter the restaurant branch name to delete the reservation:");
    
   mywaiter.deletereservation(name);
    
    }
    
    void readreservation(){
    
    mywaiter.readreservations();
    }
    
    
    
    //ORDERS FUNCTIONS
    
        void addorder(){
  
    String name=JOptionPane.showInputDialog("Enter the Item name:");

    String paymentmethod=JOptionPane.showInputDialog("Enter the payment method:");

    
    mywaiter.takeorder(new Order(name,paymentmethod));
    
    
    }
        
    void updateorder(){
    
    String name=JOptionPane.showInputDialog("Enter the order name to update:");
    
    mywaiter.updateorder(name);
    
    }
    
    void deleteorder(){

    
    String name=JOptionPane.showInputDialog("Enter the order name to Delete:");
    
    mywaiter.deleteorder(name);
    
    }
    
    void readorders(){
    
        mywaiter.readorders();
        
    }
void ShowCustomerSystem(){
    try{
    JOptionPane.showMessageDialog(null,"WELCOME TO CUSTOMER SYSTEM");
   JFrame frame=new JFrame("Costumer System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(400, 500);
            frame.getContentPane().setBackground( Color.PINK );
     JLabel lb1=new JLabel("ENTER:");
     lb1.setBounds(5,10,900,50);
     JLabel lb2=new JLabel("1-TO ADD RESERVATION");
     lb2.setBounds(5,30,900,50);
     JLabel lb3=new JLabel("2-TO UPDATE RESERVATION"); 
     lb3.setBounds(5,50,900,50);
     JLabel lb4=new JLabel("3-TO DELETE RESERVATION");
     lb4.setBounds(5,70,900,50);
     JLabel lb5=new JLabel("4-TO READ RESERVATION");
     lb5.setBounds(5,90,900,50);
     JLabel lb6=new JLabel("5-TO ADD ORDER");
     lb6.setBounds(5,110,900,50);
     JLabel lb7=new JLabel("6-TO UPDATE ORDERS");
     lb7.setBounds(5,130,900,50);
     JLabel lb8=new JLabel("7-TO DELETE ORDERS");
     lb8.setBounds(5,150,900,50);
     JLabel lb9=new JLabel("8-TO READ ORDERS");
     lb9.setBounds(5,170,900,50);
       JLabel lb10=new JLabel("9-TO EXIT");
     lb10.setBounds(5,190,900,50);
     JLabel lb=new JLabel("Enter the input:");
     lb.setBounds(5,220,900,50);
     
 JTextField text=new JTextField();
 text.setBounds(5, 260, 300, 20);
 
 JButton button=new JButton("SUBMIT");
 button.setBounds(5, 290, 90, 20);

  
   frame.add(lb1);
   frame.add(lb2);
   frame.add(lb3);
   frame.add(lb4);
   frame.add(lb5);
   frame.add(lb6);
   frame.add(lb7);
   frame.add(lb8);
   frame.add(lb9);
   frame.add(lb10);
   frame.add(lb);
   frame.add(text);
   frame.add(button);
   frame.setVisible(true);
   
   
   class listener implements ActionListener{
       
   public void actionPerformed(ActionEvent action){
       
       if(action.getSource()==button){
    if(text.getText().equals("1")){
       addreservation();
   
    }
        
      if(text.getText().equals("2")){
     updatereservation();
    }
        
        
       if(text.getText().equals("3")){
     deletereservation();

    }
        
      if(text.getText().equals("4")){
       readreservation();

    }
                        
        if(text.getText().equals("5")){
         addorder();

    }
        
        if(text.getText().equals("6")){
          updateorder();

    }         
       if(text.getText().equals("7")){
          deleteorder();

    }    
           if(text.getText().equals("8")){
          readorders();

    } 
          if(text.getText().equals("9")){
 
         JOptionPane.showMessageDialog(null,"GOODBYE");
          frame.setVisible(false);}
   }
   
   
   }
        
    
    }

   //ADDING LISTENER
     button.addActionListener(new listener());
    
    }







     catch(java.util.InputMismatchException err){
        System.out.println();
        JOptionPane.showMessageDialog(null,"TRY AGAIN,PLEASE ENTER A NUMBER FROM THE LIST","message",JOptionPane.ERROR_MESSAGE);
        }








}
}
    
    
    
