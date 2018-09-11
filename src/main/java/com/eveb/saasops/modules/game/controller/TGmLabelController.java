package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmLabel;
import com.eveb.saasops.modules.game.service.TGmLabelService;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bsapi/game/tgmlabel")
@Api(value = "TGmLabel", description = "")
public class TGmLabelController extends AbstractController {
    @Autowired
    private TGmLabelService tGmLabelService;

    @GetMapping("/list")
    public R list(@ModelAttribute TGmLabel tGmLabel, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmLabelService.queryListPage(tGmLabel, pageNo, pageSize));
    }

    @GetMapping("/listLabel")
    public R listLabel() {
        return R.ok().put("page", tGmLabelService.queryList());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmLabel tGmLabel = tGmLabelService.queryObject(id);
        return R.ok().put("tGmLabel", tGmLabel);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmLabel tGmLabel) {
        tGmLabelService.save(tGmLabel);
        List<TGmLabel> tGmLabelList = tGmLabelService.queryList();
        List<Integer> listBankSort = tGmLabelList.stream().map(ls -> ls.getId()).collect(Collectors.toList());
        int maxId = Collections.max(listBankSort);
        return R.ok().put(maxId);
    }


    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@RequestBody TGmLabel tGmLabel) {
        Assert.isNull(tGmLabel, "不能为空");
        tGmLabelService.deleteBatch(tGmLabel);
        return R.ok();
    }
}
