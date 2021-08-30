# APIRest
## Diseño de aplicaciones Web

1. Download the repository
```
git clone https://github.com/jcpenap/mutantes.git
```
2. Go to mutantes resources
```
cd mutantes/src/main/resources
```
3. Edit file application.properties and choose the enviroment
```
DEVELOP
spring.profiles.active=dev 

PROD
spring.profiles.active=prod 
```
4. Edit file application-env.yml for develop and change db properties
```
url
username
password
```
5. JUnit Test 
```
src/test/java/com/mercadolibre/controller/MutantControllerTest.java
src/test/java/com/mercadolibre/service/MutantServiceImplTest.java
```
6. Endpoints
```
POST - http://localhost:8083/mutant/
Body:
{
    "dna": [
        "ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"
    ]
}
```
```
GET - http://localhost:8083/stats
```