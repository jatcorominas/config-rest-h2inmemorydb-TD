README.MD

This is a Representational State Transfer (REST) Config CRUD (Create-Retrieve-Update-Delete) application that is a Spring-boot
microservice that manipulates config data on the H2 DB in-memory database.

Author: Jose Alfonso Corominas
email:  jatcorominas@gmail.com
cell:   +1-647-618-2080

1. Dependencies 

(a) Spring Boot version 2.0.3.RELEASE
(b) Mockito version 1.9.5
(c) Apache commons lang version 2.6
(d) Java version 1.8
(e) Maven version 3.2.3
(f) H2 in-memory database

2. Import the project as an existing maven project to Eclipse or IntelliJ

3. To build the project execute this command from the command line

mvn clean install 

4. To run the project execute this command from the command line

mvn spring-boot:run

5. The mandate from Rachel Zang is as follows

   Task #1
   Create a Config Service REST api using Spring Boot that implements below apis to return and update JSON documents representing an 
   application’s config properties.
   
   The application needs to store the JSON documents in an embedded SQL database (H2, HSQLDB, etc.) using Spring Data JPA (or any other 
   framework of your choice).

	•       GET /api/{appCode}/config/{version} – return JSON document for specified appCode and version
	•       POST /api/{appCode}/config/{version} – add new or update existing JSON document for specified appCode and version.
			 											JSON document should be included in the request body
	•       GET /api/{appCode}/config – return list of available versions in JSON sorted by last modified date in descending order

	Task #2
	Create unit tests for task #1 (controller, service and repository layers).

6. According to the REST convention, https://en.wikipedia.org/wiki/Representational_state_transfer, a POST is supposed to create a new
   resource provided that it does not exist in the first place. While a POST method can be programmed to update an existing resource doing so
   is not compliant with the REST convention. Therefore the POST method will not update any config records that already exist. It will only
   create a new one after determining that a config record with the same properties does not already exist.
   
7. ASSUMPTION

   A config record consists of an ID, APPCODE, PORT NUMBER, VERSION NUMBER and LAST MODIFIED DATE.

8. DATABASE 

   8.1 Schema
   
      A config table is created in H2 with the following properties
   
      create table config (
	   ID INT PRIMARY KEY,
	   APPCODE VARCHAR(30) NOT NULL,
	   PORT INT NOT NULL,
	   VERSION VARCHAR(30) NOT NULL,
	   LASTMODIFIEDDATE TIMESTAMP NOT NULL,
      );
   
   8.2 Sample data
   
      The config table is seeded with these records
       
      insert into config (id, appCode, port, version, lastModifiedDate)
      values(1001,'H2', 8090,'1','2021-02-01')
  			,(1002,'H2', 8976,'2','2021-02-02')
  			,(1003,'H2', 6564,'3','2021-02-03')
  			,(1004,'MONGODB', 5672,'1','2021-02-04')
  			,(1005,'MONGODB', 3471,'2','2021-02-05')
  			,(1006,'MONGODB', 7891,'3','2021-02-06')
  			,(1007,'KAFKA', 9843,'1','2021-02-07')
  			,(1008,'KAFKA', 2890,'2','2021-02-08')
  			,(1009,'KAFKA', 1895,'3','2021-02-09')
  			,(1010,'KAFKA', 2642,'4','2021-02-10');

9. REST URLS

   (a) GET /api/{appCode}/config/{version}
   
      Returns the config json object for an app code and version number. It is assumed that a config has one and only one
      configuration for an app code and version number
       
   (b) GET /api/{appCode}/config
   
      Returns the configurations for a given app code sorted in descending order by last modified date
   
   (c) POST /api/{appCode}/config/{version}
   
      Creates a new configuration provided that there is no existing config with the same properties
       
   (d) PUT /api/{appCode}/config/{version}
   
      Updates a configuration for a given app code and version number
      
10. Config Controller

    (a) @GetMapping("/{appCode}/config/{version}")
	     public ResponseEntity<Config> getConfigsByAppCodeAndVersion(@PathVariable("appCode") String appCode,  
	                                                                  @PathVariable("version") String version)
	    
	     Get a config by app code and version number. 
	     
	     Returns an HTTP status code of 200 if successful and 401 if not found
	 
	 (b) @GetMapping("/{appCode}/config")
	     public ResponseEntity<List<Config>> getConfigsByAppCode(@PathVariable("appCode") String appCode)
	     
	     Retrieves a list of configs for an app code.
	     
	     Returns an HTTP status code of 200 if successful and 401 if not found
	     
	 (c) @PostMapping("/{appCode}/config/{version}")
	     public Config postCustomer(@PathVariable("appCode") String appCode, 
	                                @PathVariable("version") String version, @RequestBody ConfigVO configvo)
	     
	     Create a new config record provided that there is no config in the database with the same property values
	     in the config value object.
	     
	     Returns an HTTP status code of 201 if successful.
	     
	 (d) @PutMapping("/{appCode}/config/{version}")
	     public ResponseEntity<Config> updateCustomer(@PathVariable("appCode") String appCode, 
	                                                  @PathVariable("version") String version, @RequestBody ConfigVO config)
	                                                  
	     Update an existing config given an app code and version number provided that it already exists in the database.
	     
	     Returns an HTTP status code of 200 if successful and 404 if not found.
	     
   
11. Service Layer

    (a) public Config findByAppCodeAndVersion(String appCode, String version)
    
        This message is invoked by the REST controller to retrieve a config given an app code and version number
    
    (b) public List<Config> findByAppCodeSortedByLastModifiedDateInDescendingOrder(String appCode)
    
        This message is invoked by the REST Controller to retrieve all the configurations for an app code sorted
        in descending order by last modified date.
        
    (c) public Config saveConfig(ConfigVO configvo)
    
        This message is invoked by the REST controller to create a new configuration provided that there is no
        config for the given app code and version number that exists in the database.
    
    (d) public Config updateConfig(String appCode, String version, ConfigVO configvo)
    
        This message is invoked by the REST controller to update an existing config.
        
12. Repository Layer

    (a) Config findByAppCodeAndVersion(String appCode, String version)
    
        Fetch a config with the app code and version
        
    (b) List<Config> findByAppCodeOrderByLastModifiedDateDesc(String appCode)
    
        Return all the configs given an app code sorted in descending order by last modified date.

13. Model

    (a) Config
    
        This ia an entity class that represents the config record in the H2 database
        
    (b) ConfigVO
    
        This is a config value object that is used to transfer data between layers


           
   

