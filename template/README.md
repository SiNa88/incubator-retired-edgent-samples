An Edgent Application template project.

Clone this template project to start your Edgent application project.

The project's pom supports
- building for java8, java7 or android execution environments
- building a standard jar and uber jar

The pom has potential Edgent dependenacies present and commented out.
Select Edgent Providers, Analytics, Utils, and Connectors used by your application.

Read `../README.md` for general information about Edgent Application development.

#Building the project

```sh
mvnw clean install  # add -Pplatform-java7 or -Pplatform-java8 as needed
```

#Running the application

You can copy app-run.sh and the generated target/*-uber.jar to the edge device

```sh
./app-run.sh
```

#Using package-app.sh with the project

Adjust the main class name and application jar path below for your application.

```sh
PLATFORM=  # add "--platform java7"  or  "--platform android" as appropriate
../package-app.sh $PLATFORM --mainClass org.apache.edgent.samples.template.HelloEdgentTemplate --appjar target/edgent-samples-template-1.2.0-SNAPSHOT.jar
```