package realestate.model;
import java.math.BigDecimal;
public final class Land extends Property { public Land(long id,long s,String t,String l,BigDecimal p,int b,int ba,double a,String d,String st){super(id,s,t,l,p,b,ba,a,d,st);} @Override public String getType(){return "LAND";} @Override public String displayDetails(){return "Land: "+super.displayDetails();} }
