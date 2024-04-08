package com.example.redis_demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis_demo.demo.entity.MemberEntity;
import com.example.redis_demo.demo.service.MemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/getAll")
    public List<MemberEntity> getAllMember() {
        return memberService.findAllMember();
    }

    @GetMapping("/getById")
    public MemberEntity getMember(@RequestParam Integer memberId) {
        return memberService.findById(memberId);
    }

    @PostMapping("/clearAll")
    public void clearAllUserCache() {
        memberService.clearAllUserCache();
    }

    @GetMapping("/clearById")
    public void clear(@RequestParam Integer memberId) {
        memberService.clear(memberId);
    }
}
