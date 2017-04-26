package cn.muye.mapper;

import cn.muye.model.Document;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public interface DocumentMapper {

    void saveDocument(Document document);

    void updateDocument(Document document);

    List<Document> listDocuments();

    List<Document> getByCategoryId(Long categoryId);

    void deleteById(Long id);

    Document getById(Long id);
}
