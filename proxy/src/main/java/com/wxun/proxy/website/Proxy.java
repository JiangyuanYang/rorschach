package com.wxun.proxy.website;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetSocketAddress;

/**
 * @author Zhuwx
 * @since 2018-06-14 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proxy {
	private String type;
	private InetSocketAddress socketAddress;
	private SiteConstant.ProxyIpType proxyIpType;

	public static void main(String[] args) {
	}
}
