package de.futurecompany.newsarticle.infrastructure.repository;

import de.futurecompany.newsarticle.domain.NewsArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsArticleRepository extends CrudRepository<NewsArticle,String> {

//    private static final List<NewsArticle> staticArticles = Collections.singletonList(
//        new NewsArticle("1", "Very nice article", "Hello there. I'm not really interesting to read without images", Collections.singletonList(new ArticleAuthor("author1", "Stefan B.")))
//    );
//
//    public Mono<NewsArticle> get(String articleId) {
//        Optional<NewsArticle> article = staticArticles.stream().filter((a) -> a.getArticleId().equals(articleId)).findFirst();
//
//        return Mono.just(article).flatMap(Mono::justOrEmpty);
//    }
//
//    public Flux<NewsArticle> list() {
//        return Flux.fromIterable(staticArticles);
//    }
}
