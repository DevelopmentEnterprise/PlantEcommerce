package com.WebMall.controller;

import com.WebMall.model.*;
import com.WebMall.repository.CategoryRepository;
import com.WebMall.repository.StoreRepository;
import com.WebMall.service.AccessDeniedException;
import com.WebMall.service.NotFoundException;
import com.WebMall.service.goodsServices.GoodsService;
import com.WebMall.service.storeServices.StoreService;
import com.WebMall.service.userServices.UserService;
import com.WebMall.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * -- Created by Killer_hacker999 --
 * Represents all functionality for operating sellers' stores logic
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GoodValidator goodValidator;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GoodsService goodsService;

    /**
     * Show store page by id
     */
    @RequestMapping("/showStore")
    public String showStorePage(@RequestParam(name = "storeId") Long storeId, Model model,
                                @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                @RequestParam(name = "sortBy", required = false) String sortType){
        Optional<Store> foundStore = storeRepository.findById(storeId);
        if (foundStore.isEmpty()) {
            throw new NotFoundException();
        }

        Store requestedStore = foundStore.get();
        List<Good> goodsToShow = goodsService.sortGoodsByParam(requestedStore.getGoods(), sortType);

        model.addAttribute("store", requestedStore);
        model.addAttribute("goodsToShow", goodsToShow);
        model.addAttribute("pagersCount", 1);
        return "store";
    }

    /**
     * Admin panel for store owner (seller role and authentication required!)
     */
    @RequestMapping("/myStore")
    public String showStoreArea(Model model){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null){
            throw new AccessDeniedException();
        }

        Store requestedStore = loggedUser.getStore();

        model.addAttribute("store", requestedStore);
        return "my-store";
    }

    /**
     * Edit main store information such as name, description...
     */
    @RequestMapping(value = "/editStoreInfo",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void editStoreInfo(@RequestBody MultiValueMap<String, String> params){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) {
            throw new AccessDeniedException();
        }

        Store userStore = loggedUser.getStore();
        userStore.setName(params.getFirst("store-name"));
        userStore.setDescription(params.getFirst("store-description"));

        userService.save(loggedUser);
    }

    /**
     * Edit all store address info such as city, street
     */
    @RequestMapping(value = "/editStoreAddress",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void editStoreAddress(@RequestBody MultiValueMap<String, String> addressParams){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null){
            throw new AccessDeniedException();
        }

        Store userStore = loggedUser.getStore();
        StoreAddress storeAddress = userStore.getStoreAddress();
        storeAddress.setCity(addressParams.getFirst("store-city"));
        storeAddress.setStreet(addressParams.getFirst("store-street"));

        userService.save(loggedUser);
    }

    @RequestMapping(value = "/createStoreGood", method = RequestMethod.GET)
    public String createStoreGood(Model model){
        model.addAttribute("acceptUrl", "createStoreGood");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("good", new Good());

        return "good-control";
    }

    /**
     * Create new store good by seller
     * @return success url(/myStore)
     */
    @RequestMapping(value = "/createStoreGood", method = RequestMethod.POST)
    public String createStoreGood(@ModelAttribute("good") Good createdGood,
                                  @RequestParam("images") List<MultipartFile> images,
                                  @RequestParam("goodCategory") String goodCategory,
                                  Model model){
        //Check for authorization
        if (!userService.checkAuthUser()) throw new AccessDeniedException();

        GoodCategory selectedCategory = categoryRepository.findByName(goodCategory);

        if (!goodValidator.validateGood(createdGood) || selectedCategory == null){
            model.addAttribute("acceptUrl", "createStoreGood");
            model.addAttribute("good", createdGood);
            model.addAttribute("categories", categoryRepository.findAll());
            return "good-control";
        }

        storeService.createNewGood(createdGood, images, selectedCategory);

        return "redirect:/store/myStore";
    }

    @RequestMapping(value = "/editStoreGood", method = RequestMethod.GET)
    public String editStoreGood(Model model, @RequestParam("goodId") Long goodId){
        Good good = goodsService.getGoodById(goodId);
        if (good == null) return "my-store";

        model.addAttribute("acceptUrl", "editStoreGood");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("good", good);
        return "good-control";
    }

    @RequestMapping(value = "/editStoreGood", method = RequestMethod.POST)
    public String editStoreGood(Model model, @ModelAttribute("good") Good goodToEdit,
                                @RequestParam("images") List<MultipartFile> images,
                                @RequestParam("goodCategory") String goodCategory){

        //Check for authorization
        if (!userService.checkAuthUser()) throw new AccessDeniedException();

        GoodCategory selectedCategory = categoryRepository.findByName(goodCategory);

        //Validate edited good
        if (!goodValidator.validateGood(goodToEdit) || selectedCategory == null || images.size() == 0){
            List<GoodCategory> goodCategories = new ArrayList<>();
            goodCategories.add(selectedCategory);
            goodToEdit.setGoodCategories(goodCategories);

            model.addAttribute("acceptUrl", "editStoreGood");
            model.addAttribute("good", goodToEdit);
            model.addAttribute("categories", categoryRepository.findAll());
            return "good-control";
        }

        //Save good to DB with uploaded images (if exist)
        storeService.saveGood(goodToEdit, goodToEdit.getId(), images, selectedCategory);

        return "my-store";
    }

    @RequestMapping(value = "/addBannerImage", method = RequestMethod.POST)
    public String addBannerImage(@RequestParam("banner-image") MultipartFile bannerImage){
        //Check for authorization
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) throw new AccessDeniedException();

        List<MultipartFile> dataForUpload = new ArrayList<>();
        dataForUpload.add(bannerImage);

        //Save uploaded file on server
        storeService.uploadGoodImages(dataForUpload);

        //Make changes in store model
        Store currentStore = loggedUser.getStore();
        currentStore.setBannerImageSrc("/работа/PlantEcommerce/WebMall/src/main/webapp/resources/images/"
                + bannerImage.getOriginalFilename());

        //Save changes to database
        storeRepository.save(currentStore);

        return "redirect:/store/myStore";
    }
}