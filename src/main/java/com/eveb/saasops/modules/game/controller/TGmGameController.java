package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGameLogo;
import com.eveb.saasops.modules.game.entity.TGmGame;
import com.eveb.saasops.modules.game.service.TGmGameService;
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
import java.util.Objects;

@RestController
@RequestMapping("/bsapi/game/tgmgame")
@Api(value = "TGmGame", description = "游戏列表")
public class TGmGameController extends AbstractController {
    @Autowired
    private TGmGameService tGmGameService;

    @GetMapping("/list")
    @ApiOperation(value = "查询", notes = "查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TGmGame tGmGame, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmGameService.queryListPage(tGmGame, pageNo, pageSize));
    }

    @GetMapping("/listGame")
    @ApiOperation(value = "查询（统计）", notes = "查询（统计）")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R listGame(@ModelAttribute TGameLogo tGameLogo, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmGameService.queryListGamePage(tGameLogo, pageNo, pageSize));
    }

    @GetMapping("/findGameList")
    @ApiOperation(value = "根据平台查询游戏列表", notes = "根据平台查询游戏列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R findGameList(@ModelAttribute TGmGame tGmGame, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmGameService.findGameList(tGmGame, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmGame tGmGame = tGmGameService.queryInfo(id);
        return R.ok().put("tGmGame", tGmGame);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmGame tGmGame) {
        tGmGameService.save(tGmGame);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "单个游戏修改", notes = "单个游戏修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmGame tGmGame) {
        tGmGameService.update(tGmGame);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "单个游戏修改", notes = "单个游戏修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TGmGame tGmGame) {
        tGmGameService.updateAvailable(tGmGame);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "电子游戏批量删除", notes = "电子游戏批量删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R gameDelete(@RequestBody TGmGame tGmGame) {
        if (tGmGame.getIds() == null || tGmGame.getIds().length <= 0) {
            throw new RRException("请选择需要删除的数据！");
        }
        tGmGameService.deleteBatch(tGmGame);
        return R.ok();
    }
}
