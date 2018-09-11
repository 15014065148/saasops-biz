package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.game.entity.TGmDepotcat;
import com.eveb.saasops.modules.game.service.TGmDepotcatService;
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
@RequestMapping("/game/tgmdepotcat")
@Api(value = "TGmDepotcat", description = "")
public class TGmDepotcatController {
    @Autowired
    private TGmDepotcatService tGmDepotcatService;

    @GetMapping("/list")
    @RequiresPermissions("game:tgmdepotcat:list")
    public R list(@ModelAttribute TGmDepotcat tGmDepotcat, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmDepotcatService.queryListPage(tGmDepotcat, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @RequiresPermissions("game:tgmdepotcat:info")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmDepotcat tGmDepotcat = tGmDepotcatService.queryObject(id);
        return R.ok().put("tGmDepotcat", tGmDepotcat);
    }

    @PostMapping("/save")
    @RequiresPermissions("game:tgmdepotcat:save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmDepotcat tGmDepotcat) {
        tGmDepotcatService.save(tGmDepotcat);
        return R.ok();
    }

    @PostMapping("/update")
    @RequiresPermissions("game:tgmdepotcat:update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmDepotcat tGmDepotcat) {
        tGmDepotcatService.update(tGmDepotcat);
        return R.ok();
    }

    @PostMapping("/delete")
    @RequiresPermissions("game:tgmdepotcat:delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody Integer[] ids) {
        return R.ok();
    }
}
