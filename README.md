# enterprise-aspects
[![Build Status](https://travis-ci.org/akovac35/enterprise-aspects.svg?branch=master)](https://travis-ci.org/akovac35/enterprise-aspects)

enterprise-aspects is an AOP Java library containing a collection of AspectJ aspects for typical small to medium enterprise application requirements. The focus is on 
* aspect performance,
* developer productivity,
* build dependency minimization.

## Usage
See Wiki: [enterprise-aspects Wiki](../../wiki)

## Building the enterprise-aspects library
The distribution files can be built by running the standard Maven goal `mvn install` in the project root.

## Including the enterprise-aspects library in your project
Standard Maven goals apply, e.g. `mvn clean install`.

Use the following pom.xml example when building your project with Maven:

```XML
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
		<!-- AspectJ version required by the codehaus.mojo version -->
		<org.aspectj.version>1.8.13</org.aspectj.version>
		<org.codehaus.mojo>1.11</org.codehaus.mojo>
		<gson.version>2.8.5</gson.version>
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
			<groupId>ak.enterprise</groupId>
			<artifactId>aspects</artifactId>
			<version>x.y.z</version>
		</dependency>
	</dependencies>
	
	<build>
		<finalName>${project.name}</finalName>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>aspectj-maven-plugin</artifactId>
				<version>${org.codehaus.mojo}</version>
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

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[GPL-3.0](LICENSE)
