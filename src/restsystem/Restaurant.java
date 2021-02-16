
package restsystem;
import java.io.Serializable;

public class Restaurant implements Serializable{
    public String branchname;
    int itemsNo;
    public String[] menuitems=new String[itemsNo];
    public int noOftables;
    public int noOfwaiters;
    

    public Restaurant(String name,int itemsnumber,String[] items,int tables,int waiters){  
    
     branchname=name;
     itemsNo=itemsnumber;
     menuitems=items;
     noOftables=tables;
     noOfwaiters=waiters;
    
    
    }
    
    
}
