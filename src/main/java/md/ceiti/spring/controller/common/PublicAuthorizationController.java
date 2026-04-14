package md.ceiti.spring.controller.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;
import md.ceiti.spring.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicAuthorizationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public PublicAuthorizationController(UserService userService,
                                         PasswordEncoder passwordEncoder,
                                         AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) String error) {
        if(error!=null){
            model.addAttribute("isAuthentificationFailed", true);
        }
        return "public/authorization/login-page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "public/authorization/registration-page";
    }

    @PostMapping("/registration")
    public String createUserAccount(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    HttpServletRequest request,          // ← добавляем
                                    HttpServletResponse response) {      // ← добавляем

        String encodedPassword = passwordEncoder.encode(password);
        userService.save(new User(name, email, encodedPassword, UserRole.USER));

        forceAutoLogin(email, password, request, response);

        return "redirect:/account";
    }

    private void forceAutoLogin(String email, String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        SecurityContextRepository repository =
                new HttpSessionSecurityContextRepository();
        repository.saveContext(context, request, response);
    }
}