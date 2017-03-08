package com.lu.springboot.controller;

import com.lu.springboot.config.WebConfig;
import com.lu.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <b>描述信息</b>
 * <b>Description:</b>
 *
 * <b>Author:</b> Luyongjia
 * <b>Date:</b> 2017年03月08日  11:15
 * <b>Copyright:</b> Copyright ©2016 tempus.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                             Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年03月08日  11:15   Luyongjia
 *         new file.
 * </pre>
 */
@RestController
@RequestMapping("/")
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message;

    @Autowired
    public WebConfig webConfig;

    @RequestMapping("{view_name}")
    public ModelAndView modelAndView(@PathVariable("view_name") String view_name, ModelAndView modelAndView) {
        modelAndView.setViewName(view_name);
        return modelAndView;
    }

    @RequestMapping("index")
    public ResponseEntity welcome(Map<String, Object> model) {
        return ResponseEntity.ok(message);
    }

    @RequestMapping("auto-insert")
    public ResponseEntity AutoInsert(User user) {
        return ResponseEntity.ok(user);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object handleMyRuntimeException(Exception exception) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("exception", exception.getMessage());
        return objectObjectHashMap;
    }
}
