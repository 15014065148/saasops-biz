package com.eveb.saasops.modules.merchant.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.merchant.dao.TCpCompanyMapper;
import com.eveb.saasops.modules.merchant.entity.TCpCompany;
import com.eveb.saasops.modules.merchant.service.TCpCompanyService;
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
@RequestMapping("/bsapi/merchant/tcpcompany")
@Api(value = "TCpCompany", description = "商户列表")
public class TCpCompanyController extends AbstractController {
    @Autowired
    private TCpCompanyService tCpCompanyService;
    @Autowired
    private TCpCompanyMapper tCpCompanyMapper;

    @GetMapping("/list")
    public R list(@ModelAttribute TCpCompany tCpCompany, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tCpCompanyService.queryListPage(tCpCompany, pageNo, pageSize));
    }

    @GetMapping("/listCompany")
    public R listCompany() {
        return R.ok().put(tCpCompanyMapper.selectAll());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TCpCompany tCpCompany = tCpCompanyService.queryObject(id);
        return R.ok().put("tCpCompany", tCpCompany);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TCpCompany tCpCompany) {
        tCpCompanyService.save(tCpCompany);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R update(@RequestBody TCpCompany tCpCompany) {
        tCpCompanyService.update(tCpCompany);
        return R.ok();
    }

    @PostMapping("/updateAvailable")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R updateAvailable(@RequestBody TCpCompany tCpCompany) {
        tCpCompanyService.updateAvailable(tCpCompany);
        return R.ok();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TCpCompany tCpCompany) {
        tCpCompanyService.deleteBatch(tCpCompany);
        return R.ok();
    }
}
