/*package com.eveb.saasops.modules.common.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

*//**
 * Created by Lebron on 2018/5/31.
 *//*
@FeignClient(value="saasops-v2-service")
public interface SaasopsV2Service {
	
    //通过SpringMVC的注解来配置所綁定的服务下的具体实现
    @Headers({"SToken: {siteCode}","token: {token}"})
    @RequestLine("POST /sysapi/uploadpic")
    String uploadpic(@Param("siteCode") String siteCode, @Param("token") String token, @Param("uploadFile")MultipartFile uploadFile);

}
*/