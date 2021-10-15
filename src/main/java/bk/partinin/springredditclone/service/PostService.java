package bk.partinin.springredditclone.service;

import bk.partinin.springredditclone.dto.PostRequest;
import bk.partinin.springredditclone.dto.PostResponse;
import bk.partinin.springredditclone.exceptions.PostNotFoundException;
import bk.partinin.springredditclone.exceptions.SubredditNotFoundException;
import bk.partinin.springredditclone.mapper.PostMapper;
import bk.partinin.springredditclone.model.Post;
import bk.partinin.springredditclone.model.Subreddit;
import bk.partinin.springredditclone.model.User;
import bk.partinin.springredditclone.repository.PostRepository;
import bk.partinin.springredditclone.repository.SubredditRepository;
import bk.partinin.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

        return postRepository.save(postMapper.map(postRequest, subreddit, currentUser));
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));

        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getAllPosts() {

        return  postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);

        return posts.stream()
                .map(postMapper::mapToDto).
                collect(Collectors.toList());
    }

    public List<PostResponse> getPostByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return  postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
