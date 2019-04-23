package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Ya Man!!!";
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> list(){
        return this.userService.ListAll();
    }

    @GetMapping("/finduser/{id}")
    @ResponseBody
    public User find(@PathVariable String id){
        return this.userService.FindUser(Long.parseLong(id));
    }
/*
    @PostMapping("/adduser")
    @ResponseBody
    public User add(@RequestBody User user){
        return this.userService.AddUser(user);
    }
*/

    @PostMapping("/adduser")
    @ResponseBody
    public ResponseFormat add(@RequestBody User user){
        return new ResponseFormat(ResponseType.USER_ADD,this.userService.AddUser(user));
    }

/*
    @PutMapping("/updateuser/{id}")
    @ResponseBody
    public User update(@PathVariable String id ,@RequestBody User user){
        return this.userService.UpdateUser(Long.parseLong(id),user);
    }
*/

    @PutMapping("/updateuser/{id}")
    @ResponseBody
    public ResponseFormat update(@PathVariable String id ,@RequestBody User user){
        return new ResponseFormat(ResponseType.USER_UPDATE,this.userService.UpdateUser(Long.parseLong(id),user));
    }

/*
    @DeleteMapping("/deleteuser/{id}")
    @ResponseBody
    public boolean delete(@PathVariable String id) {
        return this.userService.DeleteUser(Long.parseLong(id));
    }
*/

    @DeleteMapping("/deleteuser/{id}")
    @ResponseBody
    public ResponseFormat delete(@PathVariable String id) {
        return new ResponseFormat(ResponseType.USER_DELETE,this.userService.DeleteUser(Long.parseLong(id)));
    }
}
