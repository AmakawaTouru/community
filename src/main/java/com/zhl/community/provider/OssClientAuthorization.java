package com.zhl.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.StorageClass;
import com.zhl.community.exception.CustomizeErrorCode;
import com.zhl.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class OssClientAuthorization {


    @Value("${AliCloud.endpoint}")
    private String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    @Value("${AliCloud.accessKeyId}")
    private String accessKeyId;
    @Value("${AliCloud.accessKeySecret}")
    private String accessKeySecret;
    @Value("${AliCloud.bucketName}")
    private String bucketName;


    /**
     * 将图片的字节流上传到阿里云OOS里面。
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    public String upload(InputStream inputStream, String fileName) throws IOException {
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        //构造一个文件名
        if(filePaths.length > 1){
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        }else{
            return  null;
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,generatedFileName, inputStream);

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        putObjectRequest.setMetadata(metadata);

        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        ossClient.putObject(putObjectRequest);

        Long dateTime = 1962631209000L;
        Date expiration = new Date(dateTime);


        String url = ossClient.generatePresignedUrl(bucketName, generatedFileName, expiration).toString();
//        System.out.println(url.split("\\?")[0]);
        if(url != null){
            // 关闭OSSClient。
            ossClient.shutdown();
            return url.split("\\?")[0];
        }else{
            //抛出文件上传失败的异常
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILURE);
        }
    }


}
