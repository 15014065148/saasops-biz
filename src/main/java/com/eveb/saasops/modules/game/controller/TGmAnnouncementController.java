package com.eveb.saasops.modules.game.controller;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.controller.AbstractController;
import com.eveb.saasops.modules.game.entity.TGmAnnouncement;
import com.eveb.saasops.modules.game.service.TGmAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bsapi/game/tgmannouncement")
@Api(value = "TGmAnnouncement", description = "平台维护")
public class TGmAnnouncementController extends AbstractController {
    @Autowired
    private TGmAnnouncementService tGmAnnouncementService;

    @GetMapping("/list")
    public R list(@ModelAttribute TGmAnnouncement tGmAnnouncement, @RequestParam("pageNo") @NotNull Integer pageNo, @RequestParam("pageSize") @NotNull Integer pageSize) {
        return R.ok().put("page", tGmAnnouncementService.queryListPage(tGmAnnouncement, pageNo, pageSize));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R save(@RequestBody TGmAnnouncement tGmAnnouncement) {
        tGmAnnouncementService.saveAnnouncement(tGmAnnouncement);
        return R.ok();
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R info(@PathVariable("id") Integer id) {
        TGmAnnouncement tGmAnnouncement = tGmAnnouncementService.queryObject(id);
        return R.ok().put("tGmAnnouncement", tGmAnnouncement);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token头部，随便填数字", required = true, dataType = "Integer", paramType = "header")})
    public R delete(@PathVariable("id") Integer id) {
        tGmAnnouncementService.deleteById(id);
        return R.ok();
    }
}
