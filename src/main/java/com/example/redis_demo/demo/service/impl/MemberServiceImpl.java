package com.example.redis_demo.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis_demo.demo.entity.MemberEntity;
import com.example.redis_demo.demo.repository.MemberRepository;
import com.example.redis_demo.demo.service.MemberService;

@Service
@CacheConfig(cacheNames = "MemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 執行時,將清除value = getAllUsers cache
     * 【cacheNames = "userService"】
     * 也可指定清除的key 【@CacheEvict(value="abc")】
     */
    @Override
    @CacheEvict(value = "findAllMember", allEntries = true)
    public void clearAllUserCache() {

    }

    /**
     * cacheNames 與 value 定義一样，若設置了 value 的值，cacheNames 配置就無效。<br>
     * 使用 keyGenerator ，注意是否在config中定義好。
     */
    @Override
    @Cacheable(value = "findAllMember", keyGenerator = "wiselyKeyGenerator")
    public List<MemberEntity> findAllMember() {
        List<MemberEntity> membList = memberRepository.findAll();
        return membList;
    }

    /**
     * key ="#p0" 表示已第一個參數作為key
     */
    @Override
    @Cacheable(value = "redis", key = "#p0")
    public MemberEntity findById(Integer memberId) {
        Optional<MemberEntity> result = memberRepository.findById(memberId);
        return result.orElseThrow();
    }

    @Override
    @CacheEvict(value = "redis", key = "#p0")
    public void clear(Integer memberId) {

    }

}
