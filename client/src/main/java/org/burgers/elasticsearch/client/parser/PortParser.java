package org.burgers.elasticsearch.client.parser;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PortParser {
    public List<Integer> parse(String portString){
        List<String> listOfStringPorts = asList(portString.split(","));
        List<Integer> results = new ArrayList<Integer>();

        for (String stringPort : listOfStringPorts) {
            results.add(Integer.parseInt(stringPort.trim()));
        }

        return results;
    }
}
