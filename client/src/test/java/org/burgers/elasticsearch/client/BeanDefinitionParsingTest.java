package org.burgers.elasticsearch.client;

import org.burgers.elasticsearch.client.namespace.BeanDefinitionParserHelper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BeanDefinitionParsingTest extends BeanDefinitionParserHelper {
    @Test
    public void load_default_id() {
        prepareContext("<elasticsearch:client/>");

        TransportClient client = (TransportClient) context.getBean("elasticsearchClient");
        assertNotNull(client);

        Settings settings = client.settings();
        assertNotNull(settings);
    }

    @Test
    public void load_specified_id() {
        prepareContext("<elasticsearch:client id=\"test\"/>");

        TransportClient client = (TransportClient) context.getBean("test");
        assertNotNull(client);

        Settings settings = client.settings();
        assertNotNull(settings);
    }

}
