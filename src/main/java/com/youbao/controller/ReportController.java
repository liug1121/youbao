package com.youbao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youbao.resp.MsgResp;
import com.youbao.resq.ReportResq;
import com.youbao.service.WkhtmlToPdfService;

@RestController
@RequestMapping("/report/v1.0")
public class ReportController {

	@Autowired
	private WkhtmlToPdfService wkhtmlToPdfService;
	@PostMapping("/report")
	@ResponseBody
	public MsgResp<String> getReport(@RequestBody ReportResq resq){
		MsgResp<String> resp = new MsgResp<>();
		try {
			String pdfFileName = wkhtmlToPdfService.createPdf(resq.getName(), resq.getIndentify());
			String retUrl = "http://xbk.tdj.cn/pdf/" + pdfFileName;
			resp.setData(retUrl);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
