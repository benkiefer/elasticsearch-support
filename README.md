#Elasticsearch Support

A Maven plugin for running elasticsearch and some namespace goodies for instantiating a spring client.

#Todo

 - Plugin executions for starting and stopping elasticsearch, similar to cargo's start and stop.

#Client

     <elasticsearch:client id="client" settings-file="elasticsearch.yml">
          <elasticsearch:node host="localhost" ports="9255,9299"/>
          <elasticsearch:node host="otherhost" ports="9300"/>
     </elasticsearch:client/>

All beans are name-spaced under the client to avoid name collisions.

#Plugin

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

