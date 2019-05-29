package com.lmh.service.impl;

import com.lmh.mapper.BrandMapper;
import com.lmh.mapper.CategoryMapper;
import com.lmh.mapper.ProductMapper;
import com.lmh.mapper.ProductPictureMapper;
import com.lmh.pojo.*;
import com.lmh.service.ProductService;
import com.lmh.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductPictureMapper productPictureMapper;


    @Override
    public List<Category> queryCategories() {
        CategoryExample categoryExample=new CategoryExample();
        categoryExample.createCriteria().andCategoryLevelEqualTo(1);
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public List<Category> queryCategoriesById(Long id) {
        CategoryExample categoryExample=new CategoryExample();
        categoryExample.createCriteria().andParentIdEqualTo(id.intValue());
        return categoryMapper.selectByExample(categoryExample);

    }

    @Override
    public List<Brand> queryAllBrands() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public List<ProductVo> queryProductsByPages(Product product, Integer page, Integer limit) {
        List<ProductExpand> productList = productMapper.queryProductByPages(product,(page-1)*limit,limit);

        List<ProductVo> list=new ArrayList<>();
        for(ProductExpand productExpand:productList){
            ProductVo vo =new ProductVo();
            BeanUtils.copyProperties(productExpand,vo);
            ProductPicture productPicture = productMapper.selectPic(productExpand.getProductId());
            if(productPicture!=null) {
                vo.setPicUrl(productPicture.getPicUrl());
            }
            list.add(vo);
        }
        return list;
    }

    @Override
    public Long queryCountProducts(Product product, Integer page, Integer limit) {

        return productMapper.queryCount(product,(page-1)*limit,limit);
    }

    @Override
    public Product queryProductByPid(Long pid) {
        return productMapper.selectByPrimaryKey(pid);
    }

    @Override
    public boolean addProduct(Product product) {
        product.setProductionDate(new Date());
        product.setModifiedTime(new Date());
        int i = productMapper.insertSelective(product);
        return i>0;
    }

    @Override
    public boolean updateAudit(boolean audit, Long productId) {
        Product product =new Product();
        product.setAuditStatus(audit?1:0);
        product.setProductId(productId);
        int i = productMapper.updateByPrimaryKeySelective(product);
        return i>0;
    }
    @Override
    public boolean updatePublish(boolean saleStatus, Long productId) {
        Product product =new Product();
        product.setPublishStatus(saleStatus?1:0);
        product.setProductId(productId);
        int i = productMapper.updateByPrimaryKeySelective(product);
        return i>0;
    }

    @Override
    public boolean addProductPicture(String path, Long id) {
        ProductPicture productPicture=new ProductPicture();
        productPicture.setIsMaster(1);
        productPicture.setModifiedTime(new Date());
        productPicture.setProductId(id);
        productPicture.setPicUrl(path);
        int i = productPictureMapper.insertSelective(productPicture);
        return i>0;
    }

    @Override
    public boolean deleteById(Long pid) {
        ProductPictureExample productPictureExample=new ProductPictureExample();
        productPictureExample.createCriteria().andProductIdEqualTo(pid);
        productPictureMapper.deleteByExample(productPictureExample);
        return productMapper.deleteByPrimaryKey(pid)>0  ;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        int j = productMapper.deleteBatchPics(ids);
        int i = productMapper.deleteBatch(ids);
        return i>0;
    }
}
