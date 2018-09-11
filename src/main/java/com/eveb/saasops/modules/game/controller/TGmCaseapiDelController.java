package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.game.entity.TGmCaseapiDel;
import com.eveb.saasops.modules.game.service.TGmCaseapiDelService;
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
@RequestMapping("/bsapi/game/tgmcaseapidel")
@Api(value = "TGmCaseapiDel", description = "")
public class TGmCaseapiDelController {
    @Autowired
    private TGmCaseapiDelService tGmCaseapiDelService;

    @GetMapping("/list")
    public R list(@ModelAttribute TGmCaseapiDel tGmCaseapiDel, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmCaseapiDelService.queryListPage(tGmCaseapiDel, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmCaseapiDel tGmCaseapiDel = tGmCaseapiDelService.queryObject(id);
        return R.ok().put("tGmCaseapiDel", tGmCaseapiDel);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmCaseapiDel tGmCaseapiDel) {
        tGmCaseapiDelService.save(tGmCaseapiDel);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmCaseapiDel tGmCaseapiDel) {
        tGmCaseapiDelService.update(tGmCaseapiDel);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGmCaseapiDel tGmCaseapiDel) {
        tGmCaseapiDelService.deleteBatch(tGmCaseapiDel);
        return R.ok();
    }
}
