package realestate.model;
import java.math.BigDecimal;
public abstract class Property {
    private long id,sellerId; private String title,location,description,status; private BigDecimal price; private int bedrooms,bathrooms; private double area;
    protected Property(long id,long sellerId,String title,String location,BigDecimal price,int bedrooms,int bathrooms,double area,String description,String status){this.id=id;this.sellerId=sellerId;this.title=title;this.location=location;this.price=price;this.bedrooms=bedrooms;this.bathrooms=bathrooms;this.area=area;this.description=description;this.status=status;}
    public abstract String getType();
    public String displayDetails(){return String.format("#%d %s (%s) | %s | %s | %s beds, %s baths | %.0f sqft | %s",id,title,getType(),location,price.toPlainString(),bedrooms,bathrooms,area,status);}
    public long getId(){return id;} public long getSellerId(){return sellerId;} public String getTitle(){return title;} public String getLocation(){return location;} public String getDescription(){return description;} public String getStatus(){return status;} public BigDecimal getPrice(){return price;} public int getBedrooms(){return bedrooms;} public int getBathrooms(){return bathrooms;} public double getArea(){return area;}
    public void setTitle(String v){title=v;} public void setLocation(String v){location=v;} public void setDescription(String v){description=v;} public void setPrice(BigDecimal v){price=v;} public void setBedrooms(int v){bedrooms=v;} public void setBathrooms(int v){bathrooms=v;} public void setArea(double v){area=v;} public void setStatus(String v){status=v;}
}
