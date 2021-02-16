
package restsystem;
import java.io.Serializable;

public class Order implements Serializable {
   
    
    public String itemname;
    public int price;
    public String paymentmethod;
    //constructor
    public  Order(String name,String payment){
    this.itemname=name;
    this.paymentmethod=payment;
    
    
    
    
}

}
