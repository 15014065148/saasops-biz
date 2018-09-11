package com.eveb.saasops.modules.merchant.controller;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.service.TGmApiService;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.service.TCpSiteService;
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
@RequestMapping("/bsapi/merchant/tcpsite")
@Api(value = "TCpSite", description = "站点表")
public class TCpSiteController extends AbstractController {
    @Autowired
    private TCpSiteService tCpSiteService;
    @Autowired
    private TGmApiService apiService;

    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TCpSite tCpSite, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tCpSiteService.queryListPage(tCpSite, pageNo, pageSize));
    }

    @GetMapping("/listAll")
    public R listAll() {
        return R.ok().put("page", tCpSiteService.queryList());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TCpSite tCpSite = tCpSiteService.queryObject(id);
        return R.ok().put("tCpSite", tCpSite);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TCpSite tCpSite) {
        tCpSiteService.save(tCpSite);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TCpSite tCpSite) {
        tCpSiteService.update(tCpSite);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TCpSite tCpSite) {
        Assert.isNull(tCpSite, "请选择数据");
        Assert.isNull(tCpSite.getIds(), "请选择数据");
        tCpSiteService.deleteBatch(tCpSite);
        return R.ok();
    }

    @GetMapping("/apiList")
    @ApiOperation(value = "通过站点查api线路列表", notes = "通过站点查api线路列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R apiList(@RequestParam("siteCode") @NotNull String siteCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        Assert.isBlank(siteCode, "siteCode为空");
        return R.ok().put("page", apiService.getApiBySiteCode(siteCode, pageNo, pageSize));
    }

    @GetMapping("/paymentList")
    @ApiOperation(value = "通过站点支付平台列表", notes = "通过站点支付平台列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R paymentList(@RequestParam("siteCode") @NotNull String siteCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        Assert.isBlank(siteCode, "siteCode为空");
        return R.ok().put("page", tCpSiteService.paymentList(siteCode, pageNo, pageSize));
    }

    @GetMapping("/domainList")
    @ApiOperation(value = "通过站点查询域名列表", notes = "通过站点查api线路列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R domainList(@RequestParam("siteCode") @NotNull String siteCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        Assert.isBlank(siteCode, "siteCode为空");
        return R.ok().put("page", tCpSiteService.domainList(siteCode, pageNo, pageSize));
    }
}
