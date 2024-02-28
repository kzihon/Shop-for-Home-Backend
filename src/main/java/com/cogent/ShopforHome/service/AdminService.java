package com.cogent.ShopforHome.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface AdminService {
    ByteArrayInputStream getDataDownloaded() throws IOException;

    Integer uploadProducts(MultipartFile file) throws IOException;
     double setDiscountPercentageForCustomer(long customerId, double  discountPercentage);
}
