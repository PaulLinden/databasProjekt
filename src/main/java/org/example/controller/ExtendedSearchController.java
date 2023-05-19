package org.example.controller;

import org.example.repository.ExtendedSearchRepository;

public class ExtendedSearchController {

    private final ExtendedSearchRepository extendedSearchRepository;

    public ExtendedSearchController(){
        this.extendedSearchRepository = new ExtendedSearchRepository();;
    }
    public void getUsersWithPosts(){
        extendedSearchRepository.getPostsAndComments();
    }

    public void getCommentsSub(){
        extendedSearchRepository.getCommentsWithSub();
    }

    public void getCommentsByDate(){
        extendedSearchRepository.getCommentsFromDate();
    }

    public void updatePassword(){
        extendedSearchRepository.changePassword();
    }

    public void changeVisable(String input){
        extendedSearchRepository.changeVisable(input);
    }
}
