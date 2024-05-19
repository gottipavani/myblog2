package com.mybolg2.service;

import com.mybolg2.payload.PostDto;

import java.util.List;

public interface PostService {
 PostDto createPost(PostDto postDto);

 //return type of this should b list
 List<PostDto> getAllPosts();

 PostDto getPostById(long id);

 PostDto updatePost(PostDto postDto, long id);
}
