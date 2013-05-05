package org.burgers.elasticsearch.client.parser;

import org.burgers.elasticsearch.client.SettingsFactoryBean;
import org.burgers.elasticsearch.client.TransportClientFactoryBean;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

public class ClientBeanDefinitionParser implements BeanDefinitionParser {
    private PortParser portParser = new PortParser();

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");

        String settings_id = id + "_settings";

        String settingsFile = element.getAttribute("settings-file");

        List<Element> nodes = DomUtils.getChildElementsByTagName(element, "node");

        ManagedList<RuntimeBeanReference> addressListBean = new ManagedList<RuntimeBeanReference>(nodes.size());

        int index = 0;

        for (Element node : nodes) {
            List<Integer> ports = portParser.parse(node.getAttribute("ports"));

            for (Integer port : ports) {
                String inetAddressBeanId = id + "_node_" + index;
                BeanDefinitionBuilder inetAddressBuilder = BeanDefinitionBuilder.genericBeanDefinition(InetSocketTransportAddress.class);
                inetAddressBuilder.addConstructorArgValue(node.getAttribute("host"));
                inetAddressBuilder.addConstructorArgValue(port);

                parserContext.registerBeanComponent(new BeanComponentDefinition(inetAddressBuilder.getBeanDefinition(), inetAddressBeanId));
                addressListBean.add(new RuntimeBeanReference(inetAddressBeanId));
                index++;
            }

        }

        BeanDefinitionBuilder settingsBuilder = BeanDefinitionBuilder.genericBeanDefinition(SettingsFactoryBean.class);
        settingsBuilder.addPropertyValue("settingsFile", settingsFile);

        parserContext.registerBeanComponent(new BeanComponentDefinition(settingsBuilder.getBeanDefinition(), settings_id));

        BeanDefinitionBuilder clientBuilder = BeanDefinitionBuilder.genericBeanDefinition(TransportClientFactoryBean.class);
        clientBuilder.addPropertyReference("settings", settings_id);
        clientBuilder.addPropertyValue("inetAddresses", addressListBean);

        parserContext.registerBeanComponent(new BeanComponentDefinition(clientBuilder.getBeanDefinition(), id));

        return clientBuilder.getBeanDefinition();
    }

}
