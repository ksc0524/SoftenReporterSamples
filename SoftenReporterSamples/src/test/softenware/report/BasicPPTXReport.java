package test.softenware.report;

import java.io.File;
import java.util.Map;

import com.softenware.report.SoftenPPTXReporter;

public class BasicPPTXReport {

	public static void main(String[] args) throws Exception {
		File templateFile = new File("samples" + File.separator + "template" + File.separator + "Template-Sample-A.pptx");
		Map<String, Object> data = SampleDataUtils.getSampleData();
		
		SoftenPPTXReporter reporter = new SoftenPPTXReporter(templateFile, data);
		reporter.report(new File("samples" + File.separator + "result" + File.separator + "Report-Result-fromBasicSample.pptx"));
		
		System.out.println("Report file created.");
	}
}
