package com.youbao.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("通用返回消息")
public class MsgResp<T> {
	@ApiModelProperty("返回码  0 成功   -1 失败")
	private Integer resultCode = 0;
	@ApiModelProperty("返回内容")
	private T data;
	@ApiModelProperty("备注")
	private String resultInfo = "成功";

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	
	
}
