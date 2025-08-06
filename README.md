# GET STARTED!



<div align="center">

<pre>
mvn clean install --compile application
mvn test --run integration tests
</pre>

<pre>
Test with real database and POSTMAN:
  
cd docker
docker-compose up --build

(docker-compose file will pull postgresql 13.5 image, and it will create a database with table prices and will execute init.sql (insert into) )
</pre>



</div>
