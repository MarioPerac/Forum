package org.unibl.etf.sni.forum.base;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.sni.forum.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;


@Getter
public abstract class CrudController <ID extends Serializable, REQ, RESP> {


    private final CrudService<ID> crudService;
    private final Class<RESP> respClass;

    protected CrudController(CrudService<ID> crudService, Class<RESP> respClass) {
        this.crudService = crudService;
        this.respClass = respClass;
    }


    @GetMapping
    public List<RESP> findAll(){
        return crudService.findAll(respClass);
    }

    @GetMapping("/{id}")
    public RESP findById(@PathVariable ID id){
        return crudService.findById(id, respClass);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RESP insert(@RequestBody REQ object){
        return crudService.insert(object, respClass);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RESP> insert(@RequestBody List<REQ> objects) {
           return crudService.insertAll(objects, respClass);
    }
    @PutMapping("/{id}")
    public RESP update(@PathVariable ID id,@RequestBody REQ object) throws NotFoundException {
        return crudService.update(id, object, respClass);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) throws NotFoundException {
        crudService.delete(id);
    }
}
