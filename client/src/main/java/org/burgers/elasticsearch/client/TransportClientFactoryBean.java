package org.burgers.elasticsearch.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.List;

public class TransportClientFactoryBean implements FactoryBean<TransportClient> {
    private Settings settings;
    private List<InetSocketTransportAddress> inetAddresses = new ArrayList<InetSocketTransportAddress>();

    @Override
    public TransportClient getObject() throws Exception {
        TransportClient client = new TransportClient(settings);

        for (InetSocketTransportAddress inetAddress : inetAddresses) {
            client.addTransportAddress(inetAddress);
        }

        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setInetAddresses(List<InetSocketTransportAddress> inetAddresses) {
        this.inetAddresses = inetAddresses;
    }
}
