package com.hisense.hiutbd.platform.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 *  构建基于Pool的RestTemplate
 * 
 * @author  xo
 * @version 1.0 2014-02-17
 */
@Configuration
public class HttpConfig {

	@Autowired
	private BaseConfig baseConfig;

	
	@Bean
	public ClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}

	@Bean
	public CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(baseConfig.httpclient_pool_connectionmanager_max_total);
		connectionManager.setDefaultMaxPerRoute(baseConfig.httpclient_pool_connectionmanager_max_per_route);
		
		RequestConfig config = RequestConfig.custom().setConnectTimeout(baseConfig.httpclient_requestconfig_read_timeout_milliseconds).build();

		HttpClientBuilder hcb = HttpClientBuilder.create()
													.setConnectionManager(connectionManager)
													.setDefaultRequestConfig(config);
		
		CloseableHttpClient defaultHttpClient = hcb.build();
		
		return defaultHttpClient;
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
		
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		List<HttpMessageConverter<?>> formMessageConverters = new ArrayList<HttpMessageConverter<?>>();
		formMessageConverters.add(stringHttpMessageConverter);
		formMessageConverters.add(resourceHttpMessageConverter);
		formHttpMessageConverter.setPartConverters(formMessageConverters);
		
		messageConverters.add(stringHttpMessageConverter);
		messageConverters.add(resourceHttpMessageConverter);
		messageConverters.add(formHttpMessageConverter);
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
}