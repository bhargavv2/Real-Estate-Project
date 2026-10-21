package realestate.model;
public abstract class User {
    private final long id; private final String name,email,phone,role;
    protected User(long id,String name,String email,String phone,String role){this.id=id;this.name=name;this.email=email;this.phone=phone;this.role=role;}
    public long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public String getPhone(){return phone;} public String getRole(){return role;}
}
