package com.lmh.mapper;

import com.lmh.pojo.Product;
import com.lmh.pojo.ProductExample;
import com.lmh.pojo.ProductExpand;
import com.lmh.pojo.ProductPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductExpand> queryProductByPages(@Param("p") Product product, @Param("page") int i, @Param("limit") Integer limit);

    Long queryCount(@Param("p") Product product,@Param("page") int i, @Param("limit") Integer limit);

    ProductPicture selectPic(@Param("pid") Long productId);

    int deleteBatch(@Param("ids") Long[] ids);

    int deleteBatchPics(@Param("ids") Long[] ids);
}