# camunda-bpmn-training
Getting started with camunda bpmn. Step by step training.

##Setup project / links and more

###Create new maven project

Maven jersey-quickstart archtype
https://jersey.java.net/documentation/latest/getting-started.html#new-webapp
```
mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-webapp \
                -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false \
                -DgroupId=com.small -DartifactId=getting-started-with-camunda -Dpackage=com.small \
                -DarchetypeVersion=2.23.1
```

###Download links
- https://camunda.org/download/modeler/
- http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neonr

##Notes
- generate mvn artifact via archtype
- import existing maven project into workspace
- setup wildfly server configuration
- adapt web app path
-- modify web.xml
-- modify add jboss web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<jboss-web xmlns="http://www.jboss.com/xml/ns/javaee"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="
      http://www.jboss.com/xml/ns/javaee
      http://www.jboss.org/j2ee/schema/jboss-web_5_1.xsd">
   <context-root>/</context-root>
</jboss-web>
```
-- modify MyResource.java
- start server
- deploy project
- test with 
```
curl GET 'http://localhost:8080' -i
```
