package org.example.controller;

import org.example.model.Media;
import org.example.model.Posts;
import org.example.repository.MediaRepository;

import java.util.Objects;

public class MediaController {

    private final MediaRepository mediaRepository;

    public MediaController(){
        this.mediaRepository = new MediaRepository();;
    }

    public void addImage(String url, String filetype, int owner_id, String galleryAdd){

        boolean addToGallery = false;

        if (Objects.equals(galleryAdd, "y")){
            addToGallery = true;
        }

        Media media = new Media();
        Posts post = new Posts();
        media.setUrl(url);
        media.setFiltype(filetype);
        post.setPostId(owner_id);

        mediaRepository.addImageToPost(media, post, addToGallery);
    }
}
