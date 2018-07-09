package com.lerry.lerrysecurity.auth.web;

import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.auth.service.SysUserService;
import com.lerry.lerrysecurity.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lerry.lerrysecurity.common.util.EncryptProvider;
import com.lerry.lerrysecurity.common.util.StringUtil;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping
    public SysUser add(@RequestBody SysUser sysUser) {
        if(sysUser != null && StringUtil.isNotEmpty(sysUser.getPassword())){
            //密码加密存储
            sysUser.setPassword(EncryptProvider.encode(sysUser.getPassword()));
        }
        sysUserService.save(sysUser);
        return sysUser;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysUserService.deleteById(id);
    }

    @PutMapping
    public SysUser update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return sysUser;
    }

    @GetMapping("/{id}")
    public SysUser detail(@PathVariable Long id) {
        return sysUserService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
         return new PageInfo(list);
    }
}
