# Getting Started

## Application startup - Quick

The easiest way to run the application is to run the <i>LinearRegressionDemoApplication</i> main class using the "mock"
spring profile.

* -Dspring.profiles.active=mock

This, however, will not source actual data and is only meant for testing purposes.

## Application startup - CoinApi

To source real world data, the application needs to use the _CoinApiBitcoinService_.

This is achieved by simply excluding the "mock"
spring profile when running the application (LinearRegressionDemoApplication main class) AND providing an api key.

To get an api key, follow this link:
[https://www.coinapi.io/pricing?apikey](https://www.coinapi.io/pricing?apikey)

To set the api key, the following options are available:

1) Override the application.properties file with your api key

| WARNING: be careful not to commit the api key if you choose this option |
|-------------------------------------------------------------------------|

2) Create a new file <i>application-local.properties</i> (which is ignored by git) and set the key there and run
   the application using the "local" spring profile as such:

* coinapi_api_key=EXAMPLE1-KEY1-YYYY-ZZZZ-123456789ABC
* -Dspring.profiles.active=local

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.2-SNAPSHOT/reference/htmlsingle/#using.devtools)

