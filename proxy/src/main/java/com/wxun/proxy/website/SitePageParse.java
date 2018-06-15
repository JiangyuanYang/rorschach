package com.wxun.proxy.website;

import java.util.List;
import java.util.Map;

/**
 * 代理ip解析
 *
 * @author wxun
 * @create 2018-06-14 15:16
 */
public interface SitePageParse {

	Map<String, Map<SiteConstant.ProxyIpType, List<Proxy>>> parse(String html);

}
