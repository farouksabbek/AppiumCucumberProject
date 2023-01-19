package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinition",
//        tags = "@Google",
        plugin = {"pretty", "html:target/reports/report.html",
                "json:target/reports/report.json"},
        dryRun = false,
        monochrome = true
)

public class TestRunner {

    @SuppressWarnings("unchecked")
    @AfterClass
    public static void tearDown() {
        File reportOutputDirectory = new File("target/reportCucumber");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/reports/report.json");
        //To change with correct information
        String buildNumber = "1";
        String projectName = "CucumberProject";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc for details
        configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
        // do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.setBuildNumber(buildNumber);
        // To change with correct information (additional metadata presented on main page)
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        // and here validate 'result' to decide what to do if report has failed
        Reportable result = reportBuilder.generateReports();
    }
}