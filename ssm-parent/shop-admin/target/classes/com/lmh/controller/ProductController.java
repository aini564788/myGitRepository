package com.lmh.controller;

import com.lmh.pojo.Product;
import com.lmh.service.ProductService;
import com.lmh.ssm.UUIDUtils;
import com.lmh.vo.ProductVo;
import com.lmh.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    /**
     * 查询所有一级目录
     *
     * @return 一级目录json
     */

    @RequestMapping("/categories")
    @ResponseBody
    public Object queryCategories() {
        return productService.queryCategories();
    }

    /**
     * 根据一级目录id查询二级目录
     *
     * @param id 一级目录id
     * @return 相应二级目录json
     */
    @ResponseBody
    @RequestMapping("categories/second/{id}")
    public Object querySecondCategories(@PathVariable Long id) {

        return productService.queryCategoriesById(id);
    }

    /**
     * 查询所有品牌名
     *
     * @return 品牌字符串
     */
    @ResponseBody
    @RequestMapping("/brand")
    public Object queryAllBrands() {
        return productService.queryAllBrands();
    }

    /**
     * 分页查询商品
     *
     * @param product 商品名搜索
     * @param page    当前页
     * @param limit   每页数据
     * @return 商品json
     */
    @ResponseBody
    @RequestMapping("/product/query/page")
    public Object queryProductsByPages(Product product,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit) {

        List<ProductVo> productVos = productService.queryProductsByPages(product, page, limit);
        Long count = productService.queryCountProducts(product, page, limit);
        if (productVos.size() > 0) {
            return ResultVo.success(count, productVos);
        }
        return ResultVo.error();
    }

    @ResponseBody
    @RequestMapping("product/upload/img")
    public Object upload(@RequestParam MultipartFile editorFile, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getServletContext().getRealPath("/static/imgs/introduce");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {
            dir.delete();
            dir.mkdirs();
        }
        if (editorFile.isEmpty()) {
            return null;
        }
        String fileName = UUIDUtils.getUUID().replaceAll("-", "") + ".jpg";
        ;
        editorFile.transferTo(new File(realPath + "/" + fileName));
        map.put("errno", 0);
        map.put("data", new String[]{"http://localhost:81/shop-admin/static/imgs/introduce/" + fileName});
        return map;
    }

    @RequestMapping("/product/desc/{pid}")

    public Object queryDesc(@PathVariable Long pid, Model model) {

        Product p = productService.queryProductByPid(pid);
        model.addAttribute("p", p);
        return "product/productdesc";
    }

    @RequestMapping("product/add/do")
    public Object addProduct(Product product) {
        boolean b = productService.addProduct(product);
        if (b) {
            return "product/productlist";
        }
        return "product/productadd";
    }

    @ResponseBody
    @RequestMapping("product/audit/update")
    public Object updateAudit(boolean audit, Long productId) {
        boolean b = productService.updateAudit(audit, productId);
        if (b) {
            return ResultVo.success();
        }
        return ResultVo.error();
    }


    @ResponseBody
    @RequestMapping("product/publish/update")
    public Object updatePublish(boolean saleStatus, Long productId) {
        boolean b = productService.updatePublish(saleStatus, productId);
        if (b) {
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    @RequestMapping("product/images/{pid}")
    public Object goToUpLoad(@PathVariable Long pid, Model model) {
        model.addAttribute("id", pid);
        return "product/productaddimages";
    }

    @RequestMapping("product/images/modify")
    public Object uploadMasterImage(@RequestParam MultipartFile file,
                                    Long id,
                                    HttpServletRequest request) throws IOException {
//    本地上传
        String realPath = request.getServletContext().getRealPath("/static/imgs/masterImages");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {
            dir.delete();
            dir.mkdirs();
        }
        String fileName = UUIDUtils.getUUID().replaceAll("-", "") + ".jpg";
        File dest = new File(realPath + "/" + fileName);
        file.transferTo(dest);
//   路径上传到数据库
        boolean b = productService.addProductPicture("static/imgs/masterImages/" + fileName, id);
        if (b) {
            return "product/productlist";
        }
        return "product/productlist";
    }
    @ResponseBody
    @RequestMapping(value = "product/delete/{pid}",method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable Long pid){
       boolean b = productService.deleteById(pid);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    @RequestMapping(value = "product/delete/batch",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteBatch(@RequestParam Long[] ids ){
        boolean b = productService.deleteBatch(ids);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }

}
