package org.burgers.elasticsearch;


import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticSearchExample {
    public static void main(String[] args) throws IOException {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch")
                .build();

        InetSocketTransportAddress address = new InetSocketTransportAddress("localhost", 9300);
        Client client = new TransportClient(settings).addTransportAddress(address);

        client.prepareIndex("twitter", "tweet", "1").setSource(
                jsonBuilder()
                        .startObject()
                        .field("user", "ben")
                        .field("message", "this is a test")
                        .field("postDate", new Date())
                        .endObject())
                .execute()
                .actionGet();

        GetResponse getResponse = client.prepareGet("twitter", "tweet", "1").setFields(new String[]{"message"}).execute().actionGet();

        System.out.println(getResponse.field("message").getValue().toString());
    }

}
