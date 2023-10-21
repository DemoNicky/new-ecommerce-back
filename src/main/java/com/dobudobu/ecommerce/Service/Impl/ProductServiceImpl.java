package com.dobudobu.ecommerce.Service.Impl;

import com.dobudobu.ecommerce.DTO.GetProductResponse;
import com.dobudobu.ecommerce.DTO.ProductResponse;
import com.dobudobu.ecommerce.DTO.ResponseHandling;
import com.dobudobu.ecommerce.Entity.Category;
import com.dobudobu.ecommerce.Entity.ImageData;
import com.dobudobu.ecommerce.Entity.Product;
import com.dobudobu.ecommerce.Repositoy.CategoryRepository;
import com.dobudobu.ecommerce.Repositoy.ImageDataRepository;
import com.dobudobu.ecommerce.Repositoy.ProductRepository;
import com.dobudobu.ecommerce.Service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageDataRepository imageDataRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final String FOLDER_PATH = "C:/Users/edota/OneDrive/Desktop/imageFile/";
    private final Integer SOLD_ITEM = 0;

    @Transactional
    @Override
    public ProductResponse createProduct(String name, String desc, BigDecimal price,
                                         Integer stock, Long categoriesId, MultipartFile image) throws IOException {
        ImageData imageData = postImageData(image);
        Category category = getCategory(categoriesId);

        Product product = Product.builder()
                .name(name)
                .desc(desc)
                .price(price)
                .stock(stock)
                .soldItem(SOLD_ITEM)
                .createdAt(LocalDate.now())
                .modifiedAt(null)
                .deletedAt(null)
                .categories(category)
                .imageData(imageData)
                .build();
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDesc(product.getDesc());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setCategories(product.getCategories().getCategoryName());
        productResponse.setImage(product.getImageData());

        return productResponse;

    }

    @Override
    public ResponseHandling<GetProductResponse> getAllProduct() {
        
        return null;
    }

    /**
     * Mengambil data gambar dari MultipartFile dan membuat objek ImageData.
     *
     * @param img MultipartFile yang berisi gambar.
     * @return Objek ImageData yang mewakili data gambar.
     * @throws IllegalArgumentException Jika nama file atau tipe konten tidak valid.
     * @throws NullPointerException Jika gagal membuat objek ImageData.
     */
    public ImageData postImageData(MultipartFile img) throws IOException {
        String filePath = FOLDER_PATH+img.getOriginalFilename();
        img.transferTo(new File(filePath));

        if (img.getContentType() == null || img.getContentType().isEmpty()) {
            throw new IllegalArgumentException("Tipe konten tidak valid");
        }

        ImageData imageData = ImageData.builder()
                .name(img.getOriginalFilename())
                .type(img.getContentType())
                .imagePath(filePath)
                .build();

        if (imageData == null){
            throw new NullPointerException("Objek image kosong/tidak valid!!!");
        }

        return imageData;

    }

    public Category getCategory(Long id){
        //cobain pake optional nanti
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Category not found!!!"));
        if (category == null){
            throw new NullPointerException("Objek image kosong/tidak valid!!!");
        }
        return category;

    }

    public byte[] getImage(String imgName) throws IOException {
        Optional<ImageData> imageData = imageDataRepository.findByName(imgName);
        String imgPath = imageData.get().getImagePath();
        byte[] image = Files.readAllBytes(new File(imgPath).toPath());
        return image;
    }
}
