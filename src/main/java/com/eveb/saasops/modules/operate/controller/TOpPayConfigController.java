package com.eveb.saasops.modules.operate.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.game.entity.TGmApiprefix;
import com.eveb.saasops.modules.game.service.TGmApiprefixService;
import com.eveb.saasops.modules.merchant.service.TCpSiteService;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import com.eveb.saasops.modules.operate.entity.TOpPayConfig;
import com.eveb.saasops.modules.operate.service.TOpPayService;
import com.eveb.saasops.modules.operate.service.TOpPayConfigService;
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
@RequestMapping("/bsapi/merchant/paymentconfig")
@Api(value = "TOpPaywebsiterelation", description = "支付配置")
public class TOpPayConfigController {

    @Autowired
    private TOpPayConfigService payConfigService;

    @Autowired
    private TGmApiprefixService tGmApiprefixService;

    @Autowired
    private TOpPayService tOpPayService;

    @Autowired
    private TCpSiteService tCpSiteService;

    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TOpPayConfig payConfig, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", payConfigService.queryListPage(payConfig, pageNo, pageSize));
    }

    @GetMapping("/info/{siteCode}")
    @ApiOperation(value = "查询站点下支付平台信息", notes = "查询站点下支付平台信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("siteCode") String siteCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        TGmApiprefix siteInfo = tGmApiprefixService.querySiteInfo(siteCode);
        return R.ok().put("siteInfo", siteInfo).put("paymentList", tCpSiteService.paymentList(siteCode, pageNo, pageSize));
    }

    @GetMapping("/paymentList")
    @ApiOperation(value = "查询支付配置信息", notes = "查询支付配置信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R paymentList(@RequestParam("siteCode") String siteCode, @RequestParam("payCode") String payCode, @RequestParam("payClass") String payClass,
                         @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("paymentList", tCpSiteService.reversePaymentList(siteCode,payCode,payClass, pageNo, pageSize));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增支付平台", notes = "新增支付平台")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TOpPayConfig payConfig) {
        Assert.isNull(payConfig, "参数对象为空");
        Assert.isNull(payConfig.getSiteId(), "站点id为空");
        Assert.isNull(payConfig.getId(), "支付id为空");
        Assert.isNull(payConfig.getPayment(), "payment为空");
        payConfig.setAvailable(1);
        return R.ok().put("success", payConfigService.add(payConfig));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除支付配置", notes = "删除支付配置")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestParam("ids") @NotNull Integer[] ids) {
        return R.ok().put("success", payConfigService.deleteBatch(ids));
    }

    @GetMapping("/closeOrOpen")
    @ApiOperation(value = "停用支付配置", notes = "停用支付配置")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R closeOrOpen(@RequestParam("relateId") @NotNull Integer relateId, @RequestParam("available") @NotNull Integer available) {
        return R.ok().put("success", payConfigService.closeOrOpen(relateId, available));
    }
}
