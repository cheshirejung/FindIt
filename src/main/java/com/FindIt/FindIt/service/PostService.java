package com.FindIt.FindIt.service;

import com.FindIt.FindIt.entity.PostEntity;
import com.FindIt.FindIt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }
    public Optional<PostEntity> findById(Long id) {
        return postRepository.findById(id);
    }

}
