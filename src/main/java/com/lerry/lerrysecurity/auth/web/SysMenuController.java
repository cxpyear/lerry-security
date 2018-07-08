package com.lerry.lerrysecurity.auth.web;

import com.lerry.lerrysecurity.auth.model.SysMenu;
import com.lerry.lerrysecurity.auth.service.SysMenuService;
import com.lerry.lerrysecurity.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
@ResponseResult
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping
    public SysMenu add(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return sysMenu;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysMenuService.deleteById(id);
    }

    @PutMapping
    public SysMenu update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return sysMenu;
    }

    @GetMapping("/{id}")
    public SysMenu detail(@PathVariable Long id) {
        return sysMenuService.findById(id);
    }

    @GetMapping
    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysMenu> list = sysMenuService.findAll();
         return new PageInfo(list);
    }
}
