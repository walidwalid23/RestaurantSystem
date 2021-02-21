
package restsystem;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class Waiter implements Serializable{
 //ATTRIBUTES
public String name;
public int age;
public int ID;
public ArrayList<Order> orders=new ArrayList<>();
public ArrayList<Restaurant> restaurants=Owner.restaurants;
public ArrayList<Reservation> reservations=new ArrayList<>();


//FUNCTIONS
public Waiter(String name,int age,int id){
this.name=name;
this.age=age;
this.ID=id;

try{
  //READING THE OLD ORDERS FROM THE PREVIOUS FILE AND ADDING THEM TO THE LIST
    File ordersfile=new File("Orders.bin");
    
    if(ordersfile.exists()){
        
  FileInputStream filein=new FileInputStream("Orders.bin");
  ObjectInputStream read=new ObjectInputStream(filein);
    
  while(filein.available()!=0){
  
    Order myorder=(Order)read.readObject();
  orders.add(myorder);
  
  }
  filein.close();
  read.close();

  }
    
     //READING THE OLD RESERVATIONS FROM THE PREVIOUS FILE AND ADDING THEM TO THE LIST
     File myfilee=new File("ReservationsData.bin");
   
    if(myfilee.exists()){
        
  FileInputStream filein=new FileInputStream("ReservationsData.bin");
  ObjectInputStream read=new ObjectInputStream(filein);
    
  while(filein.available()!=0){
  
   Reservation myreserv=(Reservation)read.readObject();
  reservations.add(myreserv);
  
  
  }
    filein.close();
   read.close();

  }
    
       
    
    
}
catch(Exception err){
 JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.ERROR_MESSAGE );

}


}


//TAKING ORDER

void takeorder(Order order){
    try{

//ADD ORDER TO LIST
int randprice=(int)(Math.random()*191)+10;

int payment=Integer.parseInt(JOptionPane.showInputDialog("Alright Sir, The tottal price is:"+randprice+" LE:"));
while(payment!=randprice){
//FIRST CONDITION
if(payment<randprice){

payment+=Integer.parseInt(JOptionPane.showInputDialog(randprice-payment+" LE left sir:"));;

}

//SECOND CONDITION
if(payment>randprice){
JOptionPane.showMessageDialog(null,"Take "+(payment-randprice)+" Sir ");
payment=randprice;

}

}

//THIRD CONDITION
 if(payment==randprice){ 
     System.out.print("hi");
order.price=randprice;
orders.add(order);
//ADD ORDER TO FILE
File file=new File("Orders.bin");
file.delete();
 FileOutputStream fileout=new FileOutputStream("Orders.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<orders.size();i++){

        write.writeObject(orders.get(i));
    }
      
    write.close();
    JOptionPane.showMessageDialog(null,"THANKS SIR, YOUR ORDER WILL BE READY IN A MOMENT!");
}

    }

catch(Exception err){
 JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.ERROR_MESSAGE );

}
}

//READING ORDERS

void readorders(){
try{
       JFrame frame=new JFrame("Restaurants");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.getContentPane().setBackground( Color.PINK );
             
        File filee=new File("Orders.bin");
       if(filee.exists()==false){
throw new Exception("NO ORDERS TO SHOW");}

FileInputStream filein=new FileInputStream("Orders.bin");
ObjectInputStream read=new ObjectInputStream(filein);

int n=10;
while(filein.available()!=0){
Order obj=(Order)read.readObject();
 JLabel label=new JLabel("Item name: "+obj.itemname+"  Item Price: "+obj.price+"  Payment Method: "+obj.paymentmethod);
label.setBounds(5,n,900,50);
 frame.add(label);
n+=20;
}
read.close();

JButton butt=new JButton("Hide Orders");
butt.setBounds(5, n+30, 180, 30);
frame.add(butt);

 frame.setSize(900, n+200);
frame.setVisible(true);

class listener implements ActionListener{

public void actionPerformed(ActionEvent action){
if(action.getSource()==butt){

    frame.setVisible(false);
}


}

}

//ADDING LISTENER TO BUTTON
butt.addActionListener(new listener());

}

catch(Exception err){

 JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.ERROR_MESSAGE );
}

}


//UPDATING ORDER

void updateorder(String ordername)
{
    try{

        if(orders.isEmpty()){throw new Exception("NO ORDERS TO SHOW");}

boolean isfound=false;
int pos=0;
for(int i=0;i<orders.size();i++){

if(ordername.equals(orders.get(i).itemname)){
isfound=true;
pos=i;
}
}
//IF NOT FOUND
if(isfound==false){
throw new Exception("ITEM NOT FOUND");
}
//IF FOUND
else{
  JFrame frame=new JFrame("UPDATING");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
       frame.setSize(400, 200);
       frame.getContentPane().setBackground( Color.PINK );
    JLabel  lb1=new JLabel("SELECT:");
    JLabel  lb2=new JLabel("1-TO UPDATE THE ORDER");
    JLabel  lb3=new JLabel("2-TO UPDATE THE PAYMENTMETHOD");
    lb1.setBounds(5,5,900,50);
    lb2.setBounds(5,25,900,50);
    lb3.setBounds(5,45,900,50);
    frame.add(lb1);
    frame.add(lb2);
    frame.add(lb3);
    
    frame.setVisible(true);
    
int userinput=Integer.parseInt(JOptionPane.showInputDialog("Enter your input:"));

if(userinput==1){

   
    String orderinput=JOptionPane.showInputDialog("Enter the name of the new order:");

    orders.get(pos).itemname=orderinput;
}
    if(userinput==2){

     
    String paymentinput=JOptionPane.showInputDialog("Enter the payment method:");

    orders.get(pos).paymentmethod=paymentinput;
}
    
    File file=new File("Orders.bin");
file.delete();
 FileOutputStream fileout=new FileOutputStream("Orders.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<orders.size();i++){

        write.writeObject(orders.get(i));
    }
      
    write.close();
    JOptionPane.showMessageDialog(null,"ORDER UPDATED SUCCESSFULLY");
    frame.setVisible(false);
    }
    }

catch(java.util.InputMismatchException err){
JOptionPane.showMessageDialog(null,"PLEASE ENTER INPUT FROM THE ABOVE CHOICES","warning",JOptionPane.ERROR_MESSAGE);

}
    catch(Exception err){
JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.ERROR_MESSAGE);

}
}

//DELETING ORDER

void deleteorder(String ordername){
try{

    if(orders.isEmpty()){throw new Exception("NO ORDERS TO SHOW");}
boolean isfound=false;
int pos=0;

for(int i=0;i<orders.size();i++){

if(ordername.equals(orders.get(i).itemname)){
isfound=true;
//REMOVE THE WANTED ORDER
orders.remove(i);}

}
if(isfound==false){
throw new Exception("ITEM NOT FOUND");
}

//REWRITE THE ORDERS IN THE FILE
    
    File file=new File("Orders.bin");
file.delete();
 FileOutputStream fileout=new FileOutputStream("Orders.bin",true);
    ObjectOutputStream write=new ObjectOutputStream(fileout);
    for(int i=0;i<orders.size();i++){

        write.writeObject(orders.get(i));
    }
      
    write.close();
    JOptionPane.showMessageDialog(null,"ORDER DELETED SUCCESSFULLY");
}
catch(Exception err){
JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.ERROR_MESSAGE);

}


}

//ADDING RESERVATION

void addreservation(Reservation reserv){

try{

//CHECKING IF THE RESTAURANT NAME IS FOUND
boolean isfound=false;
int pos=0;

for(int i=0;i<restaurants.size();i++){

if(reserv.branchname.equals(restaurants.get(i).branchname)){
isfound=true;
pos=i;}

}

if(isfound==false){throw new Exception("RESTAURANT NOT FOUND!");}
//CHECKING IF THERE IS AVAILABLE TABLES IN THIS RESTAURANT
if(restaurants.get(pos).noOftables<reserv.noOftables){
JOptionPane.showMessageDialog(null,"SORRY SIR, THERE AREN'T ENOUGH TABLES IN THIS RESTAURANT ","message",JOptionPane.WARNING_MESSAGE);


}
else if(isfound && restaurants.get(pos).noOftables>=reserv.noOftables){
//ADDING THE RESERVATION TO THE LIST
reservations.add(reserv);
//REMOVING THE NUMBER OF TABLES RESERVED
restaurants.get(pos).noOftables-=reserv.noOftables;

//ADDING THE UPDATED RESTAURANTS TO THE FILE
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

  //ADDING THE UPDATED RESERVATIONS TO ITS FILE
       File myfile2=new File("ReservationsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile2.delete(); 
    //WRITING EACH RESERVATION TO THE FILE
    FileOutputStream fileout2=new FileOutputStream("ReservationsData.bin",true);
    ObjectOutputStream write2=new ObjectOutputStream(fileout2);
    for(int i=0;i<reservations.size();i++){
    write2.writeObject(reservations.get(i));
    }
      
    write2.close();
    
    JOptionPane.showMessageDialog(null,"RESERVATION ADDED SUCCESSFULLY");
  
}

}



catch(Exception err){
JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.WARNING_MESSAGE);

}



}

//DELETING RESERVATION

void deletereservation(String branchname){
try{

    if(reservations.isEmpty()){throw new Exception("NO RESERVATIONS TO SHOW");}
boolean isfound=false;
int pos=0;

for(int i=0;i<reservations.size();i++){

    if(reservations.get(i).branchname.equals(branchname)){
    
    isfound=true;
    pos=i;
    }
}
//IF NOT FOUND
if(isfound==false){throw new Exception("RESERVATION NOT FOUND!");}
//IF FOUND
else{
    //ADDING THE NUMBER OF FREE TABLES TO THE RESTAURANT
    int restpos=0;
    for(int i=0;i<restaurants.size();i++){
    if(reservations.get(pos).branchname.equals(restaurants.get(i).branchname)){
    
    restpos=i;
    }
    
    }
    
    restaurants.get(restpos).noOftables+=reservations.get(pos).noOftables;
    //ADDING THE UPDATED RESTAURANTS TO THE FILE
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
   
    //DELETING THE RESERVATION FROM THE LIST
    reservations.remove(pos);
    
    //ADDING THE UPDATED RESERVATIONS TO ITS FILE
       File myfile2=new File("ReservationsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile2.delete(); 
    //WRITING EACH RESERVATION TO THE FILE
    FileOutputStream fileout2=new FileOutputStream("ReservationsData.bin",true);
    ObjectOutputStream write2=new ObjectOutputStream(fileout2);
    for(int i=0;i<reservations.size();i++){
    write2.writeObject(reservations.get(i));
    }
      
    write2.close();
    
   JOptionPane.showMessageDialog(null,"RESERVATION DELETED SUCCESSFULLY");
     
}



}

catch(Exception err){

JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.WARNING_MESSAGE);

}

}


void updaterservation(String branchname){
try{

    Scanner input=new Scanner(System.in);
    
if(reservations.isEmpty()){throw new Exception("NO RESERVATIONS TO SHOW");}
boolean isfound=false;
int pos=0;

for(int i=0;i<reservations.size();i++){

    if(reservations.get(i).branchname.equals(branchname)){
    
    isfound=true;
    pos=i;
    }
}
//IF NOT FOUND
if(isfound==false){throw new Exception("RESERVATION NOT FOUND!");}
// IF FOUND
else{


 String bname=JOptionPane.showInputDialog("Enter the new branch name:");
 reservations.get(pos).branchname=bname;
 int tablesnum=Integer.parseInt(JOptionPane.showInputDialog("Enter the new number of tables:"));
 reservations.get(pos).noOftables=tablesnum;
 String date=JOptionPane.showInputDialog("Enter the new date:");;
 reservations.get(pos).date=date;
    
//UPDATING THE NUMBER OF TABLES IN THE RESTAURANT ACCORDING TO THE NEW UPDATE
 int restpos=0;
    for(int i=0;i<restaurants.size();i++){
    if(reservations.get(pos).branchname==restaurants.get(i).branchname){
    
    restpos=i;
    }
    
    }
    
    restaurants.get(restpos).noOftables-=reservations.get(pos).noOftables;
    //ADDING THE UPDATED RESTAURANTS TO THE FILE
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
   
       File myfile2=new File("ReservationsData.bin");
    //DELETING THE OLD FILE TO PREVENT IO EXCEPTION
    myfile2.delete(); 
    //WRITING EACH RESERVATION TO THE FILE
    FileOutputStream fileout2=new FileOutputStream("ReservationsData.bin",true);
    ObjectOutputStream write2=new ObjectOutputStream(fileout2);
    for(int i=0;i<reservations.size();i++){
    write2.writeObject(reservations.get(i));
    }
      
    write2.close();
   
     


  JOptionPane.showMessageDialog(null,"RESERVATION UPDATED SUCCESSFULLY");




}



}

catch(Exception err){

JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.WARNING_MESSAGE);
}
}

//READING RESERVATIONS

void readreservations(){
try{
    JFrame frame=new JFrame("Restaurants");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.getContentPane().setBackground( Color.PINK );
            
            
        File filee=new File("ReservationsData.bin");
       if(filee.exists()==false){
    throw new Exception("NO RESERVATIONS TO SHOW");}
    
    FileInputStream filein=new FileInputStream("ReservationsData.bin");
    ObjectInputStream read=new ObjectInputStream(filein);
    
    int n=10;
    while(filein.available()!=0){
    
    Reservation obj=(Reservation)read.readObject();
    JLabel label=new JLabel("branch name:"+obj.branchname+"       Number of tables:"+obj.noOftables+"     Date:"+obj.date);
     
    label.setBounds(5,n,900,50);
    frame.add(label);
     n+=20;
    
    }
    
    read.close();
    


JButton butt=new JButton("Hide Orders");
butt.setBounds(5, n+30, 180, 30);
frame.add(butt);

 frame.setSize(900, n+200);
frame.setVisible(true);

class listener implements ActionListener{

public void actionPerformed(ActionEvent action){
if(action.getSource()==butt){

    frame.setVisible(false);
}


}

}

//ADDING LISTENER TO BUTTON
butt.addActionListener(new listener());
}

catch(Exception err){
JOptionPane.showMessageDialog(null,"Error: "+err,"warning",JOptionPane.WARNING_MESSAGE);
}

}













}



