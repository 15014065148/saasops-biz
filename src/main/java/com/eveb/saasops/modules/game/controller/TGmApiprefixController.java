package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.exception.R200Exception;
import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.game.entity.TGmApi;
import com.eveb.saasops.modules.game.entity.TGmApiprefix;
import com.eveb.saasops.modules.game.service.TGmApiService;
import com.eveb.saasops.modules.game.service.TGmApiprefixService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/game/tgmapiprefix")
@Api(value = "TGmApiprefix", description = "API配置接口")
public class TGmApiprefixController {

    @Autowired
    private TGmApiprefixService tGmApiprefixService;

    @Autowired
    private TGmApiService apiService;

    @GetMapping("/list")
    @ApiOperation(value = "查询", notes = "查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TGmApiprefix tGmApiprefix, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmApiprefixService.queryListPage(tGmApiprefix, pageNo, pageSize));
    }

    @GetMapping("/info/{siteCode}")
    @ApiOperation(value = "信息", notes = "信息")
    public R info(@PathVariable("siteCode") String siteCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        TGmApiprefix siteInfo = tGmApiprefixService.querySiteInfo(siteCode);
        return R.ok().put("siteInfo", siteInfo).put("apiList", tGmApiprefixService.getApiBySiteCode(siteCode, pageNo, pageSize));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmApiprefix[] arr) {
        Assert.isNull(arr, "参数对象不能为空");
        tGmApiprefixService.save(arr);
        return R.ok();
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestParam("ids") @NotNull Integer[] ids) {
        if(null == ids){
            throw new R200Exception("请选择想要删除的数据");
        }
        return R.ok().put("success", tGmApiprefixService.deleteInfoById(ids));
    }

    @GetMapping("/close")
    @ApiOperation(value = "停用api", notes = "停用api")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestParam("id") @NotNull Integer id, @RequestParam("available") @NotNull Integer available) {
        Assert.isNull(id, "id不能为空");
        Assert.isNull(available, "状态不能为空");
        return R.ok().put("success", tGmApiprefixService.closeApi(id, available));
    }

    @GetMapping("/apiList")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@RequestParam("siteCode") @NotNull String siteCode, @RequestParam("apiName") String apiName, @RequestParam("available") Integer available, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        Assert.isNull(siteCode, "siteCode不能为空"); //查询未被当前站点使用的api
        return R.ok().put("page", apiService.queryApiListPage(siteCode, apiName, available, pageNo, pageSize));
    }

}
