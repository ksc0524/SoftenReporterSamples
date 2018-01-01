package test.softenware.report;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import com.softenware.report.client.SoftenReporterProcessExecutor;

public class UsingProcessPPTXReport {

	/**
	 * If your program is legacy system and has previous version poi library, you can use a separate other process like this.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		usingProcessExecutor();
		usingProcessBuilder();
	}
	
	private static void usingProcessExecutor() throws IOException, InterruptedException {
		SoftenReporterProcessExecutor executor = new SoftenReporterProcessExecutor("samples", "java.exe", null);
		executor.report("template/Template-Sample-A.pptx", SampleDataUtils.getSampleData(), "result/Report-Result-fromProcessExecutor.pptx");
		
		System.out.println("Report-1 created.");
	}
	
	private static void usingProcessBuilder() throws IOException, InterruptedException {
		Map<String, Object> data = SampleDataUtils.getSampleData();
		
		ProcessBuilder pb = new ProcessBuilder(
				"java",
				"-cp",
				".;lib/*",
				"com.softenware.report.SoftenReporterProcess",
				"-template", "template/Template-Sample-A.pptx",
//				"-in", "data/InputData.kson",
//				"-data", "kson",
				"-out", "result/Report-Result-fromProcessBuilder.pptx"
			);
		
		pb.directory(new File("samples"));
		pb.redirectError(Redirect.INHERIT);
		
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
		
		System.out.println("Report-2 created.");
	}
}
