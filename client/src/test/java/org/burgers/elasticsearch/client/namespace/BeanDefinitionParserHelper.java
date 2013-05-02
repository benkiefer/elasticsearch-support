package org.burgers.elasticsearch.client.namespace;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;

abstract public class BeanDefinitionParserHelper {
    protected GenericXmlApplicationContext context;

    protected void prepareContext(String myValue) {
        String contextContent =
                String.format("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xmlns:elasticsearch=\"http://www.burgers.org/schema/elasticsearch\"\n" +
                        "xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                        "http://www.burgers.org/schema/elasticsearch http://www.burgers.org/schema/elasticsearch.xsd\">\n\n" +

                        "%s" +

                        "\n\n</beans>", myValue);

        context = new GenericXmlApplicationContext();
        context.load(new ByteArrayResource(contextContent.getBytes()));
    }

    protected void printBeanInfo() {
        System.out.println("Found beans (name : class)");
        for (String beanName : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(beanName);
            System.out.println("\t" + beanName + " : " + bean.getClass());
        }
    }

    protected void cleanContext() {
        context = null;
    }

}

