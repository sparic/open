package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Document;
import cn.muye.service.DocumentService;
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
    public AjaxResult getDocumentList() {
        List<Document> list = documentService.listDocuments();
        return AjaxResult.success(list);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public AjaxResult postDocument(@RequestBody Document document) {
        document.setCreateTime(new Date());
        documentService.saveDocument(document);
        return AjaxResult.success(document);
    }

    @RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
    public AjaxResult getDocumentByCategoryId(@RequestParam(value = "categoryId") Long categoryId) {
        List<Document> documentList = documentService.getByCategoryId(categoryId);
        return AjaxResult.success(documentList);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public AjaxResult getDocumentById(@RequestParam(value = "id") Long id) {
        Document document = documentService.getById(id);
        return AjaxResult.success(document);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public AjaxResult putDocument(@PathVariable Long id, @RequestBody Document document) {
        Document documentDb = documentService.getById(id);
        documentDb.setTitle(document.getTitle());
        documentDb.setContent(document.getContent());
        documentDb.setCategoryId(document.getCategoryId());
        documentDb.setCreateTime(document.getCreateTime());
        documentDb.setUpdateTime(document.getUpdateTime());
        documentService.updateDocument(documentDb);
        return AjaxResult.success(documentDb);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public AjaxResult deleteDocument(@PathVariable Long id) {
        documentService.deleteById(id);
        return AjaxResult.success();
    }

}
