migrate: java -jar target/dependency/liquibase.jar --changeLogFile=src/non-packaged-resources/dbchanges/db-master.xml --url=$JDBC_DATABASE_URL --classpath=target/dependency/postgres.jar update

web:    java  $JAVA_OPTS -jar target/dependency/jetty-runner.jar --port $PORT target/*.war
