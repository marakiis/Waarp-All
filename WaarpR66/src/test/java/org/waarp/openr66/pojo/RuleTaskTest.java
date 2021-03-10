package org.waarp.openr66.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleTaskTest {
    @Test
    public void testGetXML() {
        Map<RuleTask, String> testCases = new HashMap<RuleTask, String>();
        // Test simple task
        testCases.put(new RuleTask("TYPE", "PATH", 0), 
            "<task><type>TYPE</type><path>PATH</path><delay>0</delay></task>");
        // Test task with special values
        testCases.put(new RuleTask("EXEC", "begin&<>end",1234), 
            "<task><type>EXEC</type><path>begin&amp;&lt;&gt;end</path><delay>1234</delay></task>");
        // Test task with ' substring
        testCases.put(new RuleTask("EXEC", "'this is one string'",1), 
            "<task><type>EXEC</type><path>'this is one string'</path><delay>1</delay></task>");
        // Test task with " substring
        testCases.put(new RuleTask("EXEC", "\"this is one string\"",-615), 
            "<task><type>EXEC</type><path>\"this is one string\"</path><delay>-615</delay></task>");
        Iterator<Entry<RuleTask, String>> iterator = testCases.entrySet().iterator();
	    while (iterator.hasNext()) {
            Map.Entry<RuleTask, String> testCase = iterator.next();
            String res = testCase.getKey().getXML();    
            assertEquals(testCase.getValue(), res);
        }
    }
}
