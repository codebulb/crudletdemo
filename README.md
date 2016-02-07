# crudletdemo
CRUD REST-to-SQL demo application.

For use with
* a Java EE server with Crudlet REST framework (included, [find out more about the framework here](https://github.com/codebulb/crudlet))
* a Node.js + Hapi + Bookshelf server ([get the demo server here](https://github.com/codebulb/HapiBookshelfServer))
* a Node.js server with hapi-bookshelf-crud ([find out more about the framework here](https://github.com/codebulb/hapi-bookshelf-crud); [get the demo server here](https://github.com/codebulb/hapi-bookshelf-crud-demo))

including an AngularJS / Restangular web client which is compatible with any of these servers.

## Usage instructions
### Client
Default Node.js setup. Load dependencies with npm / bower; start application with grunt.

[Setup Restangular's REST endpoint](https://github.com/codebulb/crudletdemo/blob/master/client/app/scripts/app.js) before startup:

Using the default Java EE server setup:
```
RestangularProvider.setBaseUrl('http://localhost:8080/CrudletDemo.server/');
```

Using the default Node.js server setup:
```
RestangularProvider.setBaseUrl('http://localhost:3000');
```

###Server
#### With Crudlet
In order to run the web application locally, you need to setup a JTA data source in your webapp server according to the persistence.xml configuration.

If you want to use a MySQL database on your Glassfish server (rather than a Derby in-memory one), these instructions may be helpful:
* Copy the MySQL driver JAR to your Glassfish installation as explained [here](http://stackoverflow.com/a/8350030/1399395).
* (If you happen to use the NetBeans IDE, you can easily locate the MySQL driver JAR it uses internally in order to copy this very file to your Glassfish installation.)
* Then, configure your Glassfish server as explained [here](https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/).

[Find out more about the framework here.](https://github.com/codebulb/crudlet)

#### For Node.js + Hapi + Bookshelf server
Get the demo server implementation [here](https://github.com/codebulb/HapiBookshelfServer). It is fully compatible with the client from this project.

#### For Node.js + hapi-bookshelf-crud
Get the demo server implementation [here](https://github.com/codebulb/hapi-bookshelf-crud-demo). It is fully compatible with the client from this project.

[Find out more about the framework here.](https://github.com/codebulb/hapi-bookshelf-crud)
