package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Document;
import cn.muye.service.DocumentService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/26.
 */
@RestController
@RequestMapping("/document")
public class DocumentController {

    private static final Logger LOGGER = Logger.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public AjaxResult getDocumentList(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                      @RequestParam(value = "pageSize",required = false, defaultValue = "5")Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Document> list = documentService.listDocuments(page);
        return AjaxResult.success(list);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public AjaxResult postDocument(@RequestBody Document document) {
        document.setCreateTime(new Date());
        documentService.saveDocument(document);
        return AjaxResult.success(document);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public AjaxResult getDocument(@PathVariable Long id) {
        Document document = documentService.getById(id);
        return AjaxResult.success(document);
    }

    @RequestMapping(value = "/category/{categoryId}", method=RequestMethod.GET)
    public AjaxResult getByCategoryId(@PathVariable Long categoryId) {
        Document document = documentService.getByMenuId(categoryId);
        return AjaxResult.success(document);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public AjaxResult putDocument(@PathVariable Long id, @RequestBody Document document) {
        Document documentDb = documentService.getById(id);
        documentDb.setTitle(document.getTitle());
        documentDb.setContent(document.getContent());
        documentDb.setMenuId(document.getMenuId());
        documentDb.setCreateTime(document.getCreateTime());
        documentDb.setUpdateTime(document.getUpdateTime());
        documentDb.setVersionId(document.getVersionId());
        documentService.updateDocument(documentDb);
        return AjaxResult.success(documentDb);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public AjaxResult deleteDocument(@PathVariable Long id) {
        documentService.deleteById(id);
        return AjaxResult.success();
    }

}
