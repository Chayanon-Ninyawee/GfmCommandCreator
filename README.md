# How to install
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
	<version>0.0.1</version>
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
# Usage
To register all GfmHeadCommand you had created
```java
GfmCommandCreator.register(yourJavaPlugin, listOfGfmHeadCommand);
```
You also need to add the command in the plugin.yml
```yml
commands:
  cmd1:
  cmd2:
  cmd3:
  .
  .
  .
```

To create GfmHeadCommand
```java
(GfmHeadCommand) new GfmHeadCommand.builder()
	// Put any configuration specifically for GfmHeadCommand first
	.setName("yourCommand") // Can't have the same name as other headCommand
	.setGfmCommandHandler((sender, args) -> false) // You can use lambba expression here
	// Other Configuration
	.build()
```

To create GfmSubCommand
```java
(GfmSubCommand) new GfmSubCommand.builder()
	// Put any configuration specifically for GfmSubCommand first
	.setName("yourSubCommand") // Can't have the same name as other headCommand
	.setGfmCommandHandler((sender, args) -> false) // You can use lambba expression here
	// Other Configuration
	.build()
```
