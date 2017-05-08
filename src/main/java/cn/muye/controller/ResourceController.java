package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
import cn.muye.config.CustomProperties;
import cn.muye.dto.ResourceDto;
import cn.muye.model.Resource;
import cn.muye.service.ResourceService;
import cn.muye.util.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Ray.Fu on 2017/4/27.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CustomProperties customProperties;

    /**
     * 获取所有文件列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "获取所有文件", httpMethod = "GET", notes = "获取所有文件")
    public AjaxResult getFileList(@ApiParam(value = "页号")@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数")@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Resource> list = resourceService.listFiles(page);
        List<ResourceDto> listDto = Lists.newArrayList();
        if (list != null && list.size() > 0) {
            for (Resource resource : list) {
                listDto.add(objectToDto(resource));
            }
        }
        PageInfo<ResourceDto> resourceDtoPageInfo = new PageInfo(list);
        resourceDtoPageInfo.setList(listDto);
        return AjaxResult.success(resourceDtoPageInfo);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "上传文件", httpMethod = "POST", notes = "上传文件")
    public AjaxResult uploadAndPostFile(@ApiParam(value = "文件") @RequestParam(value = "file", required = false) MultipartFile file) {
        uploadFile(file);
        return addFileInfo(file);
    }

//    private void modifyVersionInfo(MultipartFile file, Long versionId) {
//        Version versionDb = versionService.getById(versionId);
//        versionDb.setUrl(customProperties.getHttp() + file.getOriginalFilename());
//        versionService.updateVersion(versionDb);
//    }

    private void uploadFile(MultipartFile file) {
        try {
            File dest = FileUtils.getFile(customProperties.getDirs() + File.separator + Constants.RESOURCE_SUB_DIRECTORY);
            LOGGER.info("createResource dest.path ={} ", dest.getPath());
            String fileName = file.getOriginalFilename();
            dest.mkdirs();
            dest = FileUtils.getFile(dest.getPath() + File.separator + fileName);
            LOGGER.info("createResource dest.path with fileName ={} ", dest.getPath());
            if (!dest.exists()) {
                dest.createNewFile();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private AjaxResult addFileInfo(MultipartFile sourceFile) {
        Resource file = new Resource();
        String fileName = sourceFile.getOriginalFilename();
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        file.setSize(sourceFile.getSize());
        file.setExtensionName(extensionName);
        Random random = new Random();
        int randomNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        file.setName(DateTimeUtils.toShortDateTime(new Date()) + randomNum + "." + extensionName);
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
        dto.setUrl(customProperties.getHttp() + dto.getName());
        return dto;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public AjaxResult deleteFile(@PathVariable Long id) {
//        Resource file = fileService.getById(id);
//        FileUtil.delete(file.getPath());
//        deleteFileEntity(id);
//        return AjaxResult.success();
//    }
//
//    private void deleteFileEntity(Long id) {
//        fileService.deleteById(id);
//    }

//    public static void main(String[] args) {
//        Resource file = new Resource();
//        file.setId(1L);
//        file.setName("name.txt");
//        file.setDescription("aaaaaa");
//        file.setSourceName("dream.txt");
//        file.setCreateTime(new Date());
//        System.out.println(JSON.toJSONString(file));
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public AjaxResult putDocument(@PathVariable Long id, @RequestBody Resource file) {
//        Document documentDb = fileService.getById(id);
//        documentDb.setTitle(document.getTitle());
//        documentDb.setContent(document.getContent());
//        documentDb.setMenuId(document.getMenuId());
//        documentDb.setCreateTime(document.getCreateTime());
//        documentDb.setUpdateTime(document.getUpdateTime());
//        documentService.updateDocument(documentDb);
//        return AjaxResult.success(documentDb);
//    }
}
