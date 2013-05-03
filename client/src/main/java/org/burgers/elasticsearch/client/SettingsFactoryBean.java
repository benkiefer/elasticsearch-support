package org.burgers.elasticsearch.client;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.FactoryBean;

public class SettingsFactoryBean implements FactoryBean<Settings> {
    private String settingsFile;

    @Override
    public Settings getObject() throws Exception {
        return ImmutableSettings.settingsBuilder().loadFromSource(settingsFile).build();
    }

    @Override
    public Class<?> getObjectType() {
        return Settings.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setSettingsFile(String settingsFile) {
        this.settingsFile = settingsFile;
    }

}
