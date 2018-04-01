package com.example.esc_50005;


import junit.framework.JUnit4TestAdapter;
import junit.framework.TestResult;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;

// This section declares all of the test classes in the program.
@RunWith (Suite.class)
@Suite.SuiteClasses ({ ExampleInstrumentedTest.class})  // Add test classes here.

public class TestAll
{
    // Execution begins at main().  In this test class, we will execute
    // a text test runner that will tell you if any of your tests fail.
    public static void main (String[] args)
    {
    	System.out.println("test count " );
    	JUnit4TestAdapter suite = new JUnit4TestAdapter (TestAll.class);
    	TestResult result=junit.textui.TestRunner.run(suite);
    	
    	List<junit.framework.Test> list = suite.getTests();
    	System.out.println("test count " + list.size());
    	for (int i = 0; i < list.size(); i++) {
    		junit.textui.TestRunner.run(list.get(i));
    	}
    	
//    	Result result = JUnitCore.runClasses(FindMaxTest.class);
//    	while (result.failures().hasMoreElements()) {
//    		Failure param = result.failures().nextElement().getClass();
//    	    String param = result.errors().nextElement().toString();
//    	    System.out.println(param);
//    	}

    }
}
