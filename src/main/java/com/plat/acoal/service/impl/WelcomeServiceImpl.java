package com.plat.acoal.service.impl;

import com.plat.acoal.dao.WelcomeMapper;
import com.plat.acoal.model.WelcomeInfo;
import com.plat.acoal.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Autowired
    private WelcomeMapper wm;
    @Override
    public List<WelcomeInfo> selectwelcome(Map<String, String> condition) {
        return wm.selectwelcome(condition);
    }

    @Override
    public int addWord(Map<String, String> condition) {
        return wm.addWord(condition);
    }

    @Override
    public int updateword(Map<String, String> condition) {
        return wm.updateword(condition);
    }

    @Override
    public int deleteWord(Map<String, String> condition) {
        return wm.deleteWord(condition);
    }
}
