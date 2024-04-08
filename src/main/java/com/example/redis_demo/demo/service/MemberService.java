package com.example.redis_demo.demo.service;

import java.util.List;

import com.example.redis_demo.demo.entity.MemberEntity;

public interface MemberService {
    List<MemberEntity> findAllMember();
    MemberEntity findById(Integer memberId);
    void clearAllUserCache();
    void clear(Integer memberId);
}
