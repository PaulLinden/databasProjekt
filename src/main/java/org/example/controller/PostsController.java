package org.example.controller;

import org.example.model.Posts;
import org.example.model.User;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostsController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public PostsController() {
        this.userRepository = new UserRepository();
        this.postRepository = new PostRepository();
    }
    public void createPost(int id, String postText) throws SQLException {
        User user = new User();
        Posts posts = new Posts();

        user.setId(id);
        posts.setPostText(postText);

        if (userRepository.userExists(user)){
            postRepository.createPost(posts,user);
        }
    }

    public void getAllPosts(){
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> posts = new ArrayList<>();
        postRepository.getAllPosts(ids,posts);
        User user = new User();
        for (int i = 0; i<ids.size(); i++ ) {
            user.setId(ids.get(i));

            System.out.println(userRepository.getSpecificUser(user)+": " + posts.get(i));
        }
    }

    public void getAllOfflinePosts(){
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> posts = new ArrayList<>();
        postRepository.getAllPosts(ids,posts);
        User user = new User();

        for (int i = 0; i<ids.size(); i++ ) {
               user.setId(ids.get(i));
               System.out.println(userRepository.getSpecificUser(user) + ": " + posts.get(i));
           }
        }

    public void postExists(int id){
        Posts post = new Posts();
        post.setPostId(id);
        try {
            boolean exists = postRepository.postExists(post);
            if (exists) {
                System.out.println("User with ID " + id + " exists.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletePost(int postId){
        Posts post = new Posts();
        post.setPostId(postId);
        postRepository.deletePost(post);
    }
}
