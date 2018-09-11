package com.eveb.saasops.modules.common.controller;

import java.io.IOException;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.common.service.QiNiuYunUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bsapi/common")
@Api(value = "七牛云文件上传", description = "七牛云文件上传")
public class UploadFileController {
	
	@Autowired
    private QiNiuYunUtil qiNiuYunUtil;
	
	@PostMapping("/uploadFile")
    @ApiOperation(value = "上传图片接口", notes = "上传图片接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody MultipartFile file) {
		byte[] fileBuff = new byte[2048];
		try {
			fileBuff = IOUtils.toByteArray(file.getInputStream());
			String fileName = qiNiuYunUtil.uploadFile(fileBuff, file.getOriginalFilename());
			return R.ok(0, fileName);
		} catch (IOException e) {
			return R.ok(500, "上传图片异常");
		}
    }
}
