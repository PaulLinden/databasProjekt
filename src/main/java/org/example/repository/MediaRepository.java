package org.example.repository;

import org.example.database.InitDatabase;
import org.example.model.Media;
import org.example.model.Posts;

import java.sql.*;

public class MediaRepository {

    public void addImageToPost(Media media, Posts post, boolean addToGallery) {
        String mediaQuery = "INSERT INTO media (url, filetype) VALUES (?, ?)";
        String updatePostQuery = "UPDATE posts SET media_id = ? WHERE id = ?";
        String gallerySql = "INSERT INTO gallery (file_id, gallery_name) SELECT ?, name FROM users WHERE id = (SELECT user_id FROM posts WHERE id = ?)";

        try (Connection connection = InitDatabase.getInstance().getConnection();
             PreparedStatement mediaStatement = connection.prepareStatement(mediaQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement updateStatement = connection.prepareStatement(updatePostQuery);
             PreparedStatement galleryStatement = connection.prepareStatement(gallerySql)) {

            connection.setAutoCommit(false);

            //create row - media table
            mediaStatement.setString(1, media.getUrl());
            mediaStatement.setString(2, media.getFiltype());
            mediaStatement.executeUpdate();

            //get media_id
            ResultSet generatedKeys = mediaStatement.getGeneratedKeys();
            int mediaId = 0;

            if (generatedKeys.next()) {
                mediaId = generatedKeys.getInt(1);
            }

            // add media_id to post
            updateStatement.setInt(1, mediaId);
            updateStatement.setInt(2, post.getPostId());
            updateStatement.executeUpdate();

            if (addToGallery) {
                //create row - gallery table
                galleryStatement.setInt(1, mediaId);
                galleryStatement.setInt(2, post.getPostId());
                galleryStatement.addBatch();
                galleryStatement.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
