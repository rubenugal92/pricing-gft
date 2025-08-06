# GET STARTED!



<div align="center">
INTEGRATION TEST
<pre>
1)mvn clean install --compile application
2)mvn test --run integration tests
</pre>

<pre>
REAL TEST
  
1)cd docker
2)docker-compose up --build

docker-compose file will pull postgresql 13.5 image, and it will create a database with table prices and will execute init.sql (insert into)
As a result this is just a technical assessment of a fake project, you will find the credentials in application.properties and in docker-compose file

3)mvn clean install --compile application
4)Run the application
</pre>



</div>
