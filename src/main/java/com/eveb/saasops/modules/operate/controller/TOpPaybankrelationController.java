package com.eveb.saasops.modules.operate.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.operate.entity.TOpPaybankrelation;
import com.eveb.saasops.modules.operate.service.TOpPaybankrelationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/merchant/toppaybankrelation")
@Api(value = "TOpPaybankrelation", description = "支付和银行中间表")
public class TOpPaybankrelationController {
    @Autowired
    private TOpPaybankrelationService tOpPaybankrelationService;

    @GetMapping("/list")
    public R list(@ModelAttribute TOpPaybankrelation tOpPaybankrelation, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize, @RequestParam(value = "orderBy", required = false) String orderBy) {
        return R.ok().put("page", tOpPaybankrelationService.queryListPage(tOpPaybankrelation, pageNo, pageSize, orderBy));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TOpPaybankrelation tOpPaybankrelation = tOpPaybankrelationService.queryObject(id);
        return R.ok().put("tOpPaybankrelation", tOpPaybankrelation);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TOpPaybankrelation tOpPaybankrelation) {
        tOpPaybankrelationService.insertSelective(tOpPaybankrelation);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TOpPaybankrelation tOpPaybankrelation) {
        tOpPaybankrelationService.update(tOpPaybankrelation);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody Integer[] ids) {
        tOpPaybankrelationService.deleteBatch(ids);
        return R.ok();
    }
}
