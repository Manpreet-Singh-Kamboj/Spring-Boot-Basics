package com.blogapp.services.implementations;

import com.blogapp.entities.Comment;
import com.blogapp.entities.Like;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.Like.LikeDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.LikeRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImplementation implements LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    public LikeServiceImplementation(PostRepository postRepository,UserRepository userRepository,CommentRepository commentRepository,LikeRepository likeRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }
    @Override
    public void toggleLikeOnPost(LikeDto likeDto) {
        Post post = this.postRepository.findById(likeDto.getPost().getId()).orElseThrow(()-> new ResourceNotFoundException("Post","Id",likeDto.getPost().getId()));
        User user = this.userRepository.findById(likeDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",likeDto.getUser().getId()));
        Optional<Like> existingLike = this.likeRepository.findByUserIdAndPostId(user.getId(),post.getId());
        if(existingLike.isPresent()){
            user.getLikes().remove(existingLike.get());
            post.getLikes().remove(existingLike.get());
            this.likeRepository.delete(existingLike.get());
        }else{
            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(user);
            this.likeRepository.save(newLike);
        }
    }

    @Override
    public void toggleLikeOnComment(LikeDto likeDto) {
        Comment comment = this.commentRepository.findById(likeDto.getComment().getId()).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",likeDto.getComment().getId()));
        User user = this.userRepository.findById(likeDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",likeDto.getUser().getId()));
        Optional<Like> existingLike = this.likeRepository.findByUserIdAndCommentId(user.getId(),comment.getId());
        if(existingLike.isPresent()){
            user.getLikes().remove(existingLike.get());
            comment.getLikes().remove(existingLike.get());
            this.likeRepository.delete(existingLike.get());
        }else{
            Like newLike = new Like();
            newLike.setComment(comment);
            newLike.setUser(user);
            this.likeRepository.save(newLike);
        }
    }
}
