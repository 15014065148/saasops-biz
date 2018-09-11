package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.game.entity.TGmCode;
import com.eveb.saasops.modules.game.service.TGmCodeService;
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
@RequestMapping("/game/tgmcode")
@Api(value = "TGmCode", description = "")
public class TGmCodeController {
    @Autowired
    private TGmCodeService tGmCodeService;

    @GetMapping("/list")
    @RequiresPermissions("game:tgmcode:list")
    public R list(@ModelAttribute TGmCode tGmCode, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmCodeService.queryListPage(tGmCode, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @RequiresPermissions("game:tgmcode:info")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmCode tGmCode = tGmCodeService.queryObject(id);
        return R.ok().put("tGmCode", tGmCode);
    }

    @PostMapping("/save")
    @RequiresPermissions("game:tgmcode:save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmCode tGmCode) {
        tGmCodeService.save(tGmCode);
        return R.ok();
    }

    @PostMapping("/update")
    @RequiresPermissions("game:tgmcode:update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmCode tGmCode) {
        tGmCodeService.update(tGmCode);
        return R.ok();
    }

    @PostMapping("/delete")
    @RequiresPermissions("game:tgmcode:delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody Integer[] ids) {
        return R.ok();
    }
}
