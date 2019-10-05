# enterprise-aspects
[![Build Status](https://travis-ci.org/akovac35/enterprise-aspects.svg?branch=master)](https://travis-ci.org/akovac35/enterprise-aspects) ![Maven Central](https://img.shields.io/maven-central/v/com.github.akovac35/enterprise-aspects)

enterprise-aspects is an AOP Java library containing a collection of AspectJ aspects for typical small to medium enterprise application requirements. The focus is on 
* aspect performance,
* developer productivity,
* build dependency minimization.

## Usage
See Wiki: [enterprise-aspects Wiki](../../wiki)

## Building and installing the enterprise-aspects library from sources
The distribution files can be built by running the standard Maven goal `mvn clean install` in the project root:

1. git clone https://github.com/akovac35/enterprise-aspects.git
2. git checkout tags/tag_name
3. `mvn clean install`

## Including the enterprise-aspects library in your project
Install AspectJ Development Tools ([AJDT](https://www.eclipse.org/ajdt/downloads/index.php)) for Eclipse 4.10+ (development build).

AND/OR

Standard Maven goals apply, e.g. `mvn clean install`.

Use the following pom.xml example when building your project with Maven:

```XML
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		
		<!--
		Also Java 8. At the moment, AspectJ supports up to Java 11.
		Update the aspectj-maven-plugin to support newer Java versions.
		-->
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
		
		<!-- AspectJ version required by the aspectj-maven-plugin version -->
		<org.aspectj.version>1.8.13</org.aspectj.version>
		
		<!-- 
		aspectj-maven-plugin must run on Java 8.
		Update plugin source code to support higher Java versions:
		https://github.com/mojohaus/aspectj-maven-plugin
		-->
		<org.codehaus.mojo.aspectj-maven-plugin>1.11</org.codehaus.mojo.aspectj-maven-plugin>
	</properties>

	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj.version}</version>
			<scope>compile</scope>
		</dependency>
		
		<dependency>
			<groupId>com.github.akovac35</groupId>
			<artifactId>enterprise-aspects</artifactId>
			<version>tag_name</version>
		</dependency>
	</dependencies>
	
	<build>
		<finalName>${project.name}</finalName>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>aspectj-maven-plugin</artifactId>
				<version>${org.codehaus.mojo.aspectj-maven-plugin}</version>
				<configuration>
					<complianceLevel>${maven.compiler.target}</complianceLevel>
					<includes>
						<include>**/*.java</include>
						<include>**/*.aj</include>
					</includes>
					<source>${maven.compiler.target}</source>
					<target>${maven.compiler.target}</target>
					<showWeaveInfo>true</showWeaveInfo>
					<verbose>true</verbose>
					<Xlint>ignore</Xlint>
					<encoding>${project.build.sourceEncoding}</encoding>
					<forceAjcCompile>true</forceAjcCompile>
					<excludes>
						<exclude>**/package-info.java</exclude>
					</excludes>
				</configuration>
				<executions>
					<!-- https://www.eclipse.org/m2e/documentation/release-notes-17.html#new-syntax-for-specifying-lifecycle-mapping-metadata -->
					<execution>
					<?m2e execute onConfiguration,onIncremental?>
						<id>process-classes</id>
						<phase>process-classes</phase>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
					<!-- https://www.eclipse.org/m2e/documentation/release-notes-17.html#new-syntax-for-specifying-lifecycle-mapping-metadata -->
					<!-- Only include this section if there exist test classes, or the following 
						message may be reported: [ERROR] no sources specified -->
					<execution>
					<?m2e execute onConfiguration,onIncremental?>
						<id>process-test-classes</id>
						<phase>process-test-classes</phase>
						<goals>
							<goal>test-compile</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```
To use both Maven and AJDT in Eclipse, start a new Maven project, then right click on it -> Configure -> Convert to AspectJ Project. AJDT must be installed. This will allow your project to be built with both Maven and Eclipse tools (Project -> Build Automatically). This is especially useful for developing web projects on application servers capable of reloading updated class files.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache-2.0](LICENSE)
