package com.wxun.proxy.task;

import com.alibaba.fastjson.JSON;
import com.wxun.proxy.http.OkHttp;
import com.wxun.proxy.util.MongoUtil;
import com.wxun.proxy.website.Proxy;
import com.wxun.proxy.website.SiteConstant;
import com.wxun.proxy.website.SitePageParse;
import com.wxun.proxy.website.XiciSitePageParse;
import org.bson.BSON;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

/**
 * @author Zhuwx
 * @since 2018-06-15 09:34
 */
public class IpTask {

	public static void main(String[] args) {
		OkHttp.HttpResponse httpResponse = OkHttp.doGet("http://www.xicidaili.com/wt/1", null);
		if (httpResponse.isSuccess()) {
			String data = httpResponse.getData();
			SitePageParse sitePageParse = new XiciSitePageParse();
			Map<String, Map<SiteConstant.ProxyIpType, List<Proxy>>> parse = sitePageParse.parse(data);

			Map<SiteConstant.ProxyIpType, List<Proxy>> http = parse.get("HTTP");
			List<Proxy> proxies = http.get(SiteConstant.ProxyIpType.ANONYMOUS);
			proxies.forEach(a -> {
//				SocketAddress socketAddress = a.getSocketAddress();
//				System.out.println(((InetSocketAddress) socketAddress).getHostString());
//				System.out.println(((InetSocketAddress) socketAddress).getPort());
				System.out.println(JSON.toJSONString(a.getSocketAddress()));
				MongoUtil.insert(JSON.toJSONString(a.getSocketAddress()));
			});
		}
	}
}
