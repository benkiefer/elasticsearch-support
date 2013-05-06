package org.burgers.elasticsearch.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import java.io.File;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Mojo(name = "run", requiresProject = false)
public class ElasticSearchMojo extends AbstractMojo {

    /*
        Location of Elasticsearch YML settings file
     */
    @Parameter(property = "elasticsearch.yml.location")
    private File settingsFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Starting Elasticsearch");

        Settings settings = loadSettingsIfNeeded();

        NodeBuilder nodeBuilder = nodeBuilder();

        if (settings != null) {
            nodeBuilder.settings(settings);
        }

        Node node = nodeBuilder.node();

        node.client();
        node.start();
    }

    private Settings loadSettingsIfNeeded() {
        Settings settings = null;
        if (settingsFile != null && settingsFile.exists() && settingsFile.isFile()) {
            getLog().info("Loading settings file: " + settingsFile.getAbsolutePath());
            settings = ImmutableSettings.settingsBuilder().loadFromClasspath(settingsFile.getAbsolutePath()).build();
        }
        return settings;
    }

}
