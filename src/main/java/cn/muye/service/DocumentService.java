package cn.muye.service;

import cn.muye.mapper.DocumentMapper;
import cn.muye.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/26.
 */
@Service
@Transactional
public class DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    public void saveDocument(Document document) {
        documentMapper.saveDocument(document);
    }

    public void updateDocument(Document document) {
        documentMapper.updateDocument(document);
    }

    public List<Document> listDocuments(int page) {
        return documentMapper.listDocuments(page);
    }

    public Document getByMenuId(Long menuId) {
        return documentMapper.getByMenuId(menuId);
    }

    public void deleteById(Long id) {
        documentMapper.deleteById(id);
    }

    public Document getById(Long id) {
        return documentMapper.getById(id);
    }

    public List<Document> getByVersionId(Long id) {
        return documentMapper.getByVersionId(id);
    }
}
