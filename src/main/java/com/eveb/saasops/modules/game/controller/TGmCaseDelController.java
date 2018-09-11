package com.eveb.saasops.modules.game.controller;


import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmCaseDel;
import com.eveb.saasops.modules.game.service.TGmCaseDelService;
import com.eveb.saasops.modules.game.service.TGmCaseapiDelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/game/tgmcasedel")
@Api(value = "TGmCaseDel", description = "")
public class TGmCaseDelController extends AbstractController {
    @Autowired
    private TGmCaseDelService tGmCaseDelService;
    @Autowired
    private TGmCaseapiDelService tGmCaseapiDelService;

    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R list(@ModelAttribute TGmCaseDel tGmCaseDel, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmCaseDelService.queryListPage(tGmCaseDel, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmCaseDel tGmCaseDel = tGmCaseDelService.queryObject(id);
        tGmCaseDel.setTGmCaseapiDelList(tGmCaseapiDelService.listTGmCaseapiDel(id));
        return R.ok().put("tGmCaseDel", tGmCaseDel);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmCaseDel tGmCaseDel) {
        tGmCaseDelService.save(tGmCaseDel);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TGmCaseDel tGmCaseDel) {
        tGmCaseDelService.update(tGmCaseDel);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TGmCaseDel tGmCaseDel) {
        tGmCaseDelService.updateAvailable(tGmCaseDel);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGmCaseDel tGmCaseDel) {
        tGmCaseDelService.deleteBatch(tGmCaseDel);
        return R.ok();
    }
}
