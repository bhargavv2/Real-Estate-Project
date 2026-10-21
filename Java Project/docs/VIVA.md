# Viva questions and beginner-friendly answers

1. **What does this project do?** It stores property listings and provides separate buyer, seller, and administrator operations.
2. **Why did you choose this topic?** It models a familiar real-world process with searchable data and clear user roles.
3. **What is Java?** Java is an object-oriented programming language that runs compiled bytecode on a JVM.
4. **What is a class?** A class describes the fields and behavior of objects.
5. **What is an object?** An object is a runtime instance of a class with its own state.
6. **What is OOP?** Object-oriented programming organizes software around objects and their responsibilities.
7. **What is encapsulation?** Keeping data private and controlling access through methods; model fields use this.
8. **What is inheritance?** A subclass reuses and specializes a superclass; Buyer, Seller, and Admin extend User.
9. **What is abstraction?** Showing common operations while hiding implementation details; Property is abstract.
10. **What is polymorphism?** A common reference can represent different subtypes and dispatch overridden methods at runtime.
11. **Where is overriding used?** Property subclasses override `getType()` and `displayDetails()`.
12. **What is overloading?** Same method name with different parameter lists. This implementation avoids unnecessary overloads.
13. **What is a constructor?** A special method that initializes a new object.
14. **Why use an abstract Property class?** Common listing data belongs in one place while each type supplies its identity.
15. **Why use separate property subclasses?** They demonstrate subtype behavior and make adding type-specific details possible.
16. **What is an interface?** A contract of methods a class agrees to implement. None was added here because there was no natural second implementation requiring it.
17. **What is a collection?** An object that groups objects; DAOs use lists to return query results.
18. **What is JDBC?** Java's standard API for connecting to and executing operations on relational databases.
19. **What is a DAO?** A Data Access Object that separates SQL/database operations from UI and business logic.
20. **What is a prepared statement?** A parameterized SQL statement where values are bound separately, helping prevent SQL injection.
21. **What is SQL?** A language used to define and manipulate relational database data.
22. **What is a primary key?** A column (or columns) that uniquely identifies a table row.
23. **What is a foreign key?** A constraint that requires a referenced row to exist in another table.
24. **What is CRUD?** Create, Read, Update, and Delete, the core data-management operations.
25. **Why soft-delete a property?** Setting it INACTIVE preserves history and references while hiding it from buyer search.
26. **How are seller and property related?** One seller can list many properties; each property references one seller.
27. **How are buyer and property interests represented?** An interests row connects one buyer to one property and records date/status.
28. **How are duplicate interests blocked?** The database has a unique constraint on buyer ID and property ID.
29. **How are passwords stored?** As salted PBKDF2-HMAC-SHA256 hashes with 210,000 iterations, not plain text.
30. **What does role-based access mean here?** The application presents different actions by role, and seller writes additionally constrain owner ID in SQL.
31. **How can administrators be created?** Through a trusted setup process, not public registration, which accepts only buyer/seller.
32. **How are invalid prices handled?** Service validation rejects nonpositive amounts, with database checks as a second guard.
33. **What is exception handling?** Catching or propagating errors so the application can report failure rather than crash unexpectedly.
34. **What is try-with-resources?** Java syntax that automatically closes resources such as JDBC connections and statements.
35. **What happens when the database is unavailable?** JDBC throws SQLException; the menu boundary reports the operation failure.
36. **How does property search work?** A prepared query filters availability, partial location, type, price ceiling, and minimum bedrooms.
37. **Why use BigDecimal for price?** Decimal arithmetic avoids binary floating-point rounding issues in monetary values.
38. **What is an index?** A database structure that can speed up lookups, at the cost of storage and some write overhead.
39. **What is a limitation?** The current UI is console-based; the database schema exists but the Java JDBC application still needs a Maven run against SQL Server.
40. **What would you add next?** A graphical/web interface, property images, maps, notifications, recommendations, and stronger production security.
