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
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{id}/{title}/comments",method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Integer id, HttpSession session, Model model){

        User user = (User) session.getAttribute("loggeduser");
        //Image image = imageService.getImage(imageId);
        model.addAttribute("user",user.getId());
        if(user.getId()!=id){
            Comment comment=commentService.getCommentByUserId(id);
            model.addAttribute("comment",comment);
            return "images/image";
        }
        return "redirect:/images";

    }
}
