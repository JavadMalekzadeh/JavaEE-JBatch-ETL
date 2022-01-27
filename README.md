# About 
This is a simple ETL Batch processing to extract data, here are messages, stored in a table. transform them into a new object, then insert them in another table. Used technologies :
- JavaEE 8
- JBatch 
- Quartz as scheduler
- MySql database
- WildFly application server   

# WlidFly Configuration:
- download **JBoss AS** or simply known as Wildfly from the following Url:
https://www.wildfly.org/downloads/

- Install the downloaded application server in a directory. Move to the folder **\bin** which is under WildFly installation and start WildFly by executing `standalone.bat` on windows. 

- To add a user go to **\bin** folder and execute `add-user.bat`

- You can check that WildFly application server is running by reaching the home page at `localhost:8080`

- After login in Wildfly console, click on **Configuration** tab. Go to `subsystems \ Datasource & drivers`. To be able to connect to **MYSQL** database you need to configure driver. For adding a driver you need to add mysql module to Wildfly. for that you should first download `mysql-connector-java.jar`. 

- For adding module, go to bin folder and execute `jboss-cli.bat`
use this command to add Mysql module:
```sh
module add --name=com.mysql  --dependencies=javax.api,javax.transaction.api --resources=E:\...\mysql-connector-java-8.0.25.jar
```

- Now you can set the mysql driver and then create datasource for mysql from **Configuration** tab.

- To make wildfly able to use jdbc connection and connect to mysql when using batch go to Batch in **Configuration** tab and click on view. Default for batch is In-Memory. Go to jdbc, add mysql connector. Then in Configuration edit the default jobrepositoy. now everythis is set.

