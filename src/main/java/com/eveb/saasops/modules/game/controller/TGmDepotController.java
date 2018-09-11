package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.annotation.SysLog;
import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmDepot;
import com.eveb.saasops.modules.game.service.TGmDepotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/game/tgmdepot")
@Api(description = "游戏管理-游戏平台")
public class TGmDepotController extends AbstractController {
    @Autowired
    private TGmDepotService tGmDepotService;

    @GetMapping("/listTGmDepot")
    @ApiOperation(value = "查询游戏平台", notes = "查询游戏平台")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R listTGmDepot(@ModelAttribute TGmDepot tGmDepot, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().putPage(tGmDepotService.listTGmDepot(tGmDepot, pageNo, pageSize));
    }

    @GetMapping("/listName")
    @ApiOperation(value = "查询游戏平台", notes = "查询游戏平台")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R listName() {
        return R.ok().putPage(tGmDepotService.findGameDepot());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmDepot tGmDepot = tGmDepotService.queryObject(id);
        return R.ok().put("tGmAnnouncement", tGmDepot);
    }

    @PostMapping("/save")
    @SysLog(module = "游戏平台", methodText = "新增加游戏平台")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmDepot tGmDepot) {
        tGmDepotService.save(tGmDepot);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmDepot tGmDepot) {
        tGmDepotService.update(tGmDepot);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TGmDepot tGmDepot) {
        tGmDepotService.updateAvailable(tGmDepot);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGmDepot tGmDepot) {
        tGmDepotService.deleteBatch(tGmDepot);
        return R.ok();
    }
}
