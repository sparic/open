package cn.muye.resource.controller;

import cn.muye.core.AjaxResult;
import cn.muye.config.CustomProperties;
import cn.muye.resource.domain.ApiPackage;
import cn.muye.resource.dto.ResourceDto;
import cn.muye.resource.domain.Resource;
import cn.muye.resource.service.ApiPackageService;
import cn.muye.resource.service.ResourceService;
import cn.muye.utils.DateTimeUtils;
import cn.muye.utils.FileUtils;
import cn.muye.utils.StringUtil;
import cn.muye.utils.ZipUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipOutputStream;

/**
 * Created by Ray.Fu on 2017/4/27.
 */
@RestController
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private ApiPackageService apiPackageService;

    /**
     * 获取所有文件列表
     *
     * @param page
     * @param pageSize
     * @return
     */
//    @RequestMapping(value = {"admin/resource"}, method = RequestMethod.GET)
//    @RequiresPermissions("resource:query")
//    @ApiOperation(value = "获取所有文件", httpMethod = "GET", notes = "获取所有文件")
//    public AjaxResult getFileList(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
//                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
//        PageHelper.startPage(page, pageSize);
//        List<Resource> list = resourceService.listFiles(page);
//        List<ResourceDto> listDto = Lists.newArrayList();
//        if (list != null && list.size() > 0) {
//            for (Resource resource : list) {
//                listDto.add(objectToDto(resource));
//            }
//        }
//        PageInfo<ResourceDto> resourceDtoPageInfo = new PageInfo(list);
//        resourceDtoPageInfo.setList(listDto);
//        return AjaxResult.success(resourceDtoPageInfo);
//    }

    /**
     * 前台上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = {"resource"}, method = RequestMethod.POST)
    @ApiOperation(value = "前台上传文件", httpMethod = "POST", notes = "前台上传文件")
    public AjaxResult frontUploadFile(@ApiParam(value = "文件") @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        List<String> supportFileTypes = customProperties.getSupportFileTypes();
        if (!supportFileTypes.contains(extensionName)) {
            return AjaxResult.failed(AjaxResult.CODE_ERROR_FAILED, "不能上传" + extensionName + "类型文件");
        }
        String newFileName = FileUtils.uploadFile(file, customProperties.getPushDirs(), request);
        return addFileInfo(file, newFileName);
    }

    /**
     * 打包压缩下载文件
     */
//    @RequestMapping(value = "/downLoadZipFile", method = RequestMethod.GET)
//    @ApiOperation(value = "下载压缩文件", httpMethod = "GET", notes = "下载压缩文件")
//    @RequiresPermissions("isvApply:download")
//    public void downLoadZipFile(@ApiParam(value = "三种类型:人机交互开放平台=>human_machine_interaction\n" +
//            "业务应用接入开放平台=>business_app_access\n" +
//            "SDK开放平台=>sdk_api") @RequestParam(value = "fileType", required = false) String fileType, HttpServletResponse response) throws IOException{
//        if (customProperties.getSupportBusinessTypes().contains(fileType)) {
//            ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
//            try {
//                ZipUtils.fileToZip(customProperties.getSourceFilePath() + File.separator + fileType, customProperties.getZipFilePath() + File.separator + fileType, fileType + ".zip");
//                cn.muye.utils.FileUtils.deleteDir(new File(customProperties.getSourceFilePath() + File.separator + fileType), "zip");
//                response.sendRedirect(customProperties.getZipFileUrl() + File.separator + fileType + File.separator+ fileType + ".zip");
//            } catch (Exception e) {
//                LOGGER.error("下载失败，原因：{}", e);
//            }finally{
//                out.close();
//            }
//        }
//    }

    /**
     * 后台上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = {"admin/resource"}, method = RequestMethod.POST)
    @RequiresPermissions("resource:upload")
    @ApiOperation(value = "上传文件", httpMethod = "POST", notes = "上传文件")
    public AjaxResult uploadAndPostFile(@ApiParam(value = "文件") @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String newFileName = FileUtils.uploadFile(file, customProperties.getPushDirs(), request);
        return addFileInfo(file, newFileName);
    }

    private AjaxResult addFileInfo(MultipartFile sourceFile, String newFileName) {
        Resource file = new Resource();
        String fileName = sourceFile.getOriginalFilename();
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        file.setSize(sourceFile.getSize());
        file.setExtensionName(extensionName);
        file.setName(newFileName);
        file.setSourceName(sourceFile.getOriginalFilename());
        file.setCreateTime(new Date());
        resourceService.saveFile(file);
        return AjaxResult.success(objectToDto(file));
    }

    private ResourceDto objectToDto(Resource file) {
        ResourceDto dto = new ResourceDto();
        dto.setId(file.getId());
        dto.setUpdateTime(DateTimeUtils.getDateString(file.getUpdateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setSize(file.getSize());
        dto.setName(file.getName());
        dto.setSourceName(file.getSourceName());
        dto.setDescription(file.getDescription());
        dto.setExtensionName(file.getExtensionName());
        dto.setCreateTime(DateTimeUtils.getDateString(file.getCreateTime(), DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_SHORT));
        dto.setUrl(customProperties.getPushHttp() + dto.getName());
        return dto;
    }

}
