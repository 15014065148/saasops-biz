package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmApi;
import com.eveb.saasops.modules.game.service.TGmApiService;
import com.eveb.saasops.modules.game.service.TGmDepotService;
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
@RequestMapping("/bsapi/game/tgmapi")
@Api(value = "TGmApi", description = "")
public class TGmApiController extends AbstractController {
    @Autowired
    private TGmApiService tGmApiService;
    @Autowired
    private TGmDepotService tGmDepotService;

    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TGmApi tGmApi, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmApiService.queryListPage(tGmApi, pageNo, pageSize));
    }

    @GetMapping("/listTGmApiAll")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R listTGmApiAll() {
        return R.ok().put("page", tGmApiService.queryList());
    }

    @GetMapping("/findGameDepot")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R findGameDepot() {
        return R.ok().put("page", tGmDepotService.findGameDepot());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmApi tGmApi = tGmApiService.queryObject(id);
        return R.ok().put("tGmApi", tGmApi);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmApi tGmApi) {
        tGmApiService.save(tGmApi);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmApi tGmApi) {
        tGmApiService.update(tGmApi);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TGmApi tGmApi) {
        tGmApiService.updateAvailable(tGmApi);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGmApi tGmApi) {
        tGmApiService.deleteBatch(tGmApi);
        return R.ok();
    }

    @GetMapping("/gameList")
    @ApiOperation(value = "游戏控制列表", notes = "游戏控制列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R queryGameListPage(@RequestParam("apiName") String apiName, @RequestParam("gameName") String gameName,@RequestParam("gameTag") String gameTag,
                    @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmApiService.queryGameListPage(apiName, gameName, gameTag, pageNo, pageSize));
    }

    @GetMapping("/openOrCloseGame")
    @ApiOperation(value = "开启关闭游戏", notes = "开启关闭游戏")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R openOrCloseGame(@RequestParam("id") @NotNull Integer id, @RequestParam("status") @NotNull Integer status) {
        return R.ok().put("page", tGmApiService.openOrCloseGame(id, status));
    }
}
