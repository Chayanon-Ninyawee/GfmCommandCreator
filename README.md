# GfmCommandCreator
To install GfmCommandCreator to your local maven repository.
```
git clone https://github.com/Chayanon-Ninyawee/GfmCommandCreator.git
cd gfmcommandcreator/
mvn clean install
```

To add GfmCommandCreator as a dependency
```xml
<dependency>
	<groupId>me.garfieldcmix</groupId>
	<artifactId>gfmcommandcreator</artifactId>
	<version>0.0.0</version>
</dependency>
```

To shade to your plugin
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-shade-plugin</artifactId>
	<version>3.4.0</version>
	<executions>
		<execution>
			<phase>package</phase>
			<goals>
				<goal>shade</goal>
            		</goals>
		</execution>
	</executions>
</plugin>   
```
