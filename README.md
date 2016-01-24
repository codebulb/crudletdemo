# crudletdemo
Demo application of the Crudlet REST framework

## Version info
This is the demo for **Crudlet 0.1** release.

## Usage instructions
### Client
Default Node.js setup. Load dependencies with npm / bower; start application with grunt.

###Server
In order to run the web application locally, you need to setup a JTA data source in your webapp server according to the persistence.xml configuration.

If you want to use a MySQL database on your Glassfish server (rather than a Derby in-memory one), these instructions may be helpful:
* Copy the MySQL driver JAR to your Glassfish installation as explained [here](http://stackoverflow.com/a/8350030/1399395).
* (If you happen to use the NetBeans IDE, you can easily locate the MySQL driver JAR it uses internally in order to copy this very file to your Glassfish installation.)
* Then, configure your Glassfish server as explained [here](https://computingat40s.wordpress.com/how-to-setup-a-jdbc-connection-in-glassfish/).