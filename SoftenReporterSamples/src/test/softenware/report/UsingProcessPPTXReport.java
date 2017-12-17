package test.softenware.report;

import java.io.File;
import java.io.ObjectOutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

public class UsingProcessPPTXReport {

	/**
	 * If your program is legacy system and has previous version poi library, you can use a separate other process like this.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Map<String, Object> data = SampleDataUtils.getSampleData();
		
		ProcessBuilder pb = new ProcessBuilder(
				"java",
				"-cp",
				".;lib/*",
				"com.softenware.report.SoftenReporterProcess",
				"pptx",
				"-template", "template/Template-Sample-A.pptx",
//				"-in", "data/InputData.kson",
//				"-data", "kson",
				"-out", "result/Report-Result-fromProcessBuilder.pptx"
			);
		
		pb.directory(new File("samples"));
		pb.redirectError(Redirect.INHERIT);
//		pb.redirectOutput(new File("D:\\temp\\test\\ReportResult-Sample-A.pptx"));
		
		ObjectOutputStream procInput = null;
		try {
			Process proc = pb.start();
			procInput = new ObjectOutputStream(proc.getOutputStream());
			
			procInput.writeObject(data);
			procInput.flush();
			procInput.close();
			
			proc.waitFor();
		} finally {
			if (procInput != null)
				procInput.close();
		}
		
		System.out.println("Report created.");
	}
}
