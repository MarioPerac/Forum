package org.unibl.etf.sni.forum.services;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.models.entites.AreaEntity;
import org.unibl.etf.sni.forum.repositories.AreaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService{

    private AreaRepository areaRepository;
    private ModelMapper modelMapper;

    public AreaService(AreaRepository repository, ModelMapper modelMapper){
        this.areaRepository = repository;
        this.modelMapper = modelMapper;
    }
    public List<Comment> getComments(String name){
       AreaEntity areaEntity = areaRepository.getByName(name);
       return areaEntity.getComments().stream().filter(c -> c.getPublished() && !c.getForbidden()).map(c -> modelMapper.map(c, Comment.class)).collect(Collectors.toList());
    }
}
