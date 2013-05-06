package org.burgers.elasticsearch.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.elasticsearch.node.Node;

@Mojo(name = "stop", requiresProject = false)
public class StopElasticSearchMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Node node = StartElasticSearchMojo.node;

        if (node != null && !node.isClosed()) {
            getLog().info("Stopping Elasticsearch");
            node.stop().close();
        }
    }

}
