package com.WebMall.service.storeServices;

import com.WebMall.model.Store;
import com.WebMall.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store findStoreById(Long id) {
        if (id == null) return null;

        return storeRepository.getById(id);
    }
}
