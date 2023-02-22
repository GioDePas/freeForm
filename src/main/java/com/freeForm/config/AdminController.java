package com.freeForm.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String admin() {
        return "hello admin!";
    }
}
