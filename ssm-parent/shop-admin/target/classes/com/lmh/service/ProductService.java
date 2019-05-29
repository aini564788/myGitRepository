package com.lmh.service;

import com.lmh.pojo.Brand;
import com.lmh.pojo.Category;
import com.lmh.pojo.Product;
import com.lmh.vo.ProductVo;

import java.util.List;

public interface ProductService {
    List<Category> queryCategories();

    List<Category> queryCategoriesById(Long id);

    List<Brand> queryAllBrands();

    List<ProductVo> queryProductsByPages(Product product, Integer page, Integer limit);

    Long queryCountProducts(Product product, Integer page, Integer limit);

    Product queryProductByPid(Long pid);

    boolean addProduct(Product product);

    boolean updateAudit(boolean audit, Long productId);

    boolean updatePublish(boolean saleStatus, Long productId);

    boolean addProductPicture(String path, Long id);

    boolean deleteById(Long pid);

    boolean deleteBatch(Long[] ids);
}
