package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image();
        Blog blog=blogRepository2.findById(blogId).get();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
//        Image image=imageRepository2.findById(id).get();
//        Blog blog=image.getBlog();
//        blog.getImageList().remove(image);
        imageRepository2.deleteById(id);
//        blogRepository2.save(blog);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String imageSize=image.getDimensions();
        String[] imageDimension=imageSize.split("X");
        String[] screenDimension=screenDimensions.split("X");
        int il=Integer.parseInt(imageDimension[0]);
        int ih=Integer.parseInt(imageDimension[1]);
        int sl=Integer.parseInt(screenDimension[0]);
        int sh=Integer.parseInt(screenDimension[1]);
        int screenArea=sl*sh;
        int imageArea=il*ih;
        int count=screenArea/imageArea;
        return count;
    }
}
