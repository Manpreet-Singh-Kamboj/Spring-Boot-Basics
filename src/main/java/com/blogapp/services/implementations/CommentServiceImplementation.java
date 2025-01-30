package com.blogapp.services.implementations;

import com.blogapp.entities.Comment;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.InvalidCommentOwnerException;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.Comment.CommentDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    public CommentServiceImplementation(PostRepository postRepository,UserRepository userRepository,CommentRepository commentRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Post post = this.postRepository.findById(commentDto.getPost().getId()).orElseThrow(()-> new ResourceNotFoundException("Post","Id",commentDto.getPost().getId()));
        User user = this.userRepository.findById(commentDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",commentDto.getUser().getId()));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(String commentId,String userId,String comment) {
        Comment existingComment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
        if(!existingComment.getUser().getId().equals(userId)){
            throw new InvalidCommentOwnerException(existingComment.getId(),userId);
        }
        existingComment.setComment(comment);
        Comment updatedComment = this.commentRepository.save(existingComment);
        return this.modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(String commentId, String userId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if (!comment.getUser().getId().equals(userId)) {
            throw new InvalidCommentOwnerException(comment.getId(), userId);
        }
        Post post = comment.getPost();
        User user = comment.getUser();
        post.getComments().remove(comment);
        user.getComments().remove(comment);
        this.commentRepository.deleteById(comment.getId());
    }

}
