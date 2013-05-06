package org.burgers.elasticsearch.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NodeBuilder.class, ImmutableSettings.class})
public class RunElasticSearchMojoTest {
    @InjectMocks
    private RunElasticSearchMojo mojo;

    @Mock
    private Node node;
    @Mock
    private NodeBuilder nodeBuilder;
    @Mock
    private Settings settings;
    @Mock
    private ImmutableSettings.Builder builder;

    @Test
    public void execute() throws MojoFailureException, MojoExecutionException {
        PowerMockito.mockStatic(NodeBuilder.class);

        PowerMockito.when(NodeBuilder.nodeBuilder()).thenReturn(nodeBuilder);

        PowerMockito.when(nodeBuilder.node()).thenReturn(node);

        mojo.execute();

        Mockito.verify(node).start();
    }

    @Test
    public void execute_with_settings() throws MojoFailureException, MojoExecutionException, IOException {
        File settingsFile = File.createTempFile("test", ".txt");
        settingsFile.deleteOnExit();

        PowerMockito.mockStatic(NodeBuilder.class, ImmutableSettings.class);

        PowerMockito.when(NodeBuilder.nodeBuilder()).thenReturn(nodeBuilder);
        PowerMockito.when(nodeBuilder.node()).thenReturn(node);
        PowerMockito.when(ImmutableSettings.settingsBuilder()).thenReturn(builder);
        PowerMockito.when(builder.loadFromClasspath(settingsFile.getAbsolutePath())).thenReturn(builder);
        PowerMockito.when(builder.build()).thenReturn(settings);

        mojo.setSettingsFile(settingsFile);

        mojo.execute();

        Mockito.verify(nodeBuilder).settings(settings);
        Mockito.verify(node).start();
    }

}
