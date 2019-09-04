# enterprise-aspects
[![Build Status](https://travis-ci.org/akovac35/enterprise-aspects.svg?branch=master)](https://travis-ci.org/akovac35/enterprise-aspects)

enterprise-aspects is an AOP Java library containing a collection of AspectJ aspects for typical small to medium enterprise application requirements. The focus is on 
* aspect performance,
* developer productivity,
* build dependency minimization.


## TracingAspect usage

The `TracingAspect` is designed for aspect extension based tracing concern implementation. To enable entry/exit and other type of tracing in custom code, extend this aspect as follows:

```java
public aspect TracingAspectImpl extends TracingAspect  pertypewithin(ak.enterprise.aspects.tracing.test..*) {

	public pointcut loggableCode(): 
		within(ak.enterprise.aspects.tracing.test..*) &&
		!within(ak.enterprise.aspects.tracing.test.TracingTest+);
	
	/* Optionally define custom logger
	@Override
	protected void setTypeLogger(String name) {
		l = new DefaultLogger(name);
	}
	*/
	
	/* Optionally define custom stringifier
	@Override
	protected String stringify(Object o) {
		return DefaultStringifier.stringify(o);
	}
	*/
}
```
For in-method tracing, use `TracingHelper`, for example:
```java
TracingHelper.fine("Doing something.");
```
Calls to `TracingHelper` are replaced with named logger invocation. Trace example:

```
[9/3/19 17:43:07:391 CEST] 00000484 samples.web.RestServices              > <clinit> ENTRY
[9/3/19 17:43:07:392 CEST] 00000484 samples.web.RestServices              < <clinit> RETURN
[9/3/19 17:43:07:393 CEST] 00000484 samples.web.RestServices              > <init> ENTRY
[9/3/19 17:43:07:394 CEST] 00000484 samples.web.RestServices              < <init> RETURN
[9/3/19 17:43:07:412 CEST] 00000484 samples.web.rest.BusinessLogicService > <clinit> ENTRY
[9/3/19 17:43:07:412 CEST] 00000484 samples.web.rest.BusinessLogicService < <clinit> RETURN
[9/3/19 17:43:07:412 CEST] 00000484 samples.web.rest.BusinessLogicService > <init> ENTRY
[9/3/19 17:43:07:412 CEST] 00000484 samples.web.rest.BusinessLogicService < <init> RETURN
[9/3/19 17:43:07:416 CEST] 00000484 samples.web.rest.BusinessLogicService > getBusinessObject ENTRY
[9/4/19 17:43:07:417 CEST] 00000484 samples.web.rest.BusinessLogicService 1 Doing something.
[9/3/19 17:43:07:517 CEST] 00000484 samples.web.rest.BusinessLogicService < getBusinessObject RETURN {"firstName":"Test","lastName":"Object"}
```

Main aspect design features are:

 * one statically instantiated named logger instance per type, used without list or array search,
 * entry/exit trace with included input parameters and return value,
 * minimal performance impact when tracing is not enabled,
 * minimal development effort to support tracing in custom code,
* customizable.

The aspect already contains the necessary filters to include constructors and static initializers and exclude internal and common code (`toString`, `getClass`, ...). Define the `loggableCode` pointcut value to include packages, classes or methods to be included or excluded for entry/exit tracing. 

The aspect defines a `DefaultLogger`, which uses `java.util.logging.Loger`, and a `DefaultStringifier`, which uses `com.google.gson.Gson` to stringify Java objects to JSON string. `Gson` was selected as the default stringifier because it does not conflict with existing Java EE jsonp and jsonb server implementations while being well tested, stable and performant to cover the requirements. Concrete aspect implementation may override the defaults.

## Maven build

Use the following pom.xml example when building your project:

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