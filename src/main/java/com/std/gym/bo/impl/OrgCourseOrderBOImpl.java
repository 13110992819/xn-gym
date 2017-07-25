package com.std.gym.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.gym.bo.IOrgCourseOrderBO;
import com.std.gym.bo.base.PaginableBOImpl;
import com.std.gym.dao.IOrgCourseOrderDAO;
import com.std.gym.domain.OrgCourseOrder;
import com.std.gym.enums.EOrgCourseOrderStatus;
import com.std.gym.exception.BizException;

@Component
public class OrgCourseOrderBOImpl extends PaginableBOImpl<OrgCourseOrder>
        implements IOrgCourseOrderBO {

    @Autowired
    private IOrgCourseOrderDAO orgCourseOrderDAO;

    @Override
    public boolean isOrgCourseOrderExist(String code) {
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setCode(code);
        if (orgCourseOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveOrgCourseOrder(OrgCourseOrder data) {
        orgCourseOrderDAO.insert(data);
    }

    @Override
    public void payGroup(OrgCourseOrder order, String payGroup) {
        order.setPayGroup(payGroup);
        orgCourseOrderDAO.payGroup(order);
    }

    @Override
    public void removeOrgCourseOrder(String code) {
        if (StringUtils.isNotBlank(code)) {
            OrgCourseOrder data = new OrgCourseOrder();
            data.setCode(code);
            orgCourseOrderDAO.delete(data);
        }
    }

    @Override
    public List<OrgCourseOrder> queryOrgCourseOrderList(OrgCourseOrder condition) {
        return orgCourseOrderDAO.selectList(condition);
    }

    @Override
    public OrgCourseOrder getOrgCourseOrder(String code) {
        OrgCourseOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            OrgCourseOrder condition = new OrgCourseOrder();
            condition.setCode(code);
            data = orgCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public OrgCourseOrder getOrderPayGroup(String payGroup) {
        OrgCourseOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            OrgCourseOrder condition = new OrgCourseOrder();
            condition.setPayGroup(payGroup);
            data = orgCourseOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "支付组号不存在");
            }
        }
        return data;
    }

    @Override
    public void paySuccess(OrgCourseOrder order, String payCode, Long amount,
            Long penalty, String payType) {
        order.setStatus(EOrgCourseOrderStatus.PAYSUCCESS.getCode());
        order.setPayType(payType);
        order.setPayCode(payCode);
        order.setPayAmount(amount);
        order.setPenalty(penalty);
        order.setPayDatetime(new Date());
        orgCourseOrderDAO.paySuccess(order);
    }

    @Override
    public void userCancel(OrgCourseOrder order, String applyUser) {
        order.setStatus(EOrgCourseOrderStatus.USER_CANCEL.getCode());
        order.setApplyUser(applyUser);
        order.setApplyDatetime(new Date());
        orgCourseOrderDAO.userCancel(order);
    }

    @Override
    public void platCancel(OrgCourseOrder order, String updater, String remark) {
        order.setStatus(EOrgCourseOrderStatus.PLAT_CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orgCourseOrderDAO.platCancel(order);
    }

    @Override
    public void applyRefund(OrgCourseOrder order, String applyUser,
            String applyNote) {
        order.setStatus(EOrgCourseOrderStatus.USER_CANCEL.getCode());
        order.setApplyUser(applyUser);
        order.setApplyDatetime(new Date());
        order.setApplyNote(applyNote);
        orgCourseOrderDAO.applyRefund(order);
    }

    @Override
    public void approveRefund(OrgCourseOrder order,
            EOrgCourseOrderStatus status, String updater, String remark) {
        order.setStatus(status.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orgCourseOrderDAO.approveRefund(order);
    }

    @Override
    public void finishOrder(OrgCourseOrder order) {
        order.setStatus(EOrgCourseOrderStatus.END.getCode());
        orgCourseOrderDAO.finishOrder(order);
    }

    @Override
    public List<OrgCourseOrder> queryOrgCourseOrderList(String orgCourseCode,
            List<String> statusList) {
        OrgCourseOrder condition = new OrgCourseOrder();
        condition.setOrgCourseCode(orgCourseCode);
        condition.setStatusList(statusList);
        return orgCourseOrderDAO.selectList(condition);
    }

    @Override
    public void beginOrgCourseOrder(OrgCourseOrder order) {
        order.setStatus(EOrgCourseOrderStatus.BEGIN.getCode());
        orgCourseOrderDAO.finishOrder(order);
    }

    @Override
    public void toComment(OrgCourseOrder order) {
        order.setStatus(EOrgCourseOrderStatus.TO_COMMENT.getCode());
        orgCourseOrderDAO.finishOrder(order);
    }

}
