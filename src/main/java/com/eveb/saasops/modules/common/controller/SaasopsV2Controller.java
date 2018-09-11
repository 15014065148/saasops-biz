/*package com.eveb.saasops.modules.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eveb.saasops.modules.common.service.SaasopsV2Service;

import io.swagger.annotations.Api;

*//**
 * 网关内springBoot服务调用
 * @author Lebron
 *
 *//*
@RestController
@RequestMapping("/saasops/feign")
@Api(value = "saasopsFeign", description = "远程调用")
public class SaasopsV2Controller  {
	
	@Autowired
    private SaasopsV2Service saasopsV2;
    
    *//**
     * 公共上传图片接口
     * @param token
     * @param uploadFile
     * @return
     *//*
    @PostMapping("/uploadpic")
    String uploadpic(String token, MultipartFile uploadFile){
        return saasopsV2.uploadpic("test", token, uploadFile);
    }
}
*/