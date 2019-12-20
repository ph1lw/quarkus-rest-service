# quarkus-rest-service
Simple rest service with quarkus 1.0.0 by Philipp Wurm <phiwu@gmx.at>

This service provides a simple rest api with 3 methods:
* hello
    * GET /rest/v1/hello
        * returns "Hello World".
* helloUser
    * GET /rest/v1/hello/{name}
        * returns "Hello <UserName>" depending on the username.
* randomNumber
    * POST /rest/v1/random
    * expects a random range as json in body, e.g. `{"range_from": 0, "range_to": 100}`.
        * returns a random value between from and to.
* healthCheck
    * GET /health/ready
        * returns a ready message
* Swagger-UI
   * GET /swagger-ui
      * displays applications rest api in swagger-ui
    
        
### Prerequisites
* JDK 11+ installed with JAVA_HOME configured appropriately
* Apache Maven 3.5.3+

### Run example
To start the example in development mode, just run `mvn clean compile quarkus:dev`
