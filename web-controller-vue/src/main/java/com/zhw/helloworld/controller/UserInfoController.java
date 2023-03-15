package com.zhw.helloworld.controller;

import com.zhw.helloworld.common.dto.PageResult;
import com.zhw.helloworld.common.dto.Result;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author zhw
 * @date 2022/6/13 23:24
 */
@RequestMapping("/user-info")
@RestController
public class UserInfoController {


    @PostMapping("/login")
    public LoginDto login(@RequestBody LoginDto login){

        //测试使用接口，不执行任何动作
        return login;
    }

    @Data
    public static class LoginDto{
        private String username;
        private String password;
    }

    @GetMapping("/studentList")
    public PageResult<StudentInfo> studentList(@RequestParam Integer pageNo,
                                               @RequestParam Integer pageSize){
        List<StudentInfo> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<pageSize; i++){
            StudentInfo student = new StudentInfo();
            student.setId(i+1);
            student.setName(UUID.randomUUID().toString());
            student.setAge(i + 15);
            student.setSex(i%2);
            student.setScore(random.nextInt()%100);
            student.setClassInfo("班级-" + random.nextInt()%3);

            list.add(student);
        }
        return PageResult.createResult(pageNo, pageSize, 100, list);
    }

    @Data
    public static class StudentInfo{
        private Integer id;
        private String name;
        private Integer age;
        private Integer sex;    //0女，1男
        private Integer score;
        private String classInfo;
    }

    @DeleteMapping("/deleteStudent")
    public Result<Void> deleteStudent(@RequestParam Integer id){
       return Result.Success.delete();
    }
}
