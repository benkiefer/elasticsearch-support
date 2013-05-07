package org.burgers.elasticsearch.client.parser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PortParserTest {
    @Test
    public void parse(){
        List<Integer> results = new PortParser().parse("123,456");
        assertTrue(results.contains(123));
        assertTrue(results.contains(456));
    }
    
    @Test
    public void parse_with_spaces(){
        List<Integer> results = new PortParser().parse("123, 456");
        assertTrue(results.contains(123));
        assertTrue(results.contains(456));
    }

}
