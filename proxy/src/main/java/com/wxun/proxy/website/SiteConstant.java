package com.wxun.proxy.website;

/**
 * @author Zhuwx
 * @since 2018-06-14 15:06
 */
public class SiteConstant {
//  https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=1
//	http://www.data5u.com/
//	http://www.goubanjia.com/
//	https://www.kuaidaili.com/free/
//	http://m.66ip.cn/1.html
//	https://proxy.mimvp.com/
//	http://www.mogumiao.com/web
//	https://list.proxylistplus.com/Fresh-HTTP-Proxy-List-1
	public enum Site {

		XICI("http://www.xicidaili.com/wt/1.html", "西祠普通ip", "xici", null);

		private String url;
		private String siteName;
		private String code;
		private Class parseClass;

		Site(String url, String siteName, String code, Class parseClass) {
			this.url = url;
			this.siteName = siteName;
			this.code = code;
			this.parseClass = parseClass;
		}

		public Class parseClass() {
			return parseClass;
		}

		public String url() {
			return url;
		}

		public String code() {
			return code;
		}

		public String siteName() {
			return siteName;
		}
	}

	/**
	 * 代理id类型，匿名，透明的
	 */
	public enum ProxyIpType {
		ANONYMOUS, OPENNESS
	}
}
