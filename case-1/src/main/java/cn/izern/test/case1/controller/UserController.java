package cn.izern.test.case1.controller;

import cn.izern.test.case1.entity.User;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 * @author: zern
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

  /**
   * put user
   * @param user
   * @return user
   */
  @PutMapping("/")
  public User putUser(@RequestBody User user){
    return user;
  }

  /**
   * add user
   * @param body
   * @return user
   */
  @PostMapping("/")
  public User postUser(@RequestBody String body){
    System.out.println(body);
    return new User();
  }

}
