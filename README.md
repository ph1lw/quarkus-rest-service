# quarkus-rest-service
Simple rest service with quarkus 1.0.0 by Philipp Wurm <phiwu@gmx.at>

This service provides a simple rest api with 3 methods:
* GET hello
    * /rest/v1/hello
        * returns "Hello World".
* GET helloUser
    * /rest/v1/hello/{username}
        * returns "Hello <UserName>" depending on the username.
* POST randomNumber
    * /rest/v1/random
    * expects a random range as json in body, e.g. `{"from": 0, "to": 100}`.
        * returns a random value between from and to.
* GET healthCheck
    * /health/ready
        * returns a ready message
        
        
### Prerequisites
* JDK 11+ installed with JAVA_HOME configured appropriately
* Apache Maven 3.5.3+

### Run example
To start the example in development mode, just run `mvn clean compile quarkus:dev`
