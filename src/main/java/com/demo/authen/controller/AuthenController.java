package com.demo.authen.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenController {

    /**
     * 这个资源鉴权成功, 成功返回
     * @return
     */
    @PreAuthorize("hasAnyAuthority('TEST_PERMISSION')")
    @GetMapping("/authen")
    public Object authen() {
        return "authen pass";
    }

    /**
     * 这个资源需求的权限是未曾配置的, 鉴权失败
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPER_PERMISSION')")
    @GetMapping("/superauthen")
    public Object superauthen() {
        return "authen pass";
    }

    /**
     * 由于ResourceServerConfig的配置, 这个资源可以不用鉴权
     * @return
     */
    @GetMapping("/ignore")
    public Object ignoreDemo() {
        return "no authen";
    }
}
