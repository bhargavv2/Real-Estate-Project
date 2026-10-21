package realestate.model;
import java.math.BigDecimal;
public final class House extends Property { public House(long id,long s,String t,String l,BigDecimal p,int b,int ba,double a,String d,String st){super(id,s,t,l,p,b,ba,a,d,st);} @Override public String getType(){return "HOUSE";} @Override public String displayDetails(){return "House: "+super.displayDetails();} }
