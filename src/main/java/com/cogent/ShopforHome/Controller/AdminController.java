package com.cogent.ShopforHome.Controller;

import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.service.AdminService;
import com.cogent.ShopforHome.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;
    @GetMapping("/downloadProductdetails")
    public ResponseEntity<InputStreamResource> download() throws IOException {
        String fileName= "productDetails.csv";


        ByteArrayInputStream inputStream = adminService.getDataDownloaded();
        InputStreamResource response = new InputStreamResource(inputStream);

        ResponseEntity<InputStreamResource> responseEntity= ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response);
        return responseEntity;

    }
//    @GetMapping("/product/product_Id")
//    public ResponseEntity<Integer> getStocksForEachProduct(@PathVariable int product_Id){
//        return 0;
//    }
    @PostMapping(value = "/products/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadProducts(@RequestPart("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(adminService.uploadProducts(file));
    }
    @GetMapping(value="/product/get/{productId}")
    public ResponseEntity<Integer> getStockForProduc(@PathVariable long productId) {
        Product p=productService.getProductById(productId);

        if( p==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        int stock=p.getNumberInStock();

        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }
    @GetMapping("/customer/{customerId}/setDiscountPercentage/{discountPercentage}")
    public ResponseEntity<Double> setDiscountPercentage(@PathVariable long customerId, @PathVariable double discountPercentage){
       double getDiscountPercentage= adminService.setDiscountPercentageForCustomer(customerId,discountPercentage);
       return ResponseEntity.status(HttpStatus.OK).body(getDiscountPercentage);
    }
}
