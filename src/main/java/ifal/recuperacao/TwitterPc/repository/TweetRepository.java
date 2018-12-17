package ifal.recuperacao.TwitterPc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifal.recuperacao.TwitterPc.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer>{

}
