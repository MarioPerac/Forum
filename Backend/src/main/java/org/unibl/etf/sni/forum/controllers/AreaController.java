package org.unibl.etf.sni.forum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.services.AreaService;

import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService){
        this.areaService = areaService;
    }
    @GetMapping("/{name}/comments")
    public List<Comment> getComments(@RequestParam String name){
        return areaService.getComments(name);
    }
}
