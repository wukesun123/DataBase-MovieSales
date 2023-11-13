package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    /**
     * 上传文件的接口
     * @param file 要上传的文件
     * @return 包含上传后文件访问路径的 Result 对象
     * @throws IOException 文件上传过程中的IO异常
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 生成唯一标识符，用于文件名
        String flag = IdUtil.fastSimpleUUID();
        // 拼接文件在服务器端的保存路径
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + flag + "_" + originalFilename;
        // 将文件字节写入服务器
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        // 返回上传成功的结果，包括文件的访问路径
        return Result.success(ip + ":" + port + "/files/" + flag);
    }

    /**
     * 根据文件标识符获取文件
     * @param flag 文件标识符
     * @param response HTTP响应对象，用于输出文件内容
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/";  // 定义文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        // 找到与参数一致的文件名
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                // 设置响应头，指定文件名和内容类型
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}

