package com.std.gym.api.impl;

import com.std.gym.ao.IOrgCourseOrderAO;
import com.std.gym.api.AProcessor;
import com.std.gym.common.JsonUtil;
import com.std.gym.core.StringValidater;
import com.std.gym.dto.req.XN622072Req;
import com.std.gym.dto.res.BooleanRes;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;
import com.std.gym.spring.SpringContextHolder;

/**
 * 用户取消订单
 * @author: asus 
 * @since: 2017年7月17日 下午9:07:40 
 * @history:
 */
public class XN622072 extends AProcessor {
    private IOrgCourseOrderAO orgCourseOrderAO = SpringContextHolder
        .getBean(IOrgCourseOrderAO.class);

    private XN622072Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orgCourseOrderAO.userCancel(req.getOrderCode(), req.getApplyUser());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN622072Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getApplyUser());
    }

}
