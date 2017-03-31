package com.std.forum.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.ISubsystemAO;
import com.std.forum.bo.ISubsystemBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Subsystem;
import com.std.forum.dto.req.XN610090Req;
import com.std.forum.dto.req.XN610091Req;
import com.std.forum.enums.EBelong;
import com.std.forum.enums.EBoolean;
import com.std.forum.exception.BizException;

@Service
public class SubsystemAOImpl implements ISubsystemAO {

    @Autowired
    private ISubsystemBO subsystemBO;

    @Override
    public void editSubsystemByGlobal(XN610090Req req) {
        Subsystem subsystem = subsystemBO.getSubsystem(req.getCode());
        if (EBelong.GLOBAL.getCode().equals(subsystem.getBelong())
                || EBelong.LOCAL.getCode().equals(subsystem.getBelong())) {
            subsystemBO.refreshSubsystem(req.getCode(), req.getName(),
                req.getUrl(), req.getPic(),
                StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                req.getCompanyCode(), req.getRemark());
        } else {
            throw new BizException("xn0000", "地方子系统配置，不可修改");
        }
    }

    @Override
    public void editSubsystemByLocal(XN610091Req req) {
        Subsystem subsystem = subsystemBO.getSubsystem(req.getCode());
        if (EBelong.LOCAL.getCode().equals(subsystem.getBelong())) {
            subsystemBO.saveSubsystem(req.getName(), req.getUrl(),
                req.getPic(), StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                subsystem.getCode(), req.getCompanyCode(), req.getRemark());
        } else if (EBelong.GLOBAL.getCode().equals(subsystem.getBelong())) {
            throw new BizException("xn0000", "总部子系统，地方不可修改");
        } else {
            subsystemBO.refreshSubsystem(req.getCode(), req.getName(),
                req.getUrl(), req.getPic(),
                StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                req.getCompanyCode(), req.getRemark());
        }
    }

    @Override
    public Paginable<Subsystem> querySubsystemPage(int start, int limit,
            Subsystem condition) {
        List<String> companyCodeList = new ArrayList<String>();
        if (!condition.getCompanyCode().equals(EBoolean.NO.getCode())) {
            condition.setBelong("NO_1");
            companyCodeList.add(condition.getCompanyCode());
            companyCodeList.add("0");
            condition.setCompanyCodeList(companyCodeList);
            condition.setCompanyCode("");
        } else {
            condition.setCompanyCode("");
        }
        return subsystemBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Subsystem> querySubsystemList(String companyCode) {
        List<Subsystem> resultSubsystem = new ArrayList<Subsystem>();
        List<Subsystem> localList = subsystemBO.querySubsystemList(companyCode);
        List<Subsystem> globalList = subsystemBO.querySubsystemList("0");
        for (Subsystem global : globalList) {
            if (CollectionUtils.isNotEmpty(localList)) {
                for (Subsystem local : localList) {
                    // 本地菜单的上级是全局的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.GLOBAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        resultSubsystem.add(global);
                    }
                    // 本地菜单的上级是可配的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.LOCAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        resultSubsystem.add(local);
                    }
                }
            }
        }
        for (Subsystem subsystem : resultSubsystem) {
            for (Subsystem global : globalList) {
                if (subsystem.getCode().equals(global.getCode())) {
                    globalList.remove(global);
                    break;
                }
            }
        }
        resultSubsystem.addAll(globalList);
        return resultSubsystem;
    }

    @Override
    public Subsystem getSubsystem(String code) {
        return subsystemBO.getSubsystem(code);
    }

}
