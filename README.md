Textbook Selection and Management System

Technology Stack: JavaWeb
Server: Apache Tomcat 11
Database: MySQL 9.7

1. Teacher Module
Access the system interface by logging in with the designated teacher credentials (Username: t0001, Password: teach01).

(1) Textbook Information Query and Pagination: View a comprehensive list of all textbooks, with built-in pagination for seamless navigation.
(2) Advanced Fuzzy Search: Quickly locate specific textbooks by searching for keywords across multiple fields, including textbook name, author, publisher, and ISBN.
(3) Textbook Management: Easily add new textbooks, update existing details, or delete textbook records from the database.
(4) Security and Password Modification: Users can update their personal passwords under strict validation rules:

(1) The new password must be at least 6 characters long.
(2) It cannot consist of 6 identical characters (e.g., 111111 or aaaaaa are restricted).
(3) The user must input the correct current password to authorize the change.
(4) The two entries for the new password must match exactly.
(5) Logout: Securely terminate the session and exit the system.

2. Academic Administrator Module
Access the administrative backend by logging in with the default administrator credentials (Username: a0001, Password: admin01).

(1) Administrator Management: Authorize and create new academic administrator accounts and set their initial passwords.
(2) Teacher Information Management: Full administrative control to add, delete, modify, and query teacher profiles within the system.
(3) Textbook Management: Oversee the entire textbook inventory with the ability to add, delete, modify, and query all textbook information.
(4) Logout: Securely terminate the administrative session and exit the backend.
