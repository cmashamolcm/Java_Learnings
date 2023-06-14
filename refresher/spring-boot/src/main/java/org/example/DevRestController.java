package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.util.List;

//@Controller - helps the handler-mapping to map all endpoints
@RestController
public class DevRestController {
    final String etagString ="This is etag final";
    @Autowired
    DeveloperRepo repo;
//    @RequestMapping("/dev-rest/{dev-id}")
//    @ResponseBody
//    public String getDev(@PathVariable("dev-id") Long id){
//        System.out.println(id);
//        return "found";
//    }

    @RequestMapping(value = "/dev-rest/{dev-id}", produces = "application/xml")// now if client asks for json, it throws 405 - not acceptable error.
    @ResponseBody
    public DevModel getDev(@PathVariable("dev-id") Long id){
        System.out.println(id);
        return repo.findById(id).get();
    }


//    @PostMapping(value = "/dev-rest")
//    //@ResponseBody - can be replaced with RestController which says this is controller and all endpoints under that returns data. Not view.
//    public DevModel saveDev(DevModel model){// accepts as form-data also. 'Content-Type' header
//        System.out.println(model);
//        return repo.save(model);
//    }

    @PostMapping(value = "/dev-rest", consumes = "application/xml")// POST method + request mapping If any other format for body, 415 - UnsupportedMediaType
    //@ResponseBody - can be replaced with RestController which says this is controller and all endpoints under that returns data. Not view.
    public DevModel saveDev(@RequestBody DevModel model){// accepts as only xml also.
        System.out.println(model);
        return repo.save(model);
    }

    @GetMapping("/list")
    public List<DevModel> getList(@RequestParam("offset") int offset, @RequestParam(name="limit", defaultValue = "5") int limit){
        System.out.println(offset);
        Pageable conditions = PageRequest.of(offset, limit);
        Page<DevModel> page = repo.findAll(conditions);
        return page.getContent();

    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println(new String(file.getBytes()));
//        return ResponseEntity.accepted().build();
//    }

    @PostMapping("/upload")
    public ResponseEntity<Resource> upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(new String(file.getBytes()));
        Resource resource = new FileSystemResource("pom.xml");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=abc.txt")
                .body(resource);
    }

    @GetMapping("/redirect")
    public ResponseEntity navigate(){
        var header = new HttpHeaders();
        header.setLocation(URI.create("https://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect"));
        var result =  new ResponseEntity(header, HttpStatus.MOVED_PERMANENTLY);//HttpStatus.FOUND gives 200 ok as end response even if redirected.
        return result;

    }

    @GetMapping("/etag/{input}")
    public String etagTest(@PathVariable String input){
         //return input;

        return input;
    }


}
