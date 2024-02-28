package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.Customer;
import com.cogent.ShopforHome.entity.Product;
import com.cogent.ShopforHome.repository.CustomerRepository;
import com.cogent.ShopforHome.repository.ProductRepository;
import com.cogent.ShopforHome.util.ExcelUtil;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<Product> products= productRepository.findAll();

        ByteArrayInputStream data= ExcelUtil.dataToExcel(products);
        return data;
    }

    @Override
    public Integer uploadProducts(MultipartFile file) throws IOException {
        Set<Product> products= parseCsv(file);
        productRepository.saveAll(products);
        return products.size();
    }

    @Override
    public double setDiscountPercentageForCustomer(long customerId, double discountPercentage) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setDiscountPercentage(discountPercentage);

        customerRepository.save(customer);
        return discountPercentage;
    }

    private Set<Product> parseCsv(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<ProductCsvRepresentation> strategy=
                    new HeaderColumnNameMappingStrategy<>();

            strategy.setType(ProductCsvRepresentation.class);
            CsvToBean<ProductCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<ProductCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

        return  csvToBean.parse()
                    .stream()
                    .map(csvLine->Product.builder()
                            .name(csvLine.getName())
                            .price(csvLine.getPrice())
                            .description(csvLine.getDescription())
                            .category(csvLine.getCategory())
                            .numberInStock(csvLine.getNumberInStock())
                            .supplierName(csvLine.getSupplierName())
                            .build()).collect(Collectors.toSet());
        }
    }


}
