package com.FindIt.FindIt.service;

import com.FindIt.FindIt.dto.CommentDto;
import com.FindIt.FindIt.entity.CommentEntity;
import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.entity.UserEntity;
import com.FindIt.FindIt.repository.CommentRepository;

import com.FindIt.FindIt.repository.PostRepository;
import com.FindIt.FindIt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto createComment(Long postId,CommentDto commentDto) {

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        UserEntity user = userRepository.findLoginUserByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());

        if(commentDto.getParentCommentId() == null){
            CommentEntity parentComment = null;
            CommentEntity savedComment = commentRepository.save(commentDto.toEntity(user,post,parentComment));
            return CommentDto.fromEntity(savedComment);
        }

        CommentEntity parentComment = commentRepository.findById(commentDto.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found"));



        CommentEntity savedComment = commentRepository.save(commentDto.toEntity(user,post,parentComment));

        return CommentDto.fromEntity(savedComment);
    }

    public CommentDto updateComment(Long commentId, CommentDto commentDto) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setBody(commentDto.getBody());
        commentRepository.save(comment);
        return CommentDto.fromEntity(comment);
    }

/*    // 게시물의 모든 댓글 조회
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<CommentEntity> allComments = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);

        // 부모 댓글만
        List<CommentDto> parentComments = allComments.stream()
                .filter(comment -> comment.getParentCommentId() == null)
                .map(CommentDto::fromEntity)
                .collect(Collectors.toList());

        // 대댓글들을 해당하는 부모 댓글의 replies에 추가
        allComments.stream()
                .filter(comment -> comment.getParentCommentId() != null)
                .forEach(reply -> {
                    parentComments.stream()
                            .filter(parent -> parent.getCommentId().equals(reply.getParentCommentId()))
                            .findFirst()
                            .ifPresent(parent -> parent.getReplies().add(CommentDto.fromEntity(reply)));
                });

        return parentComments;
    }*/
}



