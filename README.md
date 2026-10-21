# Real Estate Property Listing System

A Java 17 console application using Microsoft SQL Server and JDBC. Roles are Buyer, Seller, and Admin. Passwords are stored as salted PBKDF2-HMAC-SHA256 hashes. DAO classes use prepared statements; the schema enforces unique email and duplicate-interest constraints.

## Requirements and setup

Install JDK 17+ and Maven, and run SQL Server. This project is configured for the open Azure Data Studio connection at `localhost:1433`. The project uses the local SQL Server instance configured for Azure Data Studio at `localhost:1433`.

1. Open `sql/schema.sql` in Azure Data Studio, select the connected `localhost,1433` server, and execute it. It creates the database and tables.
2. Create a least-privilege SQL Server login/user with SELECT, INSERT, UPDATE, and DELETE on the three tables. Do not use the `SA` account in the application.
3. Copy `.env.example` to `.env` and set the database login values. `DatabaseConnection` loads `.env` automatically from the project root; exported environment variables override those file values. `.env` is git-ignored and should remain private.
4. Run `mvn compile exec:java` from the project root.
5. Register a buyer or seller. Provision an administrator from a trusted local terminal by compiling the project and running `realestate.util.PasswordHashMain`, then insert the resulting hash with the SQL comment template in `sql/schema.sql`. Public registration deliberately cannot create admins.

## Menus

Start screen: register, login, or exit. Buyers browse/search and express interest. Sellers add, edit, deactivate their own listings and view expressions of interest. Admins view users/all listings, deactivate listings, and view interest records. Logging out returns to the start screen.

Search accepts partial location, a type, maximum price, and minimum bedrooms (`-1` means any). House/apartment/land/commercial types are supported. Land may use zero bedrooms/bathrooms.

## Project layout

`src/main/java/realestate/model`: user/property hierarchy. `dao`: parameterized SQL and row mapping. `service`: validation and business operations. `util`: JDBC connection and password hashing. `sql/schema.sql`: relational schema. `docs/`: report, diagrams, test plan, viva and presentation outline.

## Design notes

`User` encapsulates shared identity and is inherited by role classes. `Property` is abstract; each concrete subtype overrides `getType()` and `displayDetails()`. `PropertyDAO` maps rows into subtypes and returns `List<Property>`, so display is polymorphic. `PropertyService` validates property inputs before persistence. `PropertyDAO.update/deactivate` include seller ownership in their WHERE clause; admin operations can be authorized explicitly. The database connection values are external environment configuration. Never use real personal data in class demonstrations.

## Known project limits

This is a console implementation, without GUI, image handling, email, payment or deployment. Error reporting is intentionally simple for a teaching project. Password rules and brute-force protections should be strengthened for production. Database failure paths are caught at the menu boundary; the Java application still needs SQL Server integration testing. The Java sources compile with Maven; the app connects to the local database when the local SQL Server service is running.

See [the project report](docs/PROJECT_REPORT.md), [diagrams](docs/DIAGRAMS.md), [test plan](docs/TEST_PLAN.md), [presentation outline](docs/PRESENTATION.md), and [viva questions](docs/VIVA.md).
