package com.dangdang.digital.utils;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * API返回结果处理类.
 * 
 * @author yangming
 * 
 */
public abstract class ResultSender {

	/** 成功结果. */
	public static final int SUCCESS_CODE = 0;
	/** 失败结果. */
	public static final int FAIL_CODE = 1;
	/** 其他错误. */
	public static final int OTHER_ERROR = 200;
	/** 用户验证失败. */
	public static final int USER_VALIDATE_FAILED = 1;
	/** 用户已存在. */
	public static final int USER_EXISTED = 1;
	/** 设备未绑定. */
	public static final int DEVICE_UNBIND = 2;
	/** 绑定设备失败. */
	public static final int BIND_DEVICE_FULL = 2;
	/** 无权限. */
	public static final int YOU_HAVE_NO_RIGHT = 5;
	/** 缺省的MIME类型. */
	public static final String DEFAULT_MIME_TYPE = "text/html";
	/** XML的MIME类型. */
	public static final String XML_MIME_TYPE = "text/xml";
	/** API响应公共头名称. */
	public static final String BASE_RES_HEADER = "baseRespHeader";
	/**
	 * 存放正常返回时数据.
	 */
	protected Map<String, Object> model;

	protected Map<String, Object> baseHeader;
	
	/**
	 * 返回json
	 */
	private String returnJson;
	/**
	 * 存放正常返回时数据.
	 */
	protected Set<String> ebookFiterFields = new HashSet<String>();
	
	/**
	 * 存放要进行html过滤的字段名称.
	 */
	protected Set<String> ebookHtmlFilterFields = new HashSet<String>();
	
	/**
	 * 存放要进行换行符过滤的文本.
	 */
	protected Set<String> ebookLineFilterFields = new HashSet<String>();

	/**
	 * 存放错误返回时数据.
	 */
	protected Map<String, Object> error;

	/**
	 * DDClick补充数据 custId:用户id,statusCode:返回的状态码,resultContent:返回数据.
	 */
	protected Long custId;
	protected int statusCode;
	protected String resultContent;

	public ResultSender() {
		model = new HashMap<String, Object>();
		error = new HashMap<String, Object>();
		baseHeader = new HashMap<String, Object>();
	}

	/**
	 * 返回结果.
	 * 
	 * @param response
	 * @throws Exception
	 */
	public abstract void send(HttpServletResponse response) throws Exception;

	/**
	 * 返回成功结果.
	 * 
	 * @param response
	 * @throws Exception
	 */
	public abstract void success(HttpServletResponse response) throws Exception;

	/**
	 * 返回失败结果.
	 * 
	 * @param response
	 * @throws Exception
	 */
	public abstract void fail(int errorCode, String message, HttpServletResponse response) throws Exception;
	
	/**
	 * 
	 * Description: 下载文件
	 * 
	 * @Version1.0 2014年12月4日 上午9:38:36 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	public void download(HttpServletResponse response, String fileName, String filePath, Long fileLength, String range, Date deadline)
			throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(range)) {
			response.setHeader("Content-Range", range + fileLength);
		}
		if (deadline != null) {
			response.setHeader("monthlyEndTime", String.valueOf(deadline.getTime()));
		}
		// 增加成功标识
		response.setHeader("statusCode ", String.valueOf(SUCCESS_CODE));
		response.setContentType("application/octet-stream");
		response.setHeader("Accept-Ranges", "bytes");
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
		String ext = FilenameUtils.getExtension(fileName);
		response.setHeader("file-type", ext);
		response.setHeader("Content-Disposition",
				"attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		response.setHeader("X-Accel-Redirect", filePath);
	}

	/**
	 * 多action请求部分错误返回结果.
	 * 
	 * @param errorCode
	 * @param message
	 * @param response
	 * @throws Exception
	 */
	public abstract void multiActionPartFail(HttpServletResponse response) throws Exception;

	/**
	 * 直接返回结果.
	 * 
	 * @param response
	 * @throws Exception
	 */
	public void directSend(String result, HttpServletResponse response) throws Exception {
		directSend(result, response, DEFAULT_MIME_TYPE);
	}

	public void directSend(String result, HttpServletResponse response, String mimeType) throws Exception {
		response.setContentType(mimeType + ";charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Length", String.valueOf(result.getBytes("utf-8").length));
		PrintWriter writer = response.getWriter();
		writer.write(result);
		writer.flush();
		writer.close();
	}

	public void errorResponseHeader(int errorCode, String message, HttpServletResponse response) throws Exception {
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("code", errorCode);
		error.put("msg", message);
		String data = JSON.toJSONString(error);
		data = Base64Utils.encodeBytes(data.getBytes());
		response.setHeader("addRunErrorCode", data);
		
		putHeader("code", errorCode); //写公共头
		putHeader("msg", message); //
	}

	public void putHeader(String key, Object object){
		baseHeader.put(key, object);
	}
	
	public void addAppHead(HttpServletResponse response){
		String data = JSON.toJSONString(baseHeader);
		data = Base64Utils.encodeBytes(data.getBytes());
		response.setHeader(BASE_RES_HEADER, data);
	}
	
	public void put(String key, Object value) {
		model.put(key, value);
	}

	public void putErrorInfo(String key, Object value) {
		error.put(key, value);
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public Set<String> getEbookFiterFields() {
		return ebookFiterFields;
	}

	public void setEbookFiterFields(Set<String> ebookFiterFields) {
		this.ebookFiterFields = ebookFiterFields;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void putSuccessStatus() {
		statusCode = 1;
	}

	public void putErrorStatus() {
		statusCode = 0;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public Set<String> getEbookHtmlFilterFields() {
		return ebookHtmlFilterFields;
	}

	public void setEbookHtmlFilterFields(Set<String> ebookHtmlFilterFields) {
		this.ebookHtmlFilterFields = ebookHtmlFilterFields;
	}

	public Set<String> getEbookLineFilterFields() {
		return ebookLineFilterFields;
	}

	public void setEbookLineFilterFields(Set<String> ebookLineFilterFields) {
		this.ebookLineFilterFields = ebookLineFilterFields;
	}

	public Map<String, Object> getError() {
		return error;
	}

	public String getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(String returnJson) {
		this.returnJson = returnJson;
	}		
	
}
