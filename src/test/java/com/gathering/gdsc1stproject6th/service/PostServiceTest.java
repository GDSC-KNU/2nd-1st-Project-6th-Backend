package com.gathering.gdsc1stproject6th.service;

import com.gathering.gdsc1stproject6th.domain.Category;
import com.gathering.gdsc1stproject6th.domain.User;
import com.gathering.gdsc1stproject6th.dto.CategoryDto;
import com.gathering.gdsc1stproject6th.dto.PostDto;
import com.gathering.gdsc1stproject6th.dto.UserDto;
import com.gathering.gdsc1stproject6th.repository.CategoryRepository;
import com.gathering.gdsc1stproject6th.repository.PostRepository;
import com.gathering.gdsc1stproject6th.repository.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void savePost() {
        //give
        User user = User.builder()
                .userId(1L)
                .userLv(1L)
                .userNm("KIM")
                .userNickname("dy")
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .categoryId(1L)
                .categoryNm("exercise")
                .build();

        categoryRepository.save(category);


        UserDto userDto = UserDto.builder()
                .userId(1L)
                .userLv(1L)
                .userNm("KIM")
                .userNickname("dy")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(1L)
                .categoryNm("exercise")
                .build();

        PostDto postDto = PostDto.builder()
                .title("bye")
                .chatLink("www.naver.com")
                .userLen(4)
                .curUserLen(1)
                .meetTime("3시")
                .meetPlace("bukgu")
                .content("this is test")
                .fastYN(true)
                .activeYN(true)
                .userDto(userDto)
                .categoryDto(categoryDto)
                .build();


        //when
        long postId = postService.savePost(postDto);

        //then
        Assertions.assertThat(postRepository.findById(postId).get().getUser().getUserId()).isEqualTo(postDto.getTitle());

    }
}