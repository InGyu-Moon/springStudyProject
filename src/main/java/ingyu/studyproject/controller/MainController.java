package ingyu.studyproject.controller;

import ingyu.studyproject.service.CustomUserDetailsService;
import ingyu.studyproject.service.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Collection;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final CustomUserDetailsService customUserDetailsService;
    private final VisitService visitService;
    @GetMapping("/")
    public String mainP(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        


        model.addAttribute("id",id);
        model.addAttribute("role",role);
        return "main";
    }

    @GetMapping("/visit")
    public String pageViews(HttpServletRequest request,Model model){
        String ipAddr = request.getRemoteAddr();
        log.info("ip 주소 : {}",ipAddr);

        visitService.newVisitor(ipAddr);
        Long totalVisitor = visitService.getTotalVisitor();

        model.addAttribute("total",totalVisitor);
        return "visit";
    }



}
