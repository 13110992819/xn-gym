package com.std.forum.api.converter;

import com.std.forum.common.DateUtil;
import com.std.forum.domain.Post;
import com.std.forum.dto.req.XN610050Req;
import com.std.forum.dto.req.XN610053Req;
import com.std.forum.dto.req.XN610054Req;
import com.std.forum.dto.req.XN610055Req;
import com.std.forum.dto.req.XN610070Req;
import com.std.forum.dto.req.XN610071Req;

public class PostConverter {

    // 发布帖子
    public static Post converter(XN610050Req req) {
        Post result = new Post();
        result.setTitle(req.getTitle());
        result.setContent(req.getContent());
        result.setPic(req.getPic());
        result.setPlateCode(req.getPlateCode());
        result.setPublisher(req.getPublisher());
        return result;
    }

    // 设置帖子
    public static Post converter(XN610053Req req) {
        Post result = new Post();
        result.setCode(req.getCode());
        result.setIsHeadline(req.getIsHeadline());
        result.setIsTop(req.getIsTop());
        result.setIsEssence(req.getIsEssence());
        return result;
    }

    // 举报帖子
    public static Post converter(XN610054Req req) {
        Post result = new Post();
        result.setCode(req.getCode());
        result.setReporter(req.getReporter());
        result.setReportNote(req.getReportNote());
        return result;
    }

    // 审核帖子
    public static Post converter(XN610055Req req) {
        Post result = new Post();
        result.setCode(req.getCode());
        result.setStatus(req.getStatus());
        result.setApprover(req.getApprover());
        result.setApproveNote(req.getApproveNote());
        return result;
    }

    // 分页查询帖子
    public static Post converter(XN610070Req req) {
        Post result = new Post();
        result.setUserId(req.getUserId());
        result.setTitle(req.getTitle());
        result.setPublisher(req.getPublisher());
        result.setStatus(req.getStatus());
        result.setIsReport(req.getIsReport());
        result.setIsHeadline(req.getIsHeadline());
        result.setIsTop(req.getIsTop());
        result.setIsEssence(req.getIsEssence());
        result.setPlateCode(req.getPlateCode());
        result.setPublishDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        result.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        return result;
    }

    // 列表查询帖子
    public static Post converter(XN610071Req req) {
        Post result = new Post();
        result.setTitle(req.getTitle());
        result.setPublisher(req.getPublisher());
        result.setStatus(req.getStatus());
        result.setIsReport(req.getIsReport());
        result.setIsHeadline(req.getIsHeadline());
        result.setIsTop(req.getIsTop());
        result.setIsEssence(req.getIsEssence());
        result.setPlateCode(req.getPlateCode());
        result.setPublishDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        result.setPublishDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        return result;
    }
}
