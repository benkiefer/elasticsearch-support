#Elasticsearch Support

A Maven plugin for running elasticsearch and some namespace goodies for instantiating a spring client.

[![Build Status](https://secure.travis-ci.org/benkiefer/elasticsearch-support.png?branch=master)](http://travis-ci.org/benkiefer/elasticsearch-support)

#Client

     <elasticsearch:client id="client" settings-file="elasticsearch.yml">
          <elasticsearch:node host="localhost" ports="9255,9299"/>
          <elasticsearch:node host="otherhost" ports="9300"/>
     </elasticsearch:client/>

All beans are name-spaced under the client to avoid name collisions.

#Plugin

You can run the plugin with the following configuration... 

    <plugin>
        <groupId>org.burgers.elasticsearch</groupId>
        <artifactId>elasticsearch-maven-plugin</artifactId>
        <version>${project.version}</version>
        <configuration>
            <settingsFile>${basedir}/../path/to/elasticsearch.yml</settingsFile>
        </configuration>
        <executions>
            <execution>
                <id>run-elasticsearch</id>
                <phase>test-compile</phase>
                <goals>
                    <goal>run</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

Or start and stop it as part of your build.

    <plugin>
        <groupId>org.burgers.elasticsearch</groupId>
        <artifactId>elasticsearch-maven-plugin</artifactId>
        <version>${project.version}</version>
        <configuration>
            <settingsFile>${basedir}/../path/to/elasticsearch.yml</settingsFile>
        </configuration>
        <executions>
            <execution>
                <id>start-elasticsearch</id>
                <phase>pre-integration-test</phase>
                <goals>
                    <goal>start</goal>
                </goals>
            </execution>
            <execution>
                <id>stop-elasticsearch</id>
                <phase>post-integration-test</phase>
                <goals>
                    <goal>stop</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
	
