package com.sg.source.function.test.testController;

import com.sg.source.common.util.CommonUtil;
import com.sg.source.function.test.testService.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test01")
        public String test01() throws Exception{
        return "function/test/test01";
    }

    @RequestMapping("/test02")
    public String test02() throws Exception{
        return "function/test/test02";
    }

    @RequestMapping("/test03")
    public String test03() throws Exception{
        return "function/test/test03";
    }

    @RequestMapping("/test04")
    public String test04() throws Exception{
        return "function/test/test04";
    }

    @RequestMapping("/test05")
    public String test05() throws Exception{
        return "function/test/test05";
    }

    @RequestMapping("/test06")
    public String test06() throws Exception{
        return "function/test/test06";
    }

    @ResponseBody
    @RequestMapping(value = "/test/ajax", method = RequestMethod.POST)
    public Object testAjax(@RequestBody Map<String,Object> param, ModelMap model) throws Exception{

        Map returnMap = new HashMap();
        returnMap.put("aaa", "aaa");
        returnMap.put("bbb", "bbb");
        returnMap.put("ccc", "ccc");
        returnMap.put("ddd", "ddd");
        returnMap.put("eee", "eee");
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("/test/datatables")
    public Object testDatatables(@RequestBody Map<String,Object> param) throws Exception{

        return CommonUtil.datatableReturn(
                (Integer) param.get("draw"),
                testService.selectTbUserList(param),
                testService.selectTbUserCnt(param)
        );
    }

    @RequestMapping("/test/upload")
    public String testUpload() throws Exception{


        return null;
    }
}
