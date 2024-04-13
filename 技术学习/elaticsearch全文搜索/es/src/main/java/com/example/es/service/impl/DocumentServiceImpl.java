package com.example.es.service.impl;


import cn.easyes.common.utils.StringUtils;
import cn.easyes.core.conditions.select.LambdaEsQueryWrapper;
import com.example.es.entity.Document;
import com.example.es.mapper.DocumentMapper;
import com.example.es.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentServiceImpl implements IDocumentService {

    private final DocumentMapper documentMapper;

    /**
     * 查询ES所有数据
     * @return 查询Document结果对象集合
     */
    @Override
    public List<Document> findAllData() {
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchAllQuery();
        return documentMapper.selectList(wrapper);
    }

    /**
     * 创建索引
     * @return 结果信息
     * @throws Exception
     */
    @Override
    public String createIndex() throws Exception {
        StringBuilder msg = new StringBuilder();
        String indexName = Document.class.getSimpleName().toLowerCase();
        boolean existsIndex = documentMapper.existsIndex(indexName);
        if (existsIndex){
            throw new Exception("Document实体对应索引已存在,删除索引接口：deleteIndex");
        }
        boolean success = documentMapper.createIndex();
        if (success){
            msg.append("Document索引创建成功");
        }else {
            msg.append("索引创建失败");
        }
        return msg.toString();
    }

    /**
     * 删除索引
     * @return 结果信息
     */
    @Override
    public String deleteIndex() {
        StringBuilder msg = new StringBuilder();
        String indexName = Document.class.getSimpleName().toLowerCase();
        if (documentMapper.deleteIndex(indexName)){
            msg.append("删除成功");
        }else {
            msg.append("删除失败");
        }
        return msg.toString();
    }

    /**
     * ES新增数据
     * @param document 新增数据实体类
     * @return 结果信息
     * @throws Exception
     */
    @Override
    public String addData(Document document) throws Exception {
        if (StringUtils.isEmpty(document.getTitle()) || StringUtils.isEmpty(document.getContent())) {
            throw new Exception("请补全title及content数据");
        }
        document.setCreateTime(new Date());
        documentMapper.insert(document);
        return "Added successfully！";
    }

    /**
     * 根据id删除ES数据
     * @param id 需要删除的数据的id
     * @return
     */
    @Override
    public String deleteDataById(String id) {
        documentMapper.deleteById(id);
        return "Success";
    }

    @Override
    public String deleteData(Document document) {
        documentMapper.deleteById(document.getId());
        return "Success";
    }
    /**
     * 修改ES数据
     * @param document 修改数据对象
     */
    @Override
    public String updateData(Document document) {
        documentMapper.updateById(document);
        return "Success";
    }


    /**
     * 分词匹配查询content字段
     * @param value 查询内容
     * @return
     */
    @Override
    public List<Document> findMatch(String value) {
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(Document::getContent,value);
        wrapper.orderByDesc(Document::getCreateTime);
        List<Document> documents = documentMapper.selectList(wrapper);
        return documents;
    }
}

