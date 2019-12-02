package com.sand.mgt.auth;

import com.sand.biz.system.AuthService;
import com.sand.biz.system.UserService;
import com.sand.common.utils.ResponseEntity;
import com.sand.mgt.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public ModelAndView getLoginPage(){
        return new ModelAndView("auth/login");
    }

    @GetMapping("/logout")
    String logout(){
        ShiroUtils.logout();
        return "redirect:/login";
    }

    public String getIndexPage(Model model){
        return "index;";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(String username,
                                String password,
                                HttpServletRequest request){
        //todo add verify code
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //登录
            currentUser.login( new UsernamePasswordToken(username, password) );
            return ResponseEntity.ok();

        } catch ( UnknownAccountException uae ) {
            logger.warn("user is wrong");
            return ResponseEntity.error("user is wrong");

        } catch ( IncorrectCredentialsException ice ) {
            logger.warn("password is wrong");
            return ResponseEntity.error("password is wrong");

        } catch ( LockedAccountException lae ) {
            logger.warn("account is locked");
            return ResponseEntity.error("account is locked\"");

        } catch ( AuthenticationException ae ) {
            logger.warn("login error,ae:{}",ae.getMessage());
            return ResponseEntity.error("login error");
        }
    }
}
