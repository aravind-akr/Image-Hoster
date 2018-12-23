package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}/{title}/comments",method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Integer id, @RequestParam("comment") String comment, HttpSession session, Model model){

        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(id);
        LocalDate localDate=LocalDate.now();
        Comment newComment=new Comment(comment,localDate,user,image);
        List<Comment> commentList=new ArrayList<>();
        commentService.updateComment(newComment);

        return "redirect:/images/"+image.getId()+"/"+image.getTitle();

    }
}
