package com.youbao.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.youbao.common.FileUtils;
@Service
public class WkhtmlToPdfServiceImpl implements WkhtmlToPdfService {
	
	private String date2String(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate=sdf.format(date);
		return strDate;
	}

	@Override
	public String createPdf(String name, String indentify) throws Exception {
		File templateFile = new File("/root/reporthtml/report.html");
		File html2Pdf = new File("/root/reporthtml/" + name + "-" + indentify + ".html");
		FileUtils.stream2file(FileUtils.file2Stream(templateFile), html2Pdf,  line->{
			if(line.contains("%name%"))
				line = line.replace("%name%", name);
			if(line.contains("%indentify%"))
				line = line.replace("%indentify%", indentify);
			if(line.contains("%createdate%")) {
				Date nowDate = new Date();
				String strDate = date2String(nowDate);
				line = line.replace("%createdate%", strDate);
			}
			return line;
		});
		String pdfName = html2Pdf.getName().substring(0,  html2Pdf.getName().indexOf(".")) + ".pdf";
		System.out.println("html2Pdf.getAbsolutePath():" + html2Pdf.getAbsolutePath());
		System.out.println("pdfName:" + pdfName);
		String pdfFileName = "/root/pdf/" + pdfName;
		String htmlUrl = "http://xbk.tdj.cn/reporthtml/" + html2Pdf.getName();
		System.out.println(htmlUrl);
		ProcessBuilder pb = new ProcessBuilder("/root/reportServer/bin/wkhtmltopdf.sh", htmlUrl ,pdfFileName);
		 Process p = pb.start();
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 String line = null;
		 while ((line = reader.readLine()) != null)
		 {
		    System.out.println(line);
		 }
		 p.waitFor();
		 return pdfName;
	}

}
