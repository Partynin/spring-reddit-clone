package bk.partinin.springredditclone.repository;

import bk.partinin.springredditclone.model.Comment;
import bk.partinin.springredditclone.model.Post;
import bk.partinin.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
