Simple [Bootique](http://bootique.io) Shiro demo containing the following Bootique Modules:
 
- Bootique Jetty
- Bootique MVC, with Jersey and Mustache.
- Bootique JDBC
- Bootique Shiro Web

## Installation

The Bootique Shiro Demo uses MySQL which requires configuration. To configure 

1) Create a MySQL database and run `setup.mysql` located in **install/sql**
2) Update the Bootique JDBC Connection properties in `bootique.yml`

The tests use H2 and require no configuration.

## To Run

Simply build the app in your IDE or use Maven

```
mvn clean package
```
Enter the following to launch the app in Maven.

```bash
/project/root/$ java -jar target/shiro-demo.jar 
```

After launching the app in your IDE or with Maven, go to

```bash
http://localhost:9000/
```

## User Accounts

Two User Accounts are available for login and are listed on the web pages.

1) **bob** - Password: *password* Roles: *admin* and *user*
2) **ken** - Password: *halo* Roles: *user*

## Handling 401 Unauthorized Response

**org.apache.shiro - shiro-web** has been modified to handle the 401 Unauthorized Response. The version as you will see in your `pom.xml` is *1.4.1-SNAPSHOT* as opposed to *1.4.0.* The modified **shiro-web-1.4.1-SNAPSHOT.jar** is located in `/install/lib.` Copy it to your

`.m2/repository/org/apache/shiro/shiro-web/1.4.1-SNAPSHOT`

directory and update your Maven Dependencies. With the updated *shiro-web* library you will be redirected to an `/unauthorized` page rather than encounter a 401 Response. To test, login with the **ken** user account and try to view the *Administrators Only* page.

