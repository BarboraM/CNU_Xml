# CNU_Xml_zadání

Building an Application

Business requirements

        As a user I want to call an API to create XML entity which will have a name and the XML content itself
        
        As a user I want to call an API to list all entities
        
        As a user I want to call an API to view one entity with an ID

Optional requirements

        As a user I want to call an API to delete one entity with an ID
        
        As a user I want to call an API to update one entity with an ID

        As a user I want the application to perform validation of the documents before saving and throw away every document, which contains more than 10000 characters or a word PHP (case insensitive)

Technical aspects

        Create an application with Spring Boot, Spring JDBC, Spring REST

        Manage SQL by http://www.liquibase.org/, use H2 database.

        Structure your application into layers (database layer for data manipulation, presentation layer for API, (optional) business layer for logic)


        To simulate a client, you can e.g. use POSTMAN plugin for Chrome (or anything similar you prefer).
