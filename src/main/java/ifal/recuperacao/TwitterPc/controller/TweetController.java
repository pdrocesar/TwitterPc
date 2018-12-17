package ifal.recuperacao.TwitterPc.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ifal.recuperacao.TwitterPc.exception.ResourceNotFoundException;
import ifal.recuperacao.TwitterPc.model.Tweet;
import ifal.recuperacao.TwitterPc.repository.TweetRepository;

@RestController
public class TweetController {
	
	@Autowired
	private TweetRepository tweetR;
	
	@GetMapping("/tweet")
	public Page<Tweet> retornarTweets(Pageable pageable){
		return tweetR.findAll(pageable);
	}
	
	@PostMapping("/tweet")
	public Tweet salvarTweet(@Valid @RequestBody Tweet tweet) {
		return tweetR.save(tweet);
	}
	
	@PutMapping("/tweet/{id}")
	public Tweet atualizarTweet(@PathVariable Integer id, @Valid @RequestBody Tweet tweetRequest) {
		return tweetR.findById(id).map(tweet -> {
			tweet.setDescricao(tweetRequest.getDescricao());
			
			return tweetR.save(tweet);
		}).orElseThrow(() -> new ResourceNotFoundException("tweet nao encontrado: " + id));
	}
	
	@DeleteMapping("/tweet/{id}")
	public ResponseEntity<?> deletarTweet(@PathVariable Integer id){
		return tweetR.findById(id)
				.map(tweet -> {
					tweetR.delete(tweet);
					
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("tweet nao encontrado: " + id));
	}

}
