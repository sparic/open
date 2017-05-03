package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.bean.Constants;
import cn.muye.model.Resource;
import cn.muye.model.Version;
import cn.muye.service.ResourceService;
import cn.muye.service.VersionService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
    private VersionService versionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public AjaxResult getFileList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Resource> list = resourceService.listFiles(page);
        return AjaxResult.success(list);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadAndPostFile(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "type", required = false)String type, @RequestParam(value = "versionId", required = false) Long versionId) {
        uploadFile(file);
        if (type != null && type.equals(Constants.TYPE_SDK)) {
            modifyVersionInfo(file, versionId);
            return addFileInfo(file);
        } else {
            return addFileInfo(file);
        }
    }

    private void modifyVersionInfo(MultipartFile file, Long versionId) {
        Version versionDb = versionService.getById(versionId);
        versionDb.setUrl("http://172.16.1.52:18080/devResource/" + file.getOriginalFilename());
        versionService.updateVersion(versionDb);
    }

    private void uploadFile(MultipartFile file) {
        try {
            java.io.File dest = FileUtils.getFile(Constants.FILE_DIRECTORY + java.io.File.separator + "developers");
            LOGGER.info("createResource dest.path ={} ", dest.getPath());
            String fileName = file.getOriginalFilename();
            dest.mkdirs();
            dest = FileUtils.getFile(dest.getPath() + java.io.File.separator + fileName);
            LOGGER.info("createResource dest.path with fileName ={} ", dest.getPath());
            if (!dest.exists()) {
                dest.createNewFile();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AjaxResult addFileInfo(MultipartFile sourceFile) {
        Resource file = new Resource();
        String fileName = sourceFile.getOriginalFilename();
        String extensionName = fileName.substring(fileName.indexOf(".")+1, fileName.length());
        file.setSize(sourceFile.getSize());
        file.setExtensionName(extensionName);
        file.setSourceName(sourceFile.getOriginalFilename());
        file.setCreateTime(new Date());
        resourceService.saveFile(file);
        return AjaxResult.success(file);
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
