package bk.partinin.springredditclone.repository;

import bk.partinin.springredditclone.model.Post;
import bk.partinin.springredditclone.model.User;
import bk.partinin.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
