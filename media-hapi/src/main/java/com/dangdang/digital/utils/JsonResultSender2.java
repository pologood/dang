package com.dangdang.digital.utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSON;

/**
 * JSON格式的返回结果处理类.
 * 
 * @author yangming
 * 
 */
public class JsonResultSender2 extends ResultSender {

	private Map<String, Object> status = new HashMap<String, Object>();

	private Map<String, Object> result = new HashMap<String, Object>();
	
	private boolean gzip = false;

	public static JsonResultSender2 getInstance(){
		return new JsonResultSender2();
	}
	
	public static JsonResultSender2 getGzipInstance(){
		JsonResultSender2 sender = new JsonResultSender2();
		sender.setGzip(true);
		return sender;
	}
	
	public boolean isGzip() {
		return gzip;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}

	@Override
	public void success(HttpServletResponse response) throws Exception {
		send(response);
	}

	@Override
	public void fail(int errorCode, String message, HttpServletResponse response) throws Exception {
		status.put("code", errorCode);
		status.put("message", message);
		result.put("status", status);
		putErrorStatus();
		sendResult(error, response);
	}

	public void sendResult(Map model, HttpServletResponse response) throws Exception {
		model.put("currentDate", DateUtil.getDateTime(new Date()));
		model.put("systemDate", String.valueOf(System.currentTimeMillis()));
		result.put("data", model);

		String json = "";
		String resultContentJson = "";
		if (CollectionUtils.isNotEmpty(ebookFiterFields) || CollectionUtils.isNotEmpty(ebookHtmlFilterFields) ||
				CollectionUtils.isNotEmpty(ebookLineFilterFields)) {
			SimplePropertyPreFilter spp = new SimplePropertyPreFilter();
			if (CollectionUtils.isNotEmpty(ebookFiterFields)) {
				spp.setExcludes(ebookFiterFields);
			}
			if (CollectionUtils.isNotEmpty(ebookHtmlFilterFields)) {
				spp.setHtmlFilterFields(ebookHtmlFilterFields);
			}
			if (CollectionUtils.isNotEmpty(ebookLineFilterFields)) {
				spp.setLineFilterFields(ebookLineFilterFields);
			}
			json = JSON.toJSONString(result, spp);
		} else {
			json = JSON.toJSONString(result);
		}
		resultContentJson = dealWithResultContentJson(result, model);
		setResultContent(resultContentJson);
		//addAppHead(response); //写header信息
		
		setReturnJson(json);
		
		if (response == null) { // multiaction 转发response为空
			return;
		}
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		if (gzip) {
			byte[] output = GZIPUtil.compress(json.getBytes("utf-8"));
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-Length", String.valueOf(output.length));
			OutputStream out = response.getOutputStream();
			out.write(output);
			out.flush();
			out.close();
		}else {
			response.setHeader("Content-Length", String.valueOf(json.getBytes("utf-8").length));
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		}
		
	}
	
	/**
	 * 
	 * @Title: dealWithResultContentJson
	 * @Description: TODO处理优化api客户端ddclick存储内容去掉作者简介authorIntroduce与目录catalog；
	 * 优化mobileBook显示内容(productId,name,title,desc,price,iosprice)
	 * @param model
	 * @return
	 */
	private String dealWithResultContentJson(Map<String, Object> result, Map<String, String> model) {
		if (model.containsKey("authorIntroduce")) {
			model.remove("authorIntroduce");
		}
		if (model.containsKey("catalog")) {
			model.remove("catalog");
		}
		SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter();
		Set<String> ebookReturnSet = new HashSet<String>();
		ebookReturnSet.add("productId");
		ebookReturnSet.add("bookName");
		ebookReturnSet.add("author");
		ebookReturnSet.add("price");
		ebookReturnSet.add("publisher");
		ebookReturnSet.add("publishDate");
		simplePropertyPreFilter.setIncludes(ebookReturnSet);
		if (CollectionUtils.isNotEmpty(ebookFiterFields) || CollectionUtils.isNotEmpty(ebookHtmlFilterFields) ||
				CollectionUtils.isNotEmpty(ebookLineFilterFields)) {
			if (CollectionUtils.isNotEmpty(ebookFiterFields)) {
				simplePropertyPreFilter.setExcludes(ebookFiterFields);
			}
			if (CollectionUtils.isNotEmpty(ebookHtmlFilterFields)) {
				simplePropertyPreFilter.setHtmlFilterFields(ebookHtmlFilterFields);
			}
			if (CollectionUtils.isNotEmpty(ebookLineFilterFields)) {
				simplePropertyPreFilter.setLineFilterFields(ebookLineFilterFields);
			}
		}
		String json = JSON.toJSONString(result, simplePropertyPreFilter);
		return json;
	}
	
	@Override
	public void send(HttpServletResponse response) throws Exception {
		status.put("code", 0);
		result.put("status", status);
		result.put("systemDate", System.currentTimeMillis());
		putSuccessStatus();
		sendResult(model, response);
	}

	@Override
	public void multiActionPartFail(HttpServletResponse response) throws Exception {
		status.put("code", SUCCESS_CODE);
		result.put("status", status);
		putErrorStatus();
		sendResult(error, response);
	}

}
