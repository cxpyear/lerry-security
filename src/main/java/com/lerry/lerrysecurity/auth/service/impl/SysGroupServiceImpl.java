package com.lerry.lerrysecurity.auth.service.impl;

import com.lerry.lerrysecurity.auth.dao.SysGroupMapper;
import com.lerry.lerrysecurity.auth.model.SysGroup;
import com.lerry.lerrysecurity.auth.service.SysGroupService;
import com.lerry.lerrysecurity.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
@Service
@Transactional
public class SysGroupServiceImpl extends AbstractService<SysGroup> implements SysGroupService {

    @Resource
    private SysGroupMapper sysGroupMapper;

}
