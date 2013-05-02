package org.burgers.elasticsearch.client.namespace;

import org.burgers.elasticsearch.client.parser.ClientBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class TransportClientHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("client", new ClientBeanDefinitionParser());
    }
}
