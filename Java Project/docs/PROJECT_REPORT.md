# Real Estate Property Listing System

## Title page

**Real Estate Property Listing System**  
College project report  
Submitted by: ____________________  
Roll number: ____________________  
Department / college: ____________________  
Guide: ____________________  
Academic year: ____________________

## Abstract

This project is a role-based Java console application for managing real estate listings. Buyers can browse and filter available properties and register interest. Sellers can create, update, deactivate their own listings, and review interested buyers. Administrators can inspect users, listings, and interest records. Java connects to Microsoft SQL Server through JDBC; data access uses DAO classes and prepared statements. The database stores account roles, property attributes, and buyer-property interest records. Passwords use salted PBKDF2-HMAC-SHA256 hashes. The project demonstrates inheritance, encapsulation, abstraction, polymorphism, collections, validation, and exception handling.

## Introduction and problem statement

Property details are often spread across messages, paper records, or unrelated websites. This makes searching, updating listings, and tracking buyer enquiries difficult. This project centralizes these tasks in a small database-backed application. Its scope is educational and does not replace a commercial listing platform.

## Objectives

- Store user accounts and property listings centrally.
- Give buyers search and interest functions.
- Restrict sellers to their own listing changes.
- Give administrators visibility for basic moderation.
- Demonstrate object-oriented Java and JDBC/SQL Server design.

## Existing and proposed systems

Manual or fragmented tracking can produce stale listings, duplicated enquiries, and weak access control. The proposed application keeps listings in a relational database, validates inputs, applies role-aware operations, and enforces unique account and interest constraints. Advantages include centralized data, repeatable searches, simpler updates, and auditable interest timestamps.

## Requirements

Hardware: any computer capable of running a modern JVM and SQL Server; 4 GB RAM recommended for development. Software: JDK 17+, Maven, SQL Server, Azure Data Studio, terminal/IDE, Git. See README for setup and execution.

## Architecture

The console UI (`Main`) collects user input. Services perform validation and application-level flow. DAOs contain SQL and map rows to model objects. `DatabaseConnection` centralizes JDBC setup. SQL Server holds persistent data. Mermaid diagrams, class structure, workflow, use cases, ER model, and context-level data flow are in `DIAGRAMS.md`.

## Database design

`users` stores identity, unique email, PBKDF2 hash and role. One seller can list many properties (one-to-many). `properties.seller_id` references `users.user_id`. Buyers and properties have a many-to-many relationship represented by `interests`: each interest row belongs to one buyer and one property and records date/status. A unique `(buyer_id, property_id)` constraint prevents duplicate interest. Foreign keys and checks protect referential and numeric integrity. SQL is provided in `sql/schema.sql`.

## Implementation details

The model package contains `User` and role subclasses plus abstract `Property` and four concrete property types. DAOs implement account, property, and interest persistence. `AuthenticationService` validates registrations and delegates login. `PropertyService` validates numeric and required property values. SQL values are bound through prepared statements. Seller updates and deactivations include both property ID and seller ID in the SQL predicate; administrator mode explicitly bypasses seller ownership.

## OOP concepts used

- **Encapsulation:** model fields are private; callers use getters and controlled setters.
- **Inheritance:** `Buyer`, `Seller`, and `Admin` extend abstract `User`; property classes extend `Property`.
- **Abstraction:** abstract `Property` defines the common listing fields and requires a type.
- **Polymorphism/overriding:** subtype `displayDetails()` overrides the base implementation; a `List<Property>` can hold any subtype.
- **Overloading:** the implementation intentionally favors named methods and does not rely on artificial overloads; Java constructors provide initialization with parameters. Future overloads can be added when distinct call forms are useful.
- **Collections:** DAOs return `List<Property>` (an `ArrayList`) for result sets and stream operations.
- **Exceptions:** invalid input is rejected with `IllegalArgumentException`; JDBC failures surface as `SQLException` and are caught at the interactive boundary.
- **JDBC:** DAOs use `Connection`, `PreparedStatement`, and `ResultSet` with try-with-resources.
- **Password safety:** salted PBKDF2 hashes are stored; raw passwords are not stored.

## CRUD and access control

Create inserts a listing. Read supports browse, search, seller view, and administrator view. Update modifies listing details. Delete is implemented as a reversible `INACTIVE` status update so interest history remains. Seller ownership is part of update/deactivate SQL; admin actions pass an explicit administrator flag. Public registration permits only buyer/seller roles.

## Application workflow

The application starts at registration/login. On successful login it creates a role-specific model object and shows the role's menu. Buyers search and express interest. Sellers manage their own listings and review enquiries. Administrators inspect users and all records and deactivate unsuitable listings. Logging out returns to the start menu. See `DIAGRAMS.md`.

## Testing and results

The SQL Server schema was executed in Azure Data Studio and verified to contain the `users`, `properties`, and `interests` tables plus the expected indexes. The Java application has not been run against the database because Maven is unavailable in this environment. The test plan's application-level actual-result fields remain placeholders.

## Limitations and future enhancements

The application is console-only and has no image uploads, map, email notifications, recommendation engine, online booking/payment, mobile client, or hosted deployment. Production use would also need stronger password policy, login throttling, audit logs, account recovery, secure secret management, and broader integration tests. Future versions can add Swing/web UI, image storage, maps, email alerts, advanced search, recommendations, bookings/payments, cloud deployment, and a mobile app.

## Conclusion

The project demonstrates a complete educational path from role-based Java menus through service validation and DAO persistence to relational constraints in SQL Server. It is structured for explanation and extension, while transparently documenting that live application verification must be completed in a configured environment.

## References

- Oracle Java Documentation, https://docs.oracle.com/en/java/
- Microsoft SQL Server documentation, https://learn.microsoft.com/sql/sql-server/
- Microsoft JDBC Driver for SQL Server, https://learn.microsoft.com/sql/connect/jdbc/
- OWASP Password Storage Cheat Sheet, https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
