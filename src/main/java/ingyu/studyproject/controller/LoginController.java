package ingyu.studyproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {
    @GetMapping("/login")
    public String loginP(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!id.equals("anonymousUser")){
            log.info("[logout] username: {}",id);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        else{
            log.info("[logout] there is no login user");
        }
        return "redirect:/";
    }

//    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}
