package com.WebMall.service.storeServices;

import com.WebMall.model.*;
import com.WebMall.model.enums.Status;
import com.WebMall.repository.GoodRepository;
import com.WebMall.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private StoreService storeService;

    @Override
    public List<Good> filterStoreGoods(List<Good> goodsToFilter, String filterParam) {
        return switch (filterParam){
            case "sale" -> goodsToFilter.stream().filter(Good::getOnSale).collect(Collectors.toList());
            case "hidden" -> goodsToFilter.stream().filter(el -> !el.getOnSale()).collect(Collectors.toList());
            default -> goodsToFilter;
        };
    }

    @Override
    public List<Order> filterStoreOrders(List<Order> ordersToFilter, String filterParam) {
        return switch (filterParam){
            case "assembly" -> ordersToFilter
                    .stream()
                    .filter(el -> el.getStatus() == Status.ASSEMBLY)
                    .collect(Collectors.toList());

            case "deliver" -> ordersToFilter
                    .stream()
                    .filter(el -> el.getStatus() == Status.DELIVER)
                    .collect(Collectors.toList());

            case "completed" -> ordersToFilter
                    .stream()
                    .filter(el -> el.getStatus() == Status.COMPLETED)
                    .collect(Collectors.toList());

            default -> ordersToFilter;
        };
    }

    @Override
    public void uploadGoodImages(List<MultipartFile> images) {
        String uploadedPath = "/работа/PlantEcommerce/WebMall/src/main/webapp/resources/images/";
        Path uploadPath = Paths.get(uploadedPath);

        if (!Files.exists(uploadPath)){
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(MultipartFile image : images){
            Path filePath = uploadPath.resolve(Objects.requireNonNull(image.getOriginalFilename()));
            InputStream inputStream;

            try {
                inputStream = image.getInputStream();
                Files.copy(inputStream, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createNewGood(Good createdGood, List<MultipartFile> images, GoodCategory goodCategory) {
        User loggedUser = userService.getLoggedUser();
        List<GoodImage> goodImages = new ArrayList<>();
        Store userStore = loggedUser.getStore();

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).getOriginalFilename().equals("")) continue;
            GoodImage img = new GoodImage();
            img.setImageSrc("/работа/PlantEcommerce/WebMall/src/main/webapp/resources/images/" + images.get(i).getOriginalFilename());
            img.setGood(createdGood);
            goodImages.add(img);
        }

        //Set category
        List<GoodCategory> categories = new ArrayList<>();
        categories.add(goodCategory);
        createdGood.setGoodCategories(categories);

        createdGood.setGoodImages(goodImages);

        //Save good
        createdGood.setStore(userStore);
        goodRepository.save(createdGood);

        //Upload image to server folder
        storeService.uploadGoodImages(images);
    }

    @Override
    public void saveGood(Good editedGood, Long goodId, List<MultipartFile> images, GoodCategory goodCategory) {
        Good good = goodRepository.getById(goodId);
        good.setName(editedGood.getName());
        good.setDescription(editedGood.getDescription());
        good.setPriceBeforeDiscount(editedGood.getPriceBeforeDiscount());
        good.setOnSale(editedGood.getOnSale());
        good.setGoodCategories(Collections.singletonList(goodCategory));

        //New images were uploaded
        if (images != null){
            List<GoodImage> goodImages = new ArrayList<>();

            for (int i = 0; i < images.size(); i++) {
                if (images.get(i).getOriginalFilename().equals("")) continue;
                GoodImage goodImage = new GoodImage();
                goodImage.setImageSrc(images.get(i).getOriginalFilename());
                goodImage.setGood(good);
                goodImages.add(goodImage);
            }

            if (goodImages.size() != 0){
                List<GoodImage> existingImages = good.getGoodImages();

                //Add new good images
                existingImages.addAll(goodImages);
                good.setGoodImages(existingImages);
            }
        }

        goodRepository.save(good);
    }
}
