package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto){
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    /**
     *
     * @param post
     * @return
     */
    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setContent(post.getContent());
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    /**
     *
     * @param postDto
     * @return
     */
    private Post mapFromDtoToPost(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        User user = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No User logged in "));

        post.setUsername(user.getUsername());
        post.setCreatedOn(Instant.now());
        return post;
    }

    public PostDto getPostById(Long id ) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        PostDto postDto = mapFromPostToDto(post);
        return postDto;
    }
}
