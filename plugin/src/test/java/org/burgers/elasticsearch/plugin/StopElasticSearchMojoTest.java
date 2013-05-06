package org.burgers.elasticsearch.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.elasticsearch.node.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StartElasticSearchMojo.class})
public class StopElasticSearchMojoTest {
    @InjectMocks
    private StopElasticSearchMojo mojo;
    @Mock
    private Node node;

    @Test
    public void execute() throws MojoFailureException, MojoExecutionException {
        mockStatic(StartElasticSearchMojo.class);

        PowerMockito.when(StartElasticSearchMojo.getNode()).thenReturn(node);

        PowerMockito.when(node.isClosed()).thenReturn(false);
        PowerMockito.when(node.stop()).thenReturn(node);

        mojo.execute();

        Mockito.verify(node).isClosed();
        Mockito.verify(node).stop();
        Mockito.verify(node).close();

        verifyNoMoreInteractions(node);
        PowerMockito.verifyStatic();
    }

    @Test
    public void execute_null_node() throws MojoFailureException, MojoExecutionException {
        mockStatic(StartElasticSearchMojo.class);

        PowerMockito.when(StartElasticSearchMojo.getNode()).thenReturn(null);

        mojo.execute();

        Mockito.verifyNoMoreInteractions(node);

        PowerMockito.verifyStatic();
    }

    @Test
    public void execute_closed_node() throws MojoFailureException, MojoExecutionException {
        mockStatic(StartElasticSearchMojo.class);

        PowerMockito.when(StartElasticSearchMojo.getNode()).thenReturn(node);

        PowerMockito.when(node.isClosed()).thenReturn(true);

        mojo.execute();

        Mockito.verify(node).isClosed();
        Mockito.verifyNoMoreInteractions(node);

        PowerMockito.verifyStatic();
    }

}
