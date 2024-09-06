package org.unibl.etf.sni.forum.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.base.CrudJpaService;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.models.dto.Mail;
import org.unibl.etf.sni.forum.models.entites.CommentEntity;
import org.unibl.etf.sni.forum.models.entites.UserEntity;
import org.unibl.etf.sni.forum.models.requests.ActivationRequest;
import org.unibl.etf.sni.forum.models.requests.CommentRequest;
import org.unibl.etf.sni.forum.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService  extends CrudJpaService<CommentEntity, Integer> {

    private CommentRepository commentRepository;
    public CommentService(CommentRepository jpaRepository, ModelMapper modelMapper) {
        super(jpaRepository, CommentEntity.class, modelMapper);
        this.commentRepository = jpaRepository;
    }


    public List<Comment> getNewComments(){
        List<CommentEntity> entities = commentRepository.getAllByPublishedAndForbidden(false, false);

        return entities.stream().map(e -> modelMapper.map(e, Comment.class)).collect(Collectors.toList());
    }

    public void update(CommentRequest request){


       CommentEntity commentEntity = modelMapper.map(request, CommentEntity.class);
        commentEntity =commentRepository.saveAndFlush(commentEntity);
        entityManager.refresh(commentEntity);

    }
}
