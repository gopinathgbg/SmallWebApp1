## spring-boot-weather-client-api

Purpose : Use openweathermap in spring boot API to Create secure endpoints to use <br/>

### Local run steps <br/>
1- Start Spring Boot REST API by running main method containing class WeatherApplication.java in your IDE. <br/>
<pre> 
spring-boot-weather-forecast-client-api $ mvn clean install -U -X <br/>
</pre>

swagger_ui can be accessed via http port 9898 from localhost : <br/>

### Tech Stack
<pre>
Java 17
Mysql Database 
spring boot
spring boot starter data jpa
spring boot starter web
spring-boot-starter-security
spring boot starter test
springdoc openapi ui
spring-boot-devtools
lombok
jjwt 0.9.1
jaxb-api
jaxb-runtime
jaxb-core
mockito-core
mockito-junit-jupiter
reactor-test
logback
maven
spring-boot-starter-webflux
springfox-boot-starter
springfox-swagger-ui
springfox-swagger2

</pre>


## API OPERATIONS
### Operation 1 : Get Weather Forecast By Postal Code  and User provided in http body
### Use of JWT token -> Please go to file UserService.java for credential as input to /auth API
Method : HTTP.POST <br/>
http://localhost:9898/authenticate 
{
    "username": "gopinath",
    "password": "password"
}

Response:
 {
    "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnb3BpbmF0aCIsImV4cCI6MTcyNzA0NDU1MSwiaWF0IjoxNzI3MDI2NTUxfQ.YlznegPESPez0uxtggVRlGPnAhekZBTzsffaXrxZLtU"
 } 


Method : HTTP.POST <br/>
1) URL :http://localhost:9898/api/weather<br/>
HTTP Request Body : <br/>
<pre>
{
    "user":"John",
    "postalCode":"29636"
}

</pre>

Curl Request : <br/>
<pre>
curl --location 'http://localhost:9898/api/weather' \
--header 'Content-Type: application/json' \
--data '{
    "user":"John",
    "postalCode":"29636"
}
</pre><br/>

Response :

HTTP response code 200 <br/>
<pre>
{
    "id": 13,
    "user": "John",
    "postalCode": "29636",
    "temperature": 26.01,
    "humidity": 80,
    "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
    "timestamp": "2024-09-23T22:17:38.4681525"
}

</pre>

### Operation 2 : Get Weather By all fetched for postal code so far 


Method : HTTP.GET <br/>
URL : http://localhost:9898/api/weather/29636 <br/>
Request Body : <br/>
<pre>
{  }
</pre>
Curl Request : <br/>

<br/>

Response :

HTTP response code 200 <br/>
<pre>
[
    {
        "id": 8,
        "user": "Gopinath",
        "postalCode": "29636",
        "temperature": 25.39,
        "humidity": 82,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T21:51:54.378298"
    },
    {
        "id": 9,
        "user": "Vaibhav",
        "postalCode": "29636",
        "temperature": 25.39,
        "humidity": 82,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T21:52:17.227279"
    },
    {
        "id": 12,
        "user": "Mrunal",
        "postalCode": "29636",
        "temperature": 26.01,
        "humidity": 80,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T22:17:05.398193"
    },
    {
        "id": 13,
        "user": "John",
        "postalCode": "29636",
        "temperature": 26.01,
        "humidity": 80,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T22:17:38.468153"
    }
]

</pre>
<br/>

### Operation 3 : Get Weather By all fetched for user so far 


Method : HTTP.GET <br/>
URL : http://localhost:9898/api/weather/user/Gopinath<br/>
Request Body : <br/>
<pre>
{  }
</pre>
Curl Request : <br/>

<br/>

Response :

HTTP response code 200 <br/>
<pre>
[
    {
        "id": 1,
        "user": "Gopinath",
        "postalCode": "94040",
        "temperature": 18.23,
        "humidity": 80,
        "weatherCondition": "{id=800, main=Clear, description=clear sky, icon=01d}",
        "timestamp": "2024-09-23T21:43:21.238747"
    },
    {
        "id": 2,
        "user": "Gopinath",
        "postalCode": "33631",
        "temperature": 31.16,
        "humidity": 68,
        "weatherCondition": "{id=800, main=Clear, description=clear sky, icon=01d}",
        "timestamp": "2024-09-23T21:46:59.242444"
    },
    {
        "id": 3,
        "user": "Gopinath",
        "postalCode": "73001",
        "temperature": 17.5,
        "humidity": 80,
        "weatherCondition": "{id=802, main=Clouds, description=scattered clouds, icon=03d}",
        "timestamp": "2024-09-23T21:49:13.189714"
    },
    {
        "id": 4,
        "user": "Gopinath",
        "postalCode": "29641",
        "temperature": 25.35,
        "humidity": 80,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T21:49:40.474937"
    },
    {
        "id": 5,
        "user": "Gopinath",
        "postalCode": "41702",
        "temperature": 24.13,
        "humidity": 68,
        "weatherCondition": "{id=803, main=Clouds, description=broken clouds, icon=04d}",
        "timestamp": "2024-09-23T21:49:54.143231"
    },
    {
        "id": 6,
        "user": "Gopinath",
        "postalCode": "49734",
        "temperature": 15.84,
        "humidity": 59,
        "weatherCondition": "{id=801, main=Clouds, description=few clouds, icon=02d}",
        "timestamp": "2024-09-23T21:50:22.498272"
    },
    {
        "id": 7,
        "user": "Gopinath",
        "postalCode": "29641",
        "temperature": 25.35,
        "humidity": 80,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T21:51:37.662232"
    },
    {
        "id": 8,
        "user": "Gopinath",
        "postalCode": "29636",
        "temperature": 25.39,
        "humidity": 82,
        "weatherCondition": "{id=804, main=Clouds, description=overcast clouds, icon=04d}",
        "timestamp": "2024-09-23T21:51:54.378298"
    }
]

</pre>

TO access swager api documents : http://localhost:9898/swagger-ui/ 
<br/>
