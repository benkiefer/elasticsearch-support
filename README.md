#Elasticsearch Support

Looking into the innards of elasticsearch, and how to integrate it with it in a Spring world.

#Todo

 - Client Schema (note: fluent apis are a bad way to get people to use your code in Spring XML)
 - Plugin?

#Client

     <elasticsearch:client id="client" settings-file="elasticsearch.yml">
          <elasticsearch:node host="localhost" port="9299"/>
          <elasticsearch:node host="localhost" port="9300"/>
     </elasticsearch:client/>

All beans are name-spaced under the client to avoid name collisions.