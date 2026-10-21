# Faculty presentation outline

1. **Title:** Real Estate Property Listing System; student, roll number, guide.
2. **Why selected:** Property information and buyer enquiries are often scattered and hard to maintain.
3. **Problem and objectives:** Centralize listings, enable search, manage seller records, and track buyer interest.
4. **Features and roles:** Buyers browse/search/express interest; sellers manage own listings; admins review users and listings.
5. **Technology:** Java 17 console UI, JDBC, Microsoft SQL Server (managed in Azure Data Studio), Maven, PBKDF2 password hashes.
6. **Architecture:** Main menu → services → DAO → JDBC → SQL Server; show class diagram.
7. **Database:** Explain users to properties (one-to-many) and buyers to properties through interests (many-to-many bridge).
8. **OOP demonstration:** User inheritance; abstract Property; polymorphic property subtypes; private fields; DAO/service separation.
9. **Workflow/demo:** Register test seller, list a property, search as buyer, express interest, view the lead as seller.
10. **Testing/results:** Present actual completed cases from TEST_PLAN.md; do not claim unrun cases or display fabricated screenshots.
11. **Limits and future scope:** Console only; image/map/email/mobile/payment/cloud possibilities.
12. **Conclusion:** Summarize value and invite questions.

## Short spoken summary

“My project is a Java and SQL Server application for real estate listings. Buyers can search listings and express interest. Sellers can manage their own properties, and administrators can review users and listings. I used Java inheritance and abstraction to model users and property types, JDBC and DAO classes to access SQL Server, prepared statements for safer SQL, and PBKDF2 password hashes rather than plain-text passwords. The database links sellers to properties and records each buyer enquiry in an interests table.”
