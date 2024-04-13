package com.example.es.controller;

import com.example.es.entity.Document;
import com.example.es.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentController {

    private final IDocumentService documentService;

    /**
     * 创建索引
     * @return 结果信息
     * @throws Exception
     */
    @GetMapping("/createIndex")
    public String createIndex() throws Exception {
        return documentService.createIndex();
    }

    /**
     * 删除索引
     * @return 结果信息
     */
    @GetMapping("/deleteIndex")
    public String deleteIndex(){
        return documentService.deleteIndex();
    }

    /**
     * 查询ES所有数据
     * @return 查询Document结果对象集合
     */
    @GetMapping("/findAll")
    public List<Document> findAll(){
        return documentService.findAllData();
    }

    /**
     * ES新增数据
     * @param document 新增数据对象
     * @return 结果信息
     * @throws Exception
     */
    @GetMapping("/add")
    public String addData(Document document) throws Exception {
        return documentService.addData(document);
    }

    /**
     * 修改ES数据
     * @param document 修改数据对象
     */
    @GetMapping("/update")
    public String updateData(Document document){
        return documentService.updateData(document);
    }

    /**
     * 根据id删除ES数据
     * @param id 需要删除的数据的id
     * @return
     */
    @GetMapping("/delete")
    public String deleteData(String id){
        return documentService.deleteDataById(id);
    }

    /**
     * 分词匹配查询content字段
     * @param value 查询内容
     * @return
     */
    @GetMapping("/match")
    public List<Document> findMatch(@RequestParam String value){
        return documentService.findMatch(value);
    }

}
