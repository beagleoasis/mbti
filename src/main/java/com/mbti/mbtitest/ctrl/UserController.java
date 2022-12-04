package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;




}
