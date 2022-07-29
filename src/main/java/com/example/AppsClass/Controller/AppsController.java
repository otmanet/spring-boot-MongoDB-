package com.example.AppsClass.Controller;




import com.example.AppsClass.Model.Classe;

import net.minidev.json.JSONObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/rest/school")
public class AppsController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @GetMapping("/getAllClass")
    public JSONObject listClasse()
    {
        List<Classe> ListClasse=mongoTemplate.findAll(Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("message", "Data is found");
        jsonObject.put("ListClasse", ListClasse);
        return jsonObject;
    }

    @PostMapping("/SaveClass")
    public JSONObject SaveClass(@RequestBody Classe cls)
    {
        mongoTemplate.save(cls);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        return jsonObject;
    }

    @GetMapping("/getClass/{id}")
    public  JSONObject GetAppName(@PathVariable(name = "id") String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("global.id").is(id));
        List<Classe> ClassFind = mongoTemplate.find(query, Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("Classe", ClassFind);
        return jsonObject;
    }

    @DeleteMapping("deleteClass/{id}")
    public  JSONObject DeleteApp(@PathVariable(name = "id") String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("global.id").is(id));
        mongoTemplate.remove(query, Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        return jsonObject;
    }

    @PostMapping("/SaveUpdateClass/{id}")
    public  JSONObject UpdateApp(@PathVariable(name = "id") String id,@RequestBody Classe NewClass){
        Query query = new Query();
        query.addCriteria(Criteria.where("global.id").is(id));
        Document doc = new org.bson.Document(); // org.bson.Document
        mongoTemplate.getConverter().write(NewClass, doc);
        Update update = Update.fromDocument(doc);
        mongoTemplate.upsert(query, update, Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        return jsonObject;

    }

    @PostMapping("/desable/{id}")
    public JSONObject desableApps(@PathVariable(name = "id") String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("global.id").is(id));
        Update update = new Update();
        update.set("active", false);
        mongoTemplate.upsert(query, update, Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        return jsonObject;
    }
    @PostMapping("/enable/{id}")
    public JSONObject enableApps(@PathVariable(name = "id") String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("global.id").is(id));
        Update update = new Update();
        update.set("active", true);
        mongoTemplate.upsert(query, update, Classe.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        return jsonObject;
    }

//    @GetMapping("/noIcon")
//    public JSONObject noIcon(){
//        Query query = new Query();
//        query.fields().exclude("global.logo");
//        query.addCriteria(Criteria.where("System").is(true));
//        List<App> AppFind = mongoTemplate.find(query, App.class);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", true);
//        jsonObject.put("App", AppFind);
//        return jsonObject;
//    }
}
