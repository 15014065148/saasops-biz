package com.eveb.saasops.modules.game.controller;


import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGameLogo;
import com.eveb.saasops.modules.game.service.TGameLogoService;
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
import com.eveb.saasops.common.utils.R;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/game/tgamelogo")
@Api(value = "TGameLogo", description = "个性图和LOGO")
public class TGameLogoController extends AbstractController {
    @Autowired
    private TGameLogoService tGameLogoService;

    @GetMapping("/list")
    public R list(@ModelAttribute TGameLogo tGameLogo, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGameLogoService.queryListPage(tGameLogo, pageNo, pageSize));
    }

    @GetMapping("/listInfo")
    public R listInfo(@RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGameLogoService.listInfo(pageNo,pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGameLogo tGameLogo = tGameLogoService.queryInfo(id);
        return R.ok().put("tGameLogo", tGameLogo);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGameLogo tGameLogo) {
        tGameLogoService.save(tGameLogo);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TGameLogo tGameLogo) {
        tGameLogoService.updateAvailable(tGameLogo);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGameLogo tGameLogo) {
        tGameLogoService.update(tGameLogo);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGameLogo tGameLogo) {
        tGameLogoService.deleteBatch(tGameLogo);
        return R.ok();
    }
}
