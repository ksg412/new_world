package com.sg.source.function.test.testController;

import com.sg.source.function.test.testService.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public String test() throws Exception{

    return "function/test/test01";
    }

    @ResponseBody
    @RequestMapping(value = "/test/ajax", method = RequestMethod.POST)
    public Object testAjax(@RequestBody Map<String,Object> param, ModelMap model) throws Exception{

        Map returnMap = new HashMap();
        returnMap.put("aaa", "aaa");
        return returnMap;
    }
}
