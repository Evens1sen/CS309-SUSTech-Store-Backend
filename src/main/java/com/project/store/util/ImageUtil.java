package com.project.store.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

public class ImageUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    static String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static String accessKeyId = "LTAI5t8Fkz58dPJEVP3AciH9";
    static String accessKeySecret = "PsndQ3YByY327bDVuX5hQirztzvd4u";
    // 填写Bucket名称，例如examplebucket。
    static String bucketName = "sustech-store";

    //Save the image to Aliyun OSS and return the url
    public static String postImage(String baseStr, String objectName) {
        String imgFormat = baseStr.substring(baseStr.indexOf("/") + 1, baseStr.indexOf(";"));
        baseStr = baseStr.substring(baseStr.indexOf(",") + 1);
        objectName = objectName + "." + imgFormat;
        String url = "https://sustech-store.oss-cn-shenzhen.aliyuncs.com/" + objectName;
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = new ByteArrayInputStream(base64ToByteArray(baseStr));
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            assert ossClient != null;
            ossClient.shutdown();
        }

        return url;
    }

    public static void deleteImage(String objectName){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //Decode base64 image to byte array
    public static byte[] base64ToByteArray(String imgData) {
//        Base64.Decoder decoder = Base64.getDecoder();
        byte[] b = Base64.decodeBase64(imgData);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return b;
    }

    public static String generateObjectName(String objectName, int length){
        // Random generate a file name according to passed string
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < length; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            objectName += result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }
}
