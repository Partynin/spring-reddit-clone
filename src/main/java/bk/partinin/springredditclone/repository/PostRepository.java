package bk.partinin.springredditclone.repository;

import bk.partinin.springredditclone.model.Post;
import bk.partinin.springredditclone.model.Subreddit;
import bk.partinin.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
