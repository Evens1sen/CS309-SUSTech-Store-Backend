package com.project.store.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.ProductImage;
import com.project.store.service.ProductImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@Api(tags = "ProductImageController")
@RestController
@RequestMapping("/productImage")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @ApiOperation(value = "根据商品id获取所有商品图片")
    @GetMapping("/listProductImageByProductId/{id}")
    public List<ProductImage> listProductImageByProductId(@PathVariable Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("product_id", id);
        return productImageService.list(wrapper);
    }

    @ApiOperation(value = "根据商品id上传图片url")
    @PostMapping("/addProductImage/{id}/{url}")
    public boolean addProductImage(@PathVariable Integer id, @PathVariable String url) {
        ProductImage productImage = new ProductImage();
        productImage.setProductId(id);
        productImage.setImage(url);
        return productImageService.save(productImage);
    }

    @ApiOperation(value = "上传测试")
    @PostMapping("post")
    public <T> String post(@RequestBody T body) {
        System.out.println(body.getClass());
        return body.getClass().getName();
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("postImage")
    public void postImage() {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5t8Fkz58dPJEVP3AciH9";
        String accessKeySecret = "PsndQ3YByY327bDVuX5hQirztzvd4u";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "sustech-store";
        // 填写文件名。文件名包含路径，不包含Bucket名称。例如exampledir/exampleobject.txt。
        String objectName = "D:/Users/Desktop/Files/CourseTable.jpg";

        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            String content = "Hello OSS";
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
        } catch (OSSException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
