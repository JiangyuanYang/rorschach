package com.wxun.proxy.http;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Map;
import java.util.Objects;

/**
 * @author Zhuwx
 * @since 2018-06-14 13:19
 */
@Slf4j
public class OkHttp {


	public static HttpResponse doGet(String url, Map<String, String> param) {
		HttpResponse httpResponse;
		//构造请求参数、地址
		HttpUrl httpUrl = HttpUrl.parse(url);
		Objects.requireNonNull(httpUrl);
		HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
		if (param != null) {
			param.forEach(httpUrlBuilder::addQueryParameter);
		}

		Request request = new Request.Builder()
				.headers(getHeaders())
				.url(httpUrlBuilder.build())
				.get()
				.build();

		OkHttpClient httpClient = new OkHttpClient.Builder()
				.proxy(getProxy())
				.build();
		Call call = httpClient.newCall(request);
		try {
			httpResponse = getHttpResponse(call);
		} catch (IOException e) {
			log.error("请求地址：{},发送Get请求异常：", url, e);
			httpResponse = new HttpResponse();
			httpResponse.setCode(0);
			httpResponse.setMsg("请求异常：" + e.getMessage());
		}
		return httpResponse;
	}


	public static HttpResponse doPost(String url, Map<String, String> param, PostParamType paramType) {
		HttpResponse httpResponse;
		//构造请求参数、地址
		RequestBody requestBody;

		//JSON格式请求体接受参数 @RequestBody
		if (PostParamType.JSON.equals(paramType)) {
			requestBody = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(param));
		} else {
			FormBody.Builder formBody = new FormBody.Builder();
			if (param != null) {
				param.forEach(formBody::add);
			}
			requestBody = formBody.build();
		}
		Request request = new Request.Builder()
				.url(url)
				.headers(getHeaders())
				.post(requestBody)
				.build();

		OkHttpClient httpClient = new OkHttpClient.Builder().build();
		Call call = httpClient.newCall(request);
		try {
			httpResponse = getHttpResponse(call);
		} catch (IOException e) {
			log.error("请求地址：{},发送post请求异常：", url, e);
			httpResponse = new HttpResponse();
			httpResponse.setCode(0);
			httpResponse.setMsg("请求异常：" + e.getMessage());
		}
		return httpResponse;
	}

	private static HttpResponse getHttpResponse(Call call) throws IOException {
		HttpResponse httpResponse = new HttpResponse();
		Response execute = call.execute();
		httpResponse.setCode(execute.code());

		if (!execute.isSuccessful()) {
			httpResponse.setMsg("网络请求失败");
			return httpResponse;
		}

		ResponseBody body = execute.body();
		if (body == null) {
			httpResponse.setMsg("请求无返回内容");
			return httpResponse;
		}
		httpResponse.setSuccess(true);
		httpResponse.setData(body.string());
		return httpResponse;
	}

	public static Proxy getProxy() {
		SocketAddress socketAddress = new InetSocketAddress("60.194.46.119", 3128);
		return new Proxy(Proxy.Type.HTTP, socketAddress);
	}

	public static Headers getHeaders() {
		return new Headers.Builder().add("Cache-Control", "no-cache")
				.add("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like "
								+ "Gecko) Chrome/64.0.3282.167 Safari/537.36").build();
	}

	public static void main(String[] args) {
		HttpResponse httpResponse = doGet("http://www.data5u.com/", null);
		System.out.println(httpResponse.getData());
	}

	@Data
	public static class HttpResponse {
		/** 0 io异常，其它为对应的http status code */
		private int code;
		private String msg;
		private String data;
		private boolean success;
	}

	/**
	 * post请求参数的方式
	 */
	private enum PostParamType {
		/** JSON格式 */
		JSON,
		/** key=value格式 */
		PARAM
	}
}
