package com.WebMall.service.storeServices;

import com.WebMall.model.*;
import com.WebMall.model.enums.OrderStatus;
import com.WebMall.repository.GoodOptionRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private GoodOptionRepository goodOptionRepository;

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
                    .filter(el -> el.getStatus() == OrderStatus.ASSEMBLY)
                    .collect(Collectors.toList());

            case "deliver" -> ordersToFilter
                    .stream()
                    .filter(el -> el.getStatus() == OrderStatus.DELIVER)
                    .collect(Collectors.toList());

            case "completed" -> ordersToFilter
                    .stream()
                    .filter(el -> el.getStatus() == OrderStatus.COMPLETED)
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
            if (Objects.equals(images.get(i).getOriginalFilename(), "")) continue;
            GoodImage img = new GoodImage();
            img.setImageSrc("/работа/PlantEcommerce/WebMall/src/main/webapp/resources/images/" +
                    images.get(i).getOriginalFilename());
            img.setGood(createdGood);
            goodImages.add(img);
        }

        //Set category
        List<GoodCategory> categories = new ArrayList<>();
        categories.add(goodCategory);
        createdGood.setGoodCategories(categories);

        //Set images
        createdGood.setGoodImages(goodImages);

        //Make rating 0
        createdGood.setRating(0F);

        //Save good
        createdGood.setStore(userStore);
        goodRepository.save(createdGood);

        //Upload image to server folder
        uploadGoodImages(images);
    }

    @Override
    public void saveGood(Good editedGood, Long goodId, List<MultipartFile> images, GoodCategory goodCategory) {
        Optional<Good> foundGood = goodRepository.findById(goodId);
        if (foundGood.isEmpty()) return;

        Good good = foundGood.get();
        good.setName(editedGood.getName());
        good.setDescription(editedGood.getDescription());
        good.setPriceBeforeDiscount(editedGood.getPriceBeforeDiscount());
        good.setOnSale(editedGood.getOnSale());

        List<GoodCategory> goodCategories = new ArrayList<>();
        goodCategories.add(goodCategory);
        good.setGoodCategories(goodCategories);

        goodCategory.getGoods().add(good);

        //Filter good options for null values
        editedGood.setGoodOptions(editedGood.getGoodOptions()
                .stream()
                .filter(el -> el.getName() != null && el.getPrice() != null)
                .collect(Collectors.toList()));

        //Options that must be deleted
        List<GoodOption> goodOptionsToDelete = new ArrayList<>();

        //If user deleted all good options -> delete from DB
        if (editedGood.getGoodOptions() == null || editedGood.getGoodOptions().size() == 0){
            good.setGoodOptions(null);
        }

        //Remove existing good options
        if (good.getGoodOptions() != null && good.getGoodOptions().size() != 0){
            for(GoodOption goodOption : good.getGoodOptions()){
                Optional<GoodOption> foundGoodOption = editedGood.getGoodOptions()
                        .stream()
                        .filter(el -> el.getName().equals(goodOption.getName()) && el.getPrice().equals(goodOption.getPrice()))
                        .findFirst();

                //Good option must be deleted
                if (foundGoodOption.isEmpty()){
                    goodOptionsToDelete.add(goodOption);
                }
            }

            good.getGoodOptions().removeAll(goodOptionsToDelete);
        }

        //Add new good options if no such exist
        if (editedGood.getGoodOptions() != null && editedGood.getGoodOptions().size() != 0){
            List<GoodOption> goodOptions = new ArrayList<>();

            for (int i = 0; i < editedGood.getGoodOptions().size(); i++) {
                GoodOption goodOption = editedGood.getGoodOptions().get(i);

                Optional<GoodOption> existingGoodOption = good.getGoodOptions()
                        .stream()
                        .filter(el -> el.getName().equals(goodOption.getName()) && el.getPrice().equals(goodOption.getPrice()))
                        .limit(1).findFirst();

                //If no elems with such data found -> add new good option
                if (existingGoodOption.isEmpty()){
                    goodOption.setGood(good);
                    goodOptions.add(goodOption);
                }
            }
            good.getGoodOptions().addAll(goodOptions);
        }

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

        for(GoodOption goodOption : goodOptionsToDelete){
            goodOptionRepository.delete(goodOption);
        }
    }
}
