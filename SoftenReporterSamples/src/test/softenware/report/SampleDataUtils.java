package test.softenware.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softenware.report.KsonUtils;

public class SampleDataUtils {
	private SampleDataUtils() {
	}
	
	public static Map<String, Object> getSampleData() {
		HashMap<String, Object> map = new HashMap<>();
		
		List<Object> list = new ArrayList<>();
		
		for (int i = 0 ; i < 100 ; i++)
			list.add(makeTargetPart("1234-5678-" + i, "TEST CAD NAME - " + i, "Softenware", ""));
		
		map.put("TargetParts", list);
		map.put("BasicInfo", makeBasicInfo("Softenware test", "R&D", "ksc0524", "" + list.size()));
		
		return map;
	}
	
	private static Map<String, Object> makeTargetPart(String partNumber, String cadName, String companyName, String note) {
		HashMap<String, Object> vo = new HashMap<>();
		
		vo.put("partNumber", partNumber);
		vo.put("cadName", cadName);
		vo.put("companyName", companyName);
		vo.put("note", note);

		Date now = Calendar.getInstance().getTime();
		vo.put("startDate", now);
		vo.put("endDate", now);
		vo.put("compDate", now);
		vo.put("planDate", now);
		
		return vo;
	}
	
	private static Map<String, Object> makeBasicInfo(String partType, String deptName, String reporterName, String targetPartCount) {
		HashMap<String, Object> vo = new HashMap<>();
		
		vo.put("partType", partType);
		vo.put("deptName", deptName);
		vo.put("reporterName", reporterName);
		vo.put("targetPartCount", targetPartCount);
		
		Date now = Calendar.getInstance().getTime();
		vo.put("targetDate", now);
		vo.put("allDesignDate", now);
		vo.put("protoDate", now);
		vo.put("esirDate", now);
		vo.put("mcarDate", now);
		vo.put("p1Date", now);
		vo.put("p2Date", now);
		vo.put("prodDate", now);
		
		return vo;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> data = getSampleData();
		
		ObjectOutputStream oOut = null;
		BufferedWriter bOut = null;
		
		try {
			oOut = new ObjectOutputStream(new FileOutputStream("samples" + File.separator + "InputData.object"));
			oOut.writeObject(data);
			
			bOut = new BufferedWriter(new FileWriter("samples" + File.separator + "InputData.kson"));
			KsonUtils.toKson(data, bOut);
		} finally {
			if (oOut != null)
				oOut.close();
			if (bOut != null)
				bOut.close();
		}
		
		System.out.println("Input data created.");
	}
}
