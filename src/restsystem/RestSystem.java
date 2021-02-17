package restsystem;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class RestSystem {

    public static void main(String[] args) {
        try{
       
    Owner owner=new Owner();
    Customer customer=new Customer();
    
     JPanel objj=new JPanel();
    
   JFrame frame=new JFrame("The Restaurant");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setSize(900, 500);
            frame.getContentPane().setBackground( Color.PINK );
            
       //IMAGE
       ImageIcon img=new ImageIcon("D:\\RestSystem\\rest.png");
       JLabel restimg=new JLabel(img);
       restimg.setBounds(230,1,400,200);
      //ICONS 
      ImageIcon icon1=new ImageIcon("D:\\RestSystem\\owner.png");
      ImageIcon icon2=new ImageIcon("D:\\RestSystem\\cart.png");
      ImageIcon icon3=new ImageIcon("D:\\RestSystem\\exit.png");
      //BUTTONS
     JButton butt1=new JButton("VIEW OWNER SYSTEM",icon1);
     butt1.setBounds(140,230,270,60);
     
     
     JButton butt2=new JButton("VIEW CUSTOMER SYSTEM",icon2);  
     butt2.setBounds(430,230,270,60);
    JButton butt3=new JButton(icon3);     
    butt3.setBounds(280,330,270,60);

   frame.add(restimg);
   frame.add(butt1);
   frame.add(butt2);
   frame.add(butt3);
   

   frame.setVisible(true);
     
   
   class listener implements ActionListener{
      @Override 
   public void actionPerformed(ActionEvent action){
       
 
    if(action.getSource()==butt1){
      owner.ShowOwnerSystem();
   
    }
        
      if(action.getSource()==butt2){
         customer.ShowCustomerSystem();
    }
        
        
          if(action.getSource()==butt3){
 
         JOptionPane.showMessageDialog(null,"GOODBYE");
          System.exit(0);}
   }
   
   
   
        
    
    }

   //ADDING LISTENERS
     butt1.addActionListener(new listener());        
     butt2.addActionListener(new listener());  
     butt3.addActionListener(new listener());  


        }

        
        catch(java.util.InputMismatchException err){
        System.out.println();
        JOptionPane.showMessageDialog(null,"ERROR: "+err,"message",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}