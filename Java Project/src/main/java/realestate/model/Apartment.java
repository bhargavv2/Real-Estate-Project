package realestate.model;
import java.math.BigDecimal;
public final class Apartment extends Property { public Apartment(long id,long s,String t,String l,BigDecimal p,int b,int ba,double a,String d,String st){super(id,s,t,l,p,b,ba,a,d,st);} @Override public String getType(){return "APARTMENT";} @Override public String displayDetails(){return "Apartment: "+super.displayDetails();} }
