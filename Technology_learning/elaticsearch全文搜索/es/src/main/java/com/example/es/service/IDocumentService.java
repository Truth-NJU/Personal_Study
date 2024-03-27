package com.example.es.service;
import com.example.es.entity.Document;

import java.util.List;

public interface IDocumentService {

    /**
     * 查询ES所有数据
     * @return 查询Document结果对象集合
     */
    List<Document> findAllData();

    /**
     * 创建索引
     * @return 结果信息
     * @throws Exception
     */
    String createIndex() throws Exception;

    /**
     * 删除索引
     * @return 结果信息
     */
    String deleteIndex();

    /**
     * ES新增数据
     * @param document 新增数据实体类
     * @return 结果信息
     * @throws Exception
     */
    String addData(Document document) throws Exception;

    /**
     * 根据id删除ES数据
     * @param id 需要删除的数据的id
     * @return
     */
    String deleteDataById(String id);

    String deleteData(Document document);

    /**
     * 修改ES数据
     * @param document 修改数据对象
     */
    String updateData(Document document);

    /**
     * 分词匹配查询content字段
     * @param value 查询内容
     * @return
     */
    List<Document> findMatch(String value);
}

