package cn.muye.resource.controller;

import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.config.CustomProperties;
import cn.muye.resource.dto.ResourceDto;
import cn.muye.resource.domain.Resource;
import cn.muye.resource.service.ResourceService;
import cn.muye.utils.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    /**
     * 获取所有文件列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = {"admin/resource"}, method = RequestMethod.GET)
    @RequiresPermissions("resource:query")
    @ApiOperation(value = "获取所有文件", httpMethod = "GET", notes = "获取所有文件")
    public AjaxResult getFileList(@ApiParam(value = "页号") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @ApiParam(value = "每页记录数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
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
    @RequestMapping(value = {"admin/resource"}, method = RequestMethod.POST)
    @RequiresPermissions("resource:upload")
    @ApiOperation(value = "上传文件", httpMethod = "POST", notes = "上传文件")
    public AjaxResult uploadAndPostFile(@ApiParam(value = "文件") @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        List<String> excludeFileTypes = customProperties.getExcludeFileTypes();
        if (excludeFileTypes.contains(extensionName)) {
            return AjaxResult.failed("不能上传" + extensionName + "类型文件");
        }
        String newFileName = uploadFile(file, request);
        return addFileInfo(file, newFileName);
    }

    private String uploadFile(MultipartFile file, HttpServletRequest request) {
        String newFileName = null;
        try {
            File dest = FileUtils.getFile(customProperties.getPushDirs() + File.separator);
            LOGGER.info("createResource dest.path ={} ", dest.getPath());
            dest.mkdirs();
            String originalFileName = file.getOriginalFilename();
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
            Random random = new Random();
            int randomNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
            newFileName = DateTimeUtils.toShortDateTime(new Date()) + randomNum + "." + extensionName;
            dest = FileUtils.getFile(dest.getPath() + File.separator + newFileName);
            LOGGER.info("createResource dest.path with fileName ={} ", dest.getPath());
            if (!dest.exists()) {
                dest.createNewFile();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return newFileName;
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
