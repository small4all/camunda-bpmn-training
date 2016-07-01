# camunda-bpmn-training
Getting started with camunda bpmn. Step by step training.

###Download links
- https://camunda.org/download/modeler/
- http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neonr

##Notes
generate mvn artifact via archtype
```
mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-webapp \
                -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false \
                -DgroupId=com.small -DartifactId=getting-started-with-camunda -Dpackage=com.small \
                -DarchetypeVersion=2.23.1
```
import existing maven project into workspace

setup wildfly server configuration

adapt web app path in web.xml, resource class and jboss web.xml

modify web.xml
```
 <url-pattern>/*</url-pattern>
 ```

modify MyResource.java
```
@Path("")
```

rename class to ApiResource

add jboss-web.xml
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

start server

deploy project

test with 
```
curl GET 'http://localhost:8080' -i
```

add camunda dependencies to pom.xml
```
<!-- Camunda engine dependency -->
<dependency>
	<groupId>org.camunda.bpm</groupId>
	<artifactId>camunda-engine</artifactId>
	<version>7.5.0</version>
</dependency>

<!-- Camunda cdi beans -->
<dependency>
	<groupId>org.camunda.bpm</groupId>
	<artifactId>camunda-engine-cdi</artifactId>
	<version>7.5.0</version>
</dependency>

<!-- provides a default EjbProcessApplication -->
<dependency>
	<groupId>org.camunda.bpm.javaee</groupId>
	<artifactId>camunda-ejb-client</artifactId>
	<version>7.5.0</version>
</dependency>


<!-- Java EE 7 Specification -->
<dependency>
	<groupId>org.jboss.spec</groupId>
	<artifactId>jboss-javaee-7.0</artifactId>
  	<version>1.0.3.Final</version>
  	<type>pom</type>
  	<scope>provided</scope>
  	<exclusions>
		<exclusion>
  			<artifactId>xalan</artifactId>
  			<groupId>xalan</groupId>
		</exclusion>
  	</exclusions>
</dependency>   
```

add empty WEB-INF/beans.xml
