package org.example.controller;

import org.example.model.Posts;
import org.example.model.User;
import org.example.repository.CommentsRepository;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentsController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    public CommentsController() {
        this.userRepository = new UserRepository();
        this.postRepository = new PostRepository();
        this.commentsRepository = new CommentsRepository();
    }

    public void getAllComments(){
        ArrayList<Integer> userIds = new ArrayList<>();
        ArrayList<Integer> postIds = new ArrayList<>();
        ArrayList<String> comments = new ArrayList<>();
        commentsRepository.getAllComments(userIds,postIds,comments);

        User user = new User();
        Posts post = new Posts();
        for (int i = 0; i<userIds.size(); i++ ) {
            user.setId(userIds.get(i));
            post.setPostId(postIds.get(i));
            System.out.println(userRepository.getSpecificUser(user)+": "+postRepository.getSpecificPost(post));
            System.out.println("comment:");
            System.out.println(userRepository.getSpecificUser(user)+": " + comments.get(i));
            System.out.println("----------------------------------");
        }
    }
    public void createComment(int userId, int postId, String text) throws SQLException {
        User user = new User();
        Posts post = new Posts();
        user.setId(userId);
        post.setPostId(postId);

        boolean userExist = userRepository.userExists(user);
        boolean postExist =  postRepository.postExists(post);
       if (userExist && postExist){
           commentsRepository.createComment(user, post, text);
       }


    }
}
