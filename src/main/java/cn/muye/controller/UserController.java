package cn.muye.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;


/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    /*@Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public AjaxResult getUserInfo(@RequestParam("id")Integer id) {
        try {
            User user = userService.getUserInfoById(id);
            if(user!=null){
                return AjaxResult.success(user);
            }else {
                return AjaxResult.failed("无法获取到此用户");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统出现异常");
        }
    }

    @RequestMapping("/listUsers")
    @ResponseBody
    public AjaxResult listUsers() {
        try {
            List<User> users = userService.listUsers();
            return AjaxResult.success(users);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统出现异常");
        }
    }

    @RequestMapping("/listPageUsers")
    @ResponseBody
    public AjaxResult listPageUsers(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                    @RequestParam(value = "pageSize",required = false, defaultValue = "5")Integer pageSize) {
        try {
            PageHelper.startPage(page, pageSize);
            List<User> users = userService.listUsers();
            PageInfo<User> pageUsers = new PageInfo(users);
            return AjaxResult.success(pageUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统出现异常");
        }
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public AjaxResult saveUser(User user) {
        try {
            userService.saveUser(user);
            return AjaxResult.success(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.failed("系统出现异常");
        }
    }*/
}
