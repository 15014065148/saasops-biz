package com.eveb.saasops.modules.merchant.controller;

import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.merchant.entity.TRestriction;
import com.eveb.saasops.modules.merchant.service.TRestrictionService;
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
@RequestMapping("/bsapi/merchant/trestriction")
@Api(value = "TRestriction", description = "访问控制")
public class TRestrictionController extends AbstractController {
    @Autowired
    private TRestrictionService tRestrictionService;

    @GetMapping("/list")
    public R list(@ModelAttribute TRestriction tRestriction, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tRestrictionService.queryListPage(tRestriction, pageNo, pageSize));
    }

    @GetMapping("/listTRestriction")
    public R listTRestriction(@ModelAttribute TRestriction tRestriction, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tRestrictionService.queryListPage(tRestriction, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TRestriction tRestriction = tRestrictionService.queryObject(id);
        return R.ok().put("tRestriction", tRestriction);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TRestriction tRestriction) {
        tRestrictionService.save(tRestriction);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TRestriction tRestriction) {
        tRestrictionService.update(tRestriction);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TRestriction tRestriction) {
        tRestrictionService.updateAvailable(tRestriction);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TRestriction tRestriction) {
        tRestrictionService.deleteBatch(tRestriction);
        return R.ok();
    }
}
