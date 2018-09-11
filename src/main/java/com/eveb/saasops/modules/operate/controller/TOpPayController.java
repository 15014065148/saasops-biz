package com.eveb.saasops.modules.operate.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import com.eveb.saasops.modules.operate.service.TOpPayService;
import com.eveb.saasops.modules.operate.service.TOpPaybankrelationService;
import com.eveb.saasops.modules.operate.service.TOpPaywebsiterelationService;
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
@RequestMapping("/bsapi/merchant/toppay")
@Api(value = "TOpPay", description = "")
public class TOpPayController {
    @Autowired
    private TOpPayService tOpPayService;
    @Autowired
    private TOpPaybankrelationService tOpPaybankrelationService;
    @Autowired
    private TOpPaywebsiterelationService tOpPaywebsiterelationService;

    @GetMapping("/list")
    public R list(@ModelAttribute TOpPay tOpPay, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tOpPayService.queryListPage(tOpPay, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TOpPay tOpPay = tOpPayService.queryObject(id);
        tOpPay.setTOpPaybankrelationList(tOpPaybankrelationService.listBankName(id));
        tOpPay.setTOpPaywebsiterelationList(tOpPaywebsiterelationService.listTOpPaywebsiterelation(id));
        return R.ok().put("tOpPay", tOpPay);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TOpPay tOpPay) {
        tOpPayService.save(tOpPay);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TOpPay tOpPay) {
        tOpPayService.update(tOpPay);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TOpPay tOpPay) {
        tOpPayService.updateAvailable(tOpPay);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TOpPay tOpPay) {
        tOpPayService.deleteBatch(tOpPay);
        return R.ok();
    }
}
