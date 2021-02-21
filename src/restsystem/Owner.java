
package restsystem;
import java.awt.Color;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;

 
public class Owner {
   public static ArrayList<Restaurant> restaurants=new ArrayList<>();
   private ArrayList<Waiter> waiters=new ArrayList<>();

 
   //CONSTRUCTOR
   
   Owner(){
  
     //READING THE OLD RESTAURANTS FROM THE PREVIOUS FILE AND ADDING THEM TO THE LIST
     try{
    File restfile=new File("RestaurantsData.bin");
    if(restfile.exists()){
  FileInputStream filein=new FileInputStream("RestaurantsData.bin");
  ObjectInputStream read=new ObjectInputStream(filein);
    
  while(filein.available()!=0){
  
  Restaurant myrest=(Restaurant)read.readObject();
  restaurants.add(myrest);
  
  
  }
  filein.close();
   read.close();

    }
    
      //READING THE OLD WAITERS FROM THE PREVIOUS FILE AND ADDING THEM TO THE LIST
      File waitersfile=new File("WaitersData.bin");
    if(waitersfile.exists()){
  FileInputStream filein=new FileInputStream("WaitersData.bin");
  ObjectInputStream read=new ObjectInputStream(filein);
    
  while(filein.available()!=0){
  
  Waiter mywaiter=(Waiter)read.readObject();
  waiters.add(mywaiter);
  
  }
  filein.close();
  read.close();

  }
  
    
   
     }
     
     
     
     
     
         catch(Exception err){
    JOptionPane.showMessageDialog(null,"Error:"+err,"warning",JOptionPane.ERROR_MESSAGE );
    
    }
   
   }
   
   
//ADDING RESTAURANTS
    public void addRestaurant(){
      
    try{

  //START OF THE FUNCTION  
    String name=JOptionPane.showInputDialog("Type the name of the restaurant branch:");
    for(int i=0;i<restaurants.size();i++){
    if(restaurants.get(i).branchname.equals(name)){
    throw new Exception("Restaurant Already Exists");}
    }
     int itemsnumber=Integer.parseInt(JOptionPane.showInputDialog("Type the number of the items to add:"));
     String[] items=new String[itemsnumber];
     
     for(int i=0;i<itemsnumber;i++){
       
     String item=JOptionPane.showInputDialog("Enter the name of the Item:");
     items[i]=item;
     }
     
     int tablesnum=Integer.parseInt(JOptionPane.showInputDialog("Type the number of tables to add:"));
     //ADDING WAITERS
         
     int waitersnum=Integer.parseInt(JOptionPane.showInputDialog("Type the number of waiters to add:"));
     if(waitersnum>waiters.size()){
     throw new Exception("THERE AREN'T ENOUGH WAITERS TO ADD TO THIS RESTAURANT");}
   
      //ADDING RESTAURANT TO THE LIST
        restaurants.add(new Restaurant(name,itemsnumber,items,tablesnum,waitersnum));  
    
        File myfile=new File("RestaurantsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile.delete();
    //WRITING EACH RESTAURANT TO THE FILE
    FileOutputStream fileout=new FileOutputStream("RestaurantsData.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<restaurants.size();i++){
       
    write.writeObject(restaurants.get(i));
    }
      
    write.close();
JOptionPane.showMessageDialog(null,"RESTAURANT ADDED SUCCESSFULLY","success",JOptionPane.INFORMATION_MESSAGE);
     }
    
    catch(Exception err){
    JOptionPane.showMessageDialog(null,"Error:"+err,"warning",JOptionPane.ERROR_MESSAGE );
    
    }
    }

    //READING RESTAURANTS
        public void readRestaurants(){
            
       
    try{  
            JFrame frame=new JFrame("Restaurants");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.getContentPane().setBackground( Color.PINK );
            
        File filee=new File("RestaurantsData.bin");
       if(filee.exists()==false){
       throw new Exception("There are currently no restaurants");
       }
        
     FileInputStream filein=new FileInputStream("RestaurantsData.bin");
    ObjectInputStream read=new ObjectInputStream(filein);
    //TAKING OWNER INPUT

        int n=10;
    while(filein.available()!=0){
    
    Restaurant restobj=(Restaurant)read.readObject();
    JLabel label=new JLabel("Restaurant branch name: "+restobj.branchname+"      number of tables: "+restobj.noOftables
    +"     number of waiters: "+restobj.noOfwaiters+"    number of menue items: "+restobj.itemsNo);
    label.setBounds(5,n,900,50);

      frame.add(label);
      n+=20;
    }


read.close();
JButton butt=new JButton("Hide Restaurants");
butt.setBounds(5, n+30, 180, 30);
frame.add(butt);

 frame.setSize(900, n+200);
frame.setVisible(true);



  class listener implements ActionListener{
       @Override
   public void actionPerformed(ActionEvent action){
       
if(action.getSource()==butt){

frame.setVisible(false);
}
    
    }


  }
  
 //ADDING LISTENER TO THE BUTTON
butt.addActionListener(new listener());


  
 
}
    
    catch(Exception err){
    
  JOptionPane.showMessageDialog(null,"ERROR: "+err,"message",JOptionPane.WARNING_MESSAGE);
  
    }
    }
        
        
        
        
        
        
        
        //UPDATE RESTAURANT FUNCTION
        
        public void updateRestaurant(){
            try{
    
                  if(restaurants.isEmpty()){
       throw new Exception("There are currently no restaurants");
       }
         //TAKING OWNER INPUT 
        String ownerinput=JOptionPane.showInputDialog("Enter the Restaurant name to Update:");
        boolean namefound=false;
        int objpos=0;
        //SEARCHING THE RESTAURANTS LIST
     for(int i=0;i<restaurants.size();i++){
     
     if(restaurants.get(i).branchname.equals(ownerinput)){namefound=true;objpos=i;
     }
     
  
     }
    
    if(namefound==false){throw new Exception("Restaurant name not found");}
    else{
        JFrame frame=new JFrame("UPDATING");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
       frame.setSize(400, 200);
       frame.getContentPane().setBackground( Color.PINK );
    JLabel  lb1=new JLabel("SELECT:");
    JLabel  lb2=new JLabel("1-TO UPDATE RESTAURANT BRANCH NAME");
    JLabel  lb3=new JLabel("2-TO UPDATE NUMBER OF TABLES");
    JLabel  lb4=new JLabel("3-TO UPDATE NUMBER OF WAITERS IN THIS BRANCH");
    lb1.setBounds(5,5,900,50);
    lb2.setBounds(5,25,900,50);
    lb3.setBounds(5,45,900,50);
    lb4.setBounds(5,65,900,50);
    frame.add(lb1);
    frame.add(lb2);
    frame.add(lb3);
    frame.add(lb4);
    frame.setVisible(true);
    int choice=Integer.parseInt(JOptionPane.showInputDialog("Enter the input:"));
    //CASE 1
    if(choice==1){

    String newname=JOptionPane.showInputDialog("Enter the new name:");
    restaurants.get(objpos).branchname=newname;
    JOptionPane.showMessageDialog(null,"UPDATED SUCCESSFULLY","message",JOptionPane.INFORMATION_MESSAGE);
    frame.setVisible(false);
  
    }

       //CASE 3
    if(choice==2){
    int newnum=Integer.parseInt(JOptionPane.showInputDialog("Enter the new number of tables:"));
    restaurants.get(objpos).noOftables=newnum;
  
      JOptionPane.showMessageDialog(null,"UPDATED SUCCESSFULLY","message",JOptionPane.INFORMATION_MESSAGE);
       frame.setVisible(false);
    }
       //CASE 4
    if(choice==3){
    
    int newnum=Integer.parseInt(JOptionPane.showInputDialog("Enter the new number of Waiters:"));
    restaurants.get(objpos).noOfwaiters=newnum;
       System.out.println(); 
       JOptionPane.showMessageDialog(null,"UPDATED SUCCESSFULLY","message",JOptionPane.INFORMATION_MESSAGE);
        frame.setVisible(false);
    }
 
     File myfile=new File("RestaurantsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile.delete(); 
    //WRITING EACH RESTAURANT TO THE FILE
    FileOutputStream fileout=new FileOutputStream("RestaurantsData.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<restaurants.size();i++){
    write.writeObject(restaurants.get(i));
    }
      
    write.close();

    
    }
        
            }
            
                    catch(java.util.InputMismatchException err){
JOptionPane.showMessageDialog(null,"PLEASE ENTER INPUT FROM THE ABOVE CHOICES","warning",JOptionPane.ERROR_MESSAGE);

}
            catch(Exception err){
             JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
            
            }

        }
        
        //DELETE RESTAURANT FUNCTION
        void deleteRestaurant(){
        try{    

            if(restaurants.isEmpty()){
       throw new Exception("There are currently no restaurants");
       }
             //TAKING OWNER INPUT
         JOptionPane.showMessageDialog(null,"WELCOME OWNER");
    
        String ownerinput=JOptionPane.showInputDialog("Enter a restaurant branch name to delete from:");
        boolean namefound=false;
        int objpos=0;
        //SEARCHING THE RESTAURANTS LIST
     for(int i=0;i<restaurants.size();i++){
     
     if(restaurants.get(i).branchname.equals(ownerinput)){namefound=true;objpos=i;}
     
  
     }
    
    if(namefound==false){throw new Exception("Restaurant name not found");}
    else{
        JFrame frame=new JFrame("DELETING");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
       frame.setSize(400, 200);
       frame.getContentPane().setBackground( Color.PINK );
    JLabel  lb1=new JLabel("SELECT:");
    JLabel  lb2=new JLabel("1-TO DELETE RESTAURANT");
    JLabel  lb3=new JLabel("2-TO DELETE MENUE ITEMS");
    JLabel  lb4=new JLabel("3-TO DELETE TABLES");
    JLabel  lb5=new JLabel("4-TO DELETE WAITERS IN THIS BRANCH");
    lb1.setBounds(5,5,900,50);
    lb2.setBounds(5,25,900,50);
    lb3.setBounds(5,45,900,50);
    lb4.setBounds(5,65,900,50);
    lb5.setBounds(5, 85, 900,50);
    frame.add(lb1);
    frame.add(lb2);
    frame.add(lb3);
    frame.add(lb4);
    frame.add(lb5);
    frame.setVisible(true);
    int choice=Integer.parseInt(JOptionPane.showInputDialog("Enter your input:"));;
    //CASE 1
    if(choice==1){

    restaurants.remove(objpos);
    JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY","deleting",JOptionPane.INFORMATION_MESSAGE);
   frame.setVisible(false);
    }
        //CASE 2
    if(choice==2){
    
  for(int i=0;i<restaurants.get(objpos).menuitems.length;i++){
restaurants.get(objpos).menuitems[i]="";
  
  }

     
         JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY","deleting",JOptionPane.INFORMATION_MESSAGE);
         
            frame.setVisible(false);
    }
       //CASE 3
    if(choice==3){
  
    restaurants.get(objpos).noOftables=0;
       
       JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY","deleting",JOptionPane.INFORMATION_MESSAGE);
          frame.setVisible(false);
    }
       //CASE 4
    if(choice==4){

    restaurants.get(objpos).noOfwaiters=0;
     
      JOptionPane.showMessageDialog(null,"DELETED SUCCESSFULLY","deleting",JOptionPane.INFORMATION_MESSAGE);
         frame.setVisible(false);
    }
    //UPDATING OUR DATA FILE
     File myfile=new File("RestaurantsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile.delete(); 
    //WRITING EACH RESTAURANT TO THE FILE
    FileOutputStream fileout=new FileOutputStream("RestaurantsData.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<restaurants.size();i++){
    write.writeObject(restaurants.get(i));
    }
      
    write.close();
    
    }
    }
        
            
            catch(Exception err){
                
             JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
            
            }
        }
        
        
        void hirewaiter(){
            try{

String wName=JOptionPane.showInputDialog("Enter the name of the waiter:");
int wAge=Integer.parseInt(JOptionPane.showInputDialog("Enter the age of the waiter:"));
int wID=Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the waiter:"));
//ADDING WAITER TO THE LIST
waiters.add(new Waiter(wName,wAge,wID));
//REWRITING THE FILE
     File myfile=new File("WaitersData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile.delete(); 
    //WRITING EACH RESTAURANT TO THE FILE
    FileOutputStream fileout=new FileOutputStream("WaitersData.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<waiters.size();i++){
   write.writeObject(waiters.get(i));
    }
      
    write.close();
     JOptionPane.showMessageDialog(null,"THE WAITER IS HIRED");
            }
            catch(Exception err){
           JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
            
            }
}

          
        
        
//FIRING THE WAITER(DELETING)
  void firewaiter(){
            try{

        if(waiters.isEmpty()){throw new Exception("NO WAITERS FOUND");}

int wID=Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the waiter to fire:"));;

//SEARCHING THE WAITER
boolean isfound=false;
int pos=0;
for(int i=0;i<waiters.size();i++){

if(waiters.get(i).ID==wID){isfound=true;
pos=i;}

}

  if(isfound==false){throw new Exception("WAITER ID NOT FOUND");}
  
  else{waiters.remove(pos);}

//REWRITING THE FILE
     File myfile=new File("WaitersData.bin");
    //DELETING THE OLD FILE TO PREVENT AC EXCEPTION
    myfile.delete(); 
    //WRITING EACH WAITER TO THE FILE
    FileOutputStream fileout=new FileOutputStream("WaitersData.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<waiters.size();i++){
   write.writeObject(waiters.get(i));
    }
      
    write.close();
    JOptionPane.showMessageDialog(null,"حسبي الله ونعم الوكيل اطعت عيشي");
    
    
            }
            catch(Exception err){
           JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
            }
}

       
  void readwaiters(){
  
  
  try{
         JFrame frame=new JFrame("Waiters");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.getContentPane().setBackground( Color.PINK );
           
 File filee=new File("WaitersData.bin");
if(filee.exists()==false){throw new Exception("NO WAITERS TO SHOW");}

FileInputStream filein=new FileInputStream("WaitersData.bin");
ObjectInputStream read=new ObjectInputStream(filein);
int n=10;
while(filein.available()!=0){
Waiter obj=(Waiter)read.readObject();
JLabel label=new JLabel("Waiter name: "+obj.name+"    Waiter age: "+obj.age+"    ID: "+obj.ID);
 label.setBounds(5,n,900,50);
 frame.add(label);
n+=20;
}
JButton butt=new JButton("Hide Waiters");
butt.setBounds(5, n+20, 140, 20);
read.close();
frame.add(butt);

 frame.setSize(500, n+200);
frame.setVisible(true);



  class listener implements ActionListener{
       
   public void actionPerformed(ActionEvent action){
       
if(action.getSource()==butt){

frame.setVisible(false);
}
    
    }


  }
  
  //ADD BUTTON LISTENER
butt.addActionListener(new listener());
}

catch(Exception err){

JOptionPane.showMessageDialog(null,"ERROR"+err,"message",JOptionPane.ERROR_MESSAGE);
}
  
  
  }
  
  
      
    
    //SYSTEM FUNCTION
    
    void ShowOwnerSystem(){
        try{
   JOptionPane.showMessageDialog(null,"WELCOME OWNER!");
   JFrame frame=new JFrame("Owner System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(400, 500);
           frame.getContentPane().setBackground( Color.PINK );
     JLabel lb1=new JLabel("ENTER:");
     lb1.setBounds(5,10,900,50);
     JLabel lb2=new JLabel("1-TO ADD RESTAURANT");
     lb2.setBounds(5,30,900,50);
     JLabel lb3=new JLabel("2-TO READ YOUR RESTAURANTS");
     lb3.setBounds(5,50,900,50);
     JLabel lb4=new JLabel("3-TO UPDATE RESTAURANT");
     lb4.setBounds(5,70,900,50);
     JLabel lb5=new JLabel("4-TO DELETE RESTAURANT");
     lb5.setBounds(5,90,900,50);
     JLabel lb6=new JLabel("5-TO HIRE A WAITER");
     lb6.setBounds(5,110,900,50);
     JLabel lb7=new JLabel("6-TO FIRE A WAITER");
     lb7.setBounds(5,130,900,50);
     JLabel lb8=new JLabel("7-TO READ WAITERS DATA");
     lb8.setBounds(5,150,900,50);
     JLabel lb9=new JLabel("8-TO EXIT");
     lb9.setBounds(5,170,900,50);
     JLabel lb=new JLabel("Enter the input:");
     lb.setBounds(5,200,900,50);
     
 JTextField text=new JTextField();
 text.setBounds(5, 240, 300, 20);
 
 JButton button=new JButton("Submit");
 button.setBounds(5, 270, 130, 30);

  
   frame.add(lb1);
   frame.add(lb2);
   frame.add(lb3);
   frame.add(lb4);
   frame.add(lb5);
   frame.add(lb6);
   frame.add(lb7);
   frame.add(lb8);
   frame.add(lb9);
   frame.add(lb);
   frame.add(text);
   frame.add(button);
   frame.setVisible(true);
   
   
   class listener implements ActionListener{
       @Override
   public void actionPerformed(ActionEvent action){
       
       if(action.getSource()==button){
    if(text.getText().equals("1")){
      addRestaurant();
   
    }
        
      if(text.getText().equals("2")){
      readRestaurants();
    }
        
        
       if(text.getText().equals("3")){
      updateRestaurant();

    }
        
      if(text.getText().equals("4")){
      deleteRestaurant();

    }
                        
        if(text.getText().equals("5")){
      hirewaiter();

    }
        
        if(text.getText().equals("6")){
      firewaiter();

    }         
       if(text.getText().equals("7")){
      readwaiters();

    }    
          if(text.getText().equals("8")){
 
         JOptionPane.showMessageDialog(null,"GOODBYE OWNER");
          frame.setVisible(false);}
   }
   
   
   }
        
    
    }

   //ADDING LISTENER
     button.addActionListener(new listener());
    
    }
    
          
        
             catch(java.util.InputMismatchException err){
     
        JOptionPane.showMessageDialog(null,"TRY AGAIN,PLEASE ENTER A NUMBER FROM THE LIST","message",JOptionPane.ERROR_MESSAGE);
        }
    
        
        
    
    
    }
    
    
}




