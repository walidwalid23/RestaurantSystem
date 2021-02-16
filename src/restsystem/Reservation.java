

package restsystem;
import java.io.Serializable;


public class Reservation implements Serializable {
   public String branchname;
    public int noOftables;
    public String date;
    
    Reservation(String name,int numtables,String date){
    this.branchname=name;
    this.noOftables=numtables;
    this.date=date;
    
    
    }
    
}
