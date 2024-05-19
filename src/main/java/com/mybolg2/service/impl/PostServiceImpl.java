package com.mybolg2.service.impl;

import antlr.ASTNULLType;
import com.mybolg2.entity.Post;
import com.mybolg2.exception.ResourceNotFoundException;
import com.mybolg2.payload.PostDto;
import com.mybolg2.repository.PostRepository;
import com.mybolg2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=   mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto dto=mapToDto(newPost);
        return dto;
    }


    public Post mapToEntity(PostDto postDto) {
        Post post=new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
         post.setContent(postDto.getContent());

         return post;
     }
    PostDto mapToDto(Post post) {
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;

    }
    @Override
    public List<PostDto> getAllPosts() {
         List<Post> posts = postRepository.findAll();
      return   posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
         Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
         );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
         Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
         post.setId(postDto.getId());
         post.setTitle(postDto.getTitle());
         post.setDescription(postDto.getDescription());
         post.setContent(postDto.getContent());

        Post updatedPost= postRepository.save(post);
      return  mapToDto(updatedPost);
    }


}
