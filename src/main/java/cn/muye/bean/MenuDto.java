package cn.muye.bean;

import cn.muye.model.Document;
import cn.muye.model.Menu;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/27.
 */
public class MenuDto {

    private String menuName;

    private Long id;

    private Long versionId;

    private Long parentId;

    private List<MenuDto> childrenMenuDtoList;

    private Long documentId;

    private Boolean isLeaf;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MenuDto> getChildrenMenuDtoList() {
        return childrenMenuDtoList;
    }

    public void setChildrenMenuDtoList(List<MenuDto> childrenMenuDtoList) {
        this.childrenMenuDtoList = childrenMenuDtoList;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
