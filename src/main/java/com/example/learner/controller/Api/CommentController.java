package com.example.learner.controller.Api;

import com.example.learner.bean.Comment;
import com.example.learner.service.impl.CommentServiceImpl;
import com.example.learner.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.example.learner.util.LqNiceUtil.hasErrors;

/**
 * Created by LiQian_Nice on 2018/4/4
 */
@Controller
@Api(value="课程章节评论管理接口",tags={"课程章节管理Api"})//接口简要标注，对中文的支持不太好
@RequestMapping(value = "/api/comments")//接口基本路径
public class CommentController {

    @Resource
    private CommentServiceImpl commentService;

    @GetMapping("/{chapterId}")
    @ResponseBody
    @ApiOperation(value = "根据课程章节id查找该章节下的所有评论",httpMethod = "GET",response = Comment.class)
    public Object findCommentByChapterId(@PathVariable("chapterId") Integer chapterId){
        return ResultUtil.success(commentService.findCommentByChapterId(chapterId));
    }

    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "根据课程章节id添加一个评论",httpMethod = "POST",response = Comment.class)
    public Object add(@ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
          /*  //如果验证添加的表单数据错误，返回失败Json
            StringBuffer sb = new StringBuffer();
            for(ObjectError objectError : bindingResult.getAllErrors()){
                sb.append(((FieldError)objectError).getField() +" : ").append(objectError.getDefaultMessage());
            }*/
            return ResultUtil.error(1, hasErrors(bindingResult));
        }else {
            commentService.add(comment);
            return ResultUtil.success();
        }
    }

}
