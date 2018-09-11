package com.eveb.saasops.modules.operate.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.operate.entity.TOpActtmpl;
import com.eveb.saasops.modules.operate.service.TOpActtmplService;
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

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;

@RestController
@RequestMapping("/bsapi/merchant/topacttmpl")
@Api(value = "TOpActtmpl", description = "活动分类")
public class TOpActtmplController extends AbstractController {
    @Autowired
    private TOpActtmplService tOpActtmplService;

    @GetMapping("/list")
    public R list(@ModelAttribute TOpActtmpl tOpActtmpl, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tOpActtmplService.queryListPage(tOpActtmpl, pageNo, pageSize));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TOpActtmpl tOpActtmpl = tOpActtmplService.queryObject(id);
        return R.ok().put("tOpActtmpl", tOpActtmpl);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TOpActtmpl tOpActtmpl) {
        tOpActtmplService.save(tOpActtmpl);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TOpActtmpl tOpActtmpl) {
        tOpActtmplService.update(tOpActtmpl);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改按钮", notes = "修改按钮")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TOpActtmpl tOpActtmpl) {
        tOpActtmplService.updateAvailable(tOpActtmpl);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TOpActtmpl tOpActtmpl) {
        tOpActtmplService.deleteBatch(tOpActtmpl);
        return R.ok();
    }
}
