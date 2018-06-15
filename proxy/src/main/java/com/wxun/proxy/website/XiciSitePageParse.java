package com.wxun.proxy.website;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.wxun.proxy.website.SiteConstant.ProxyIpType;
import static java.util.stream.Collectors.groupingBy;

/**
 * 西祠代理
 * http://www.xicidaili.com/wt/1
 * @author Zhuwx
 * @since 2018-06-14 15:19
 */
@Slf4j
public class XiciSitePageParse implements SitePageParse {

	@Override
	public Map<String, Map<ProxyIpType, List<Proxy>>> parse(String html) {
		Document document = Jsoup.parse(html);
		Elements elements = document.select("table[id=ip_list] tr[class]");
		List<Proxy> proxyList = new ArrayList<>();

		for (Element element : elements) {
			String ip = element.select("td:eq(1)").first().text();
			String port = element.select("td:eq(2)").first().text();
			String isAnonymous = element.select("td:eq(4)").first().text();
			String type = element.select("td:eq(5)").first().text();
			log.debug("parse result = " + type + "://" + ip + ":" + port + "  " + isAnonymous);

			ProxyIpType proxyIpType = isAnonymous.contains("匿") ? ProxyIpType.ANONYMOUS : ProxyIpType.OPENNESS;

			InetSocketAddress socketAddress = new InetSocketAddress(ip, Integer.valueOf(port));
			proxyList.add(new Proxy(type.toUpperCase(), socketAddress, proxyIpType));
		}
		return proxyList.stream().collect(groupingBy(Proxy::getType, groupingBy(Proxy::getProxyIpType)));
	}


}
