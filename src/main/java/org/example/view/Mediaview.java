package org.example.view;

import org.example.controller.CommentsController;
import org.example.controller.MediaController;

import java.util.Scanner;

public class Mediaview {

    public static void addImage(){
        Scanner scanner = new Scanner(System.in);
        MediaController mediaController = new MediaController();

        System.out.println("1.Add image");

        String menuOptionInput = scanner.nextLine();

        switch (menuOptionInput) {
            case"1": {
                System.out.println("Write url");
                String urlInput = scanner.nextLine();
                System.out.println("Write file type");
                String fileType = scanner.nextLine();
                System.out.println("Write post id");
                int owner_id = Integer.parseInt(scanner.nextLine());
                System.out.println("Add to gallery? [y/n]");
                String addToGallery = scanner.nextLine();
                mediaController.addImage(urlInput,fileType,owner_id,addToGallery);
            }
        }
    }
}
