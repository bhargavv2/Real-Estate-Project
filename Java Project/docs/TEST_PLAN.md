# Test plan

Run these against the SQL Server database. Record actual outcomes after execution; all actual-result cells intentionally remain placeholders.

| ID | Scenario | Input / action | Expected result | Actual result |
|---|---|---|---|---|
| T01 | Successful login | Registered email and correct password | Session opens with correct role menu | To be recorded |
| T02 | Unsuccessful login | Wrong password | Generic invalid-credentials message; no session | To be recorded |
| T03 | Duplicate email | Register same email twice | Unique constraint rejects second account | To be recorded |
| T04 | Add valid property | Valid type, title, location, positive price/area | New AVAILABLE listing and generated ID | To be recorded |
| T05 | Invalid property data | Zero price or blank title | Validation rejects; no insert | To be recorded |
| T06 | Search location | Search substring of listing location | Matching available listings appear | To be recorded |
| T07 | Filter by price | Max price below one listing and above another | Only listings at/below limit appear | To be recorded |
| T08 | Update listing | Seller edits own property | New fields are persisted | To be recorded |
| T09 | Unauthorized update | Seller attempts another seller's ID | Zero rows changed; ownership denial | To be recorded |
| T10 | Express interest | Buyer selects available property | Interest saved once | To be recorded |
| T11 | Missing property ID | Interest/deactivate nonexistent ID | Clear not-found response; no corrupted record | To be recorded |
| T12 | Database unavailable | Stop SQL Server or use invalid URL | Operation error shown; application returns to menu | To be recorded |
| T13 | Deactivate listing | Owner or admin deactivates listing | Status becomes INACTIVE and is absent from buyer search | To be recorded |
| T14 | Duplicate interest | Same buyer repeats interest on same property | Unique key prevents duplicate; friendly message | To be recorded |
| T15 | Role access | Buyer tries seller/admin menu path | No such operation available in buyer menu | To be recorded |
