package com.xingniu.zoon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: IndexController
 * @Reason: TODO ADD REASON(可选)
 * @date: 2018年11月13日 14:20
 * @company:星牛旅游
 * @since JDK 1.8
 */
@RestController
public class IndexController {


    @RequestMapping("/index")
    public String index() {


        return "首页";
    }
}
