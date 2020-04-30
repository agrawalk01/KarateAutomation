package KarateTest;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

//@RunWith(Karate.class)
@KarateOptions(
        features = "src/test/java/Features",
        tags = {"@CountryTest"})

public class TestRunner {

    @Test
    public void testParallel() {
        String karateOutputPath = "target/cucumber-html-reports";
        Results results = Runner.parallel(getClass(), 1, karateOutputPath);
        generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    private static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList (jsonFiles.size());
        for (File file : jsonFiles) {
            jsonPaths.add(file.getAbsolutePath());
        }
        Configuration config = new Configuration(new File("target"), "KaratePOC");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
