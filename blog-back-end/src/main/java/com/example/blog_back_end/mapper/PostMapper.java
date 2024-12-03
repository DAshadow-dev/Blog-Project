package com.example.blog_back_end.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.blog_back_end.dto.request.PostCreationRequest;
import com.example.blog_back_end.dto.request.PostUpdateRequest;
import com.example.blog_back_end.dto.response.PostResponse;
import com.example.blog_back_end.model.Post;
import com.example.blog_back_end.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {
    //Map PostCreationRequest to Post
    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserFromId")
    Post toPost(PostCreationRequest request);

    //Map Post to PostResponse
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    PostResponse toPostResponse(Post post);

     // Update Post entity using PostUpdateRequest
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromRequest(PostUpdateRequest request, @MappingTarget Post post);

    @Named("mapUserFromId")
    default User mapUserFromId(String userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }

    
}
