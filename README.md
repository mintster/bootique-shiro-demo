Simple [Bootique](http://bootique.io) Shiro demo containing the following Bootique Modules:
 
- Bootique Jetty
- Bootique MVC, with Jersey and Mustache.
- Bootique JDBC
- Bootique Shiro Web

![Bootique Shiro Demo App Home Page](http://nixmash.com/x/blog/2017/shiro0808a.png)

## NixMash Posts

- [A Bootique Shiro Demo Application](http://nixmash.com/post/a-bootique-shiro-demo-application)
- [A Custom Shiro Realm Example with Bootique](http://nixmash.com/post/a-custom-shiro-realm-example-with-bootique)
- [Custom Role Filters with Bootique Shiro Web](http://nixmash.com/post/custom-role-filters-with-bootique-shiro-web)
- [Using Encrypted Passwords in Shiro](http://nixmash.com/post/using-encrypted-passwords-in-shiro)

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

