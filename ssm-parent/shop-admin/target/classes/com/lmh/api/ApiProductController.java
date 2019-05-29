package com.lmh.api;

import com.lmh.pojo.Product;
import com.lmh.service.ProductService;
import com.lmh.vo.ProductVo;
import com.lmh.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * 主要是提供商品的数据服务
 */
@RestController//@ResponseBody+@Controller
@RequestMapping("/api/test")
@Slf4j
public class ApiProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页获取所有商品
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public  Object apiGetProductPages(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println(s+"================="+request.getHeader(s));

        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                System.out.println(c.getName()+"==="+c.getValue());
            }

        }

        List<ProductVo> productVos = productService.queryProductsByPages(new Product(), page, limit);
        return productVos;
    }


    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public Object addProduct(Product product){

      /*  System.out.println(product);*/

        log.info("--------------->"+product);

        return ResultVo.success();    }


    /**
     * 根据id查询  GET
     *      * rest:http://localhost/shop-admin/api/product/1
     */
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public  Object queryById(@PathVariable Long id){

        log.info("------------------->"+id);

        return id;
    }

    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    public   Object  deleteById(@PathVariable Long id){

        log.info("-------------------------->"+id);
        return  id;
    }

    /**
     * 更新
     * url:/product/{id}
     */
    @RequestMapping(value = "/product/{id}",method = RequestMethod.PUT)
    //@RequestBody 获取请求体数据@RequestBody  String  str
    public  Object  update(@PathVariable Long id,Product product){

        log.info(product+"---------------------->"+id);
        return id;
    }

    /**
     * 更新
     * url:/product/{id}
     */
    @RequestMapping(value = "/product2/{id}",method = RequestMethod.PUT)
    //@RequestBody 获取请求体数据@RequestBody  String  str   把请求体的内容赋值给str

    public  Object  update2(@PathVariable Long id,@RequestBody Product product){

        log.info(product+"---------------------->"+id);
        return id;
    }
}
