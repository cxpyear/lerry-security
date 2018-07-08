package com.lerry.lerrysecurity.auth.web;

import com.lerry.lerrysecurity.auth.model.SysUserRole;
import com.lerry.lerrysecurity.auth.service.SysUserRoleService;
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
@RequestMapping("/sys/user/role")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping
    public SysUserRole add(@RequestBody SysUserRole sysUserRole) {
        sysUserRoleService.save(sysUserRole);
        return sysUserRole;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysUserRoleService.deleteById(id);
    }

    @PutMapping
    public SysUserRole update(@RequestBody SysUserRole sysUserRole) {
        sysUserRoleService.update(sysUserRole);
        return sysUserRole;
    }

    @GetMapping("/{id}")
    public SysUserRole detail(@PathVariable Long id) {
        return sysUserRoleService.findById(id);
    }

    @GetMapping
    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserRole> list = sysUserRoleService.findAll();
         return new PageInfo(list);
    }
}
