package base;

import static File.PropertyFileUtil.*;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class DynamicTestNG {

    @Test
    public static  void runTest() throws Exception {
        loadPropertyFile();
        XmlSuite suite = new XmlSuite();
            XmlTest test = new XmlTest(suite);
                List<XmlPackage> packages = new ArrayList<>();
                packages.add(new XmlPackage(getProperty("packageName")));
            test.setPackages(packages);
            test.setName(getProperty("testName"));
            List<XmlTest> tests = new ArrayList<>();
            tests.add(test);
        suite.setTests(tests);
        suite.setName(getProperty("suiteName"));
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();

    }
}
