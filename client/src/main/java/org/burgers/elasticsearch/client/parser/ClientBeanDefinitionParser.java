package org.burgers.elasticsearch.client.parser;

import org.burgers.elasticsearch.client.SettingsFactoryBean;
import org.burgers.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ClientBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        String settings_id = id + "_settings";

        String settingsFile = element.getAttribute("settings-file");

        BeanDefinitionBuilder settingsBuilder = BeanDefinitionBuilder.genericBeanDefinition(SettingsFactoryBean.class);
        settingsBuilder.addPropertyValue("settingsFile", settingsFile);

        parserContext.registerBeanComponent(new BeanComponentDefinition(settingsBuilder.getBeanDefinition(), settings_id));

        BeanDefinitionBuilder clientBuilder = BeanDefinitionBuilder.genericBeanDefinition(TransportClientFactoryBean.class);
        clientBuilder.addPropertyReference("settings", settings_id);

        parserContext.registerBeanComponent(new BeanComponentDefinition(clientBuilder.getBeanDefinition(), id));

        return clientBuilder.getBeanDefinition();
    }

}
