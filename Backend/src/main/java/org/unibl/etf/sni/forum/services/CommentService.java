package org.unibl.etf.sni.forum.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.base.CrudJpaService;
import org.unibl.etf.sni.forum.models.entites.CommentEntity;
import org.unibl.etf.sni.forum.repositories.CommentRepository;

@Service
public class CommentService  extends CrudJpaService<CommentEntity, Integer> {

    private CommentRepository commentRepository;
    public CommentService(CommentRepository jpaRepository, ModelMapper modelMapper) {
        super(jpaRepository, CommentEntity.class, modelMapper);
        this.commentRepository = jpaRepository;
    }
}
