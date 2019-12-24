
First load the Postman collection.

You can create, login, logout a user via **user-service** on port 8081.  
A simple service called **advertisement-service** is available on port 8082.  

Session is shared via common H2 database.  
When you log in, you have access to current user from other (micro)services!  
When you log out, user is logged out automatically from all services!


### UI
Since this microservice uses regular HTTP session, almost no action is required from UI.  
UI needs to create 2 forms (register and login) and 1 button (logout).  
The rest is handled by SESSION cookie.  
When user logs in, SESSION cookie is automatically sent to server in each request (this is done by browser).
