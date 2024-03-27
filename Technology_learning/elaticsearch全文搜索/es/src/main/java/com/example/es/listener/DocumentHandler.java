package com.example.es.listener;

import com.example.es.entity.Document;
import com.example.es.service.IDocumentService;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import javax.annotation.Resource;

@CanalTable("document")
@Component
public class DocumentHandler implements EntryHandler<Document> {

    @Resource
    private IDocumentService documentService;

    /**
     * mysql中数据有新增时自动执行
     * @param document 新增的数据
     */
    @Override
    public void insert(Document document) {
        try {
            documentService.addData(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * mysql中数据有修改时自动执行
     * @param before 修改前的数据
     * @param after 修改后的数据
     */
    @Override
    public void update(Document before, Document after) {
        documentService.updateData(after);
    }

    /**
     * mysql中数据有删除时自动执行
     * @param document 要删除的数据
     */
    @Override
    public void delete(Document document) {
        documentService.deleteData(document);
    }
}
