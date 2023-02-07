# Multithreaded_Client_Server_App

# How to run
Run in IDE and send a HTTP request to the server on port 8080 by either using your browser or postman and sending it to: localhost:8080

# Observation
We can see a significant increase in efficeny when using multithreaded server than the singlethreaded server.
When connecting with multiple clients we do not have to wait for a task to finish on the multithreaded server before starting on the next task, meanwhile the singlethreaded have to wait for each one to finish.

[singlethreaded](documentation/singlethreaded.png)
[multithreaded](documentation/multithreaded.png)
