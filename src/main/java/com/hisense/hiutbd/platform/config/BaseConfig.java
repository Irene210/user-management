package com.hisense.hiutbd.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 基本环境配置
 * 这里的配置项都是从配置文件读取来的
 */
@Configuration
public class BaseConfig {
	
	/*******httpclient配置start********/
	@Value("#{propertyConfigurer['httpclient.pool.connectionmanager.max-total']}")
    public int httpclient_pool_connectionmanager_max_total;
	
	@Value("#{propertyConfigurer['httpclient.pool.connectionmanager.max-per-route']}")
	public int httpclient_pool_connectionmanager_max_per_route;
	
	@Value("#{propertyConfigurer['httpclient.requestconfig.read-timeout-milliseconds']}")
	public int httpclient_requestconfig_read_timeout_milliseconds;
	/*******httpclient配置end********/

}
