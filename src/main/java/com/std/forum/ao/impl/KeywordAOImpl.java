package com.std.forum.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.IKeywordAO;
import com.std.forum.bo.IKeywordBO;
import com.std.forum.bo.base.Paginable;
import com.std.forum.domain.Keyword;
import com.std.forum.exception.BizException;

@Service
public class KeywordAOImpl implements IKeywordAO {

    @Autowired
    private IKeywordBO keywordBO;

    @Override
    public String addKeyword(String word) {
        Keyword data = new Keyword();
        data.setWord(word);
        return keywordBO.saveKeyword(data);
    }

    @Override
    public int editKeyword(String code, String word) {
        if (!keywordBO.isKeywordExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        Keyword data = new Keyword();
        data.setCode(code);
        data.setWord(word);
        return keywordBO.refreshKeyword(data);
    }

    @Override
    public int dropKeyword(String code) {
        if (!keywordBO.isKeywordExist(code)) {
            throw new BizException("xn0000", "该编号不存在");
        }
        return keywordBO.removeKeyword(code);
    }

    @Override
    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition) {
        return keywordBO.getPaginable(start, limit, condition);
    }

    @Override
    public Keyword getKeyword(String code) {
        return keywordBO.getKeyword(code);
    }
}
