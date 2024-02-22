package ingyu.studyproject.controller;

import ingyu.studyproject.dto.JoinDTO;
import ingyu.studyproject.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }
    @PostMapping("/joinSuccess")
    public String joinSuccess(JoinDTO joinDTO){
        joinService.save(joinDTO);
        return "redirect:/login";
    }
}
