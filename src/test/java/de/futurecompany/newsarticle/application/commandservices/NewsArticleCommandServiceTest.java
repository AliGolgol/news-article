package de.futurecompany.newsarticle.application.commandservices;

import de.futurecompany.newsarticle.infrastructure.repository.NewsArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value={SpringExtension.class})
@SpringBootTest
class NewsArticleCommandServiceTest {
    @Autowired
    NewsArticleRepository repository;
    @Autowired
    NewsArticleCommandService service;

    NewsArticleCommandServiceTest() {
    }

    @BeforeEach
    public void setup() {
        this.service = new NewsArticleCommandService();
    }


}