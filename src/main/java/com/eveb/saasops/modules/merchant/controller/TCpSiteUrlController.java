package com.eveb.saasops.modules.merchant.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmApi;
import com.eveb.saasops.modules.game.entity.TGmApiprefix;
import com.eveb.saasops.modules.game.service.TGmApiService;
import com.eveb.saasops.modules.game.service.TGmApiprefixService;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.entity.TCpSiteUrl;
import com.eveb.saasops.modules.merchant.service.TCpSiteService;
import com.eveb.saasops.modules.merchant.service.TCpSiteUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/merchant/tcpsiteurl")
@Api(value = "TCpSiteUrl", description = "域名配置")
public class TCpSiteUrlController extends AbstractController {
    @Autowired
    private TCpSiteUrlService siteUrlService;
    @Autowired
    private TGmApiprefixService tGmApiprefixService;

    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TCpSiteUrl siteUrl, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", siteUrlService.queryListPage(siteUrl, pageNo, pageSize));
    }

    @GetMapping("/info/{siteId}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("siteId") String siteId, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        TGmApiprefix siteInfo = tGmApiprefixService.querySiteInfo(siteId);
        return R.ok().put("siteInfo", siteInfo).put("domainList", siteUrlService.findDomainListPage(siteId, pageNo, pageSize));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除站点下域名", notes = "删除站点下域名")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestParam("ids") @NotNull Integer[] ids) {
        Assert.isNull(ids, "请选择数据");
        return R.ok().put("success", siteUrlService.deleteBatch(ids));
    }

    @GetMapping("/closeOrOpen")
    @ApiOperation(value = "停用或启用站点下域名", notes = "停用或启用站点下域名")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R closeOrOpen(@RequestParam("id") @NotNull Integer id, @RequestParam("available") @NotNull Integer available) {
        Assert.isNull(id, "域名不能为空");
        Assert.isNull(available, "状态不能为空");
        return R.ok().put("success", siteUrlService.closeOrOpen(id, available));
    }

    @PostMapping("/add")
    @ApiOperation(value = "删除站点下域名", notes = "删除站点下域名")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R add(@RequestBody TCpSiteUrl siteUrl) {
        Assert.isNull(siteUrl, "参数对象为空");
        Assert.isNull(siteUrl.getSiteUrl(), "域名为空");
        Assert.isNull(siteUrl.getClientType(), "客户端类型为空");
        return R.ok().put("success", siteUrlService.addInfo(siteUrl));
    }
}
