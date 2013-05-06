package org.burgers.elasticsearch.client;

import org.burgers.elasticsearch.client.namespace.BeanDefinitionParserHelper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanDefinitionParsingTest extends BeanDefinitionParserHelper {
    @Test
    public void load_default_id() {
        String schemaDef = "<elasticsearch:client settings-file=\"elasticsearch.yml\">" +
                                "<elasticsearch:node host=\"localhost\" ports=\"2980,2981\"/>" +
                                "<elasticsearch:node host=\"localhost\" ports=\"2986\"/>" +
                            "</elasticsearch:client>";
        prepareContext(schemaDef);

        assertClient("elasticsearchClient");
    }

    private void assertClient(String beanId) {
        TransportClient client = context.getBean(beanId, TransportClient.class);
        assertNotNull(client);

        Settings settings = client.settings();
        assertNotNull(settings);
        assertEquals("foo", settings.get("cluster.name"));

        assertNotNull(context.getBean(beanId + "_settings", Settings.class));

        ImmutableList<TransportAddress> transportAddresses = client.transportAddresses();

        assertEquals(3, transportAddresses.size());
    }

    @Test
    public void load_specified_id() {
        String schemaDef = "<elasticsearch:client id=\"test\" settings-file=\"elasticsearch.yml\">" +
                                "<elasticsearch:node host=\"localhost\" ports=\"2980,2981\"/>" +
                                "<elasticsearch:node host=\"localhost\" ports=\"2986\"/>" +
                            "</elasticsearch:client>";
        prepareContext(schemaDef);

        assertClient("test");
    }

}
