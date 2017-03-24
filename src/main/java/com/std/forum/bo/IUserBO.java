/**
 * @Title IUserBO.java 
 * @Package com.std.forum.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午8:09:47 
 * @version V1.0   
 */
package com.std.forum.bo;

import com.std.forum.bo.base.IPaginableBO;
import com.std.forum.domain.User;
import com.std.forum.dto.res.XN001400Res;

/** 
 * @author: xieyj 
 * @since: 2016年8月29日 下午8:09:47 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {

    public XN001400Res getRemoteUser(String userId);

    /**
     * 加减积分
     * @param userId
     * @param direction
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月12日 上午8:13:47 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, Long amount,
            String remark, String refNo);

    /**
     * 用户间单账户划转
     * @param fromUser
     * @param toUser
     * @param amount
     * @param remark
     * @param refNo 
     * @create: 2016年10月24日 下午11:14:29 xieyj
     * @history:
     */
    public void doTransferAdd(String fromUser, String toUser, Long amount,
            String remark, String refNo);

    /**
     * 活动加减积分
     * @param userId
     * @param direction
     * @param ruleType
     * @param refNo 
     * @create: 2016年10月23日 下午8:54:46 xieyj
     * @history:
     */
    public void doTransfer(String userId, String direction, String ruleType,
            String refNo);
}
