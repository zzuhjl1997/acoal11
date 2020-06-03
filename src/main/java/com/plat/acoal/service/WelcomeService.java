package com.plat.acoal.service;

import com.plat.acoal.model.WelcomeInfo;

import java.util.List;
import java.util.Map;

public interface WelcomeService {
    List<WelcomeInfo> selectwelcome(Map<String, String> condition);

    int addWord(Map<String, String> condition);

    int updateword(Map<String, String> condition);

    int deleteWord(Map<String, String> condition);
}
