package test.softenware.report;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.softenware.report.client.SoftenReporterClient;

public class UsingSoftenReporterServer {

	public static void main(String[] args) throws Exception {
		// Initialize SoftenReporterClient: set server host and port
		SoftenReporterClient client = new SoftenReporterClient("localhost", 8080);
		
		// 1. How to request server to make pptx file directly
		boolean result = client.report("D:\\temp\\test\\ReportTemplate-Sample-A.pptx", SampleDataUtils.getSampleData(), "D:\\temp\\test\\ReportResult-From-Server.pptx");
		
		if (result)
			System.out.println("Succeed to report");
		else
			System.out.println("Fail to report");
		
		
		// 2. How to request server to return pptx stream (You can use this method for web response, etc.
		FileOutputStream out = null;
		InputStream in = null;
		
		try {
			in = client.report("D:\\temp\\test\\ReportTemplate-Sample-A.pptx", SampleDataUtils.getSampleData());
			
			// For test, write stream to a file
			out = new FileOutputStream("D:\\temp\\test\\ReportResult-From-ServerStream.pptx");
			
			byte[] buff = new byte[1024];
			int read;
			while ((read = in.read(buff)) > 0) {
				out.write(buff, 0, read);
			}
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
	}
}
