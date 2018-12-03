package com.aravindh.commentFeedback.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = JUnit4.class)
public class FeedBackValidationServiceTest {

    @Test
    public void validateWithObjectionableWordReturnsObjectionableWordsList(){
        FeedBackValidationService.init(Arrays.asList("worst", "bad"));
        List<String> feedbacks = FeedBackValidationService.validate("worst Bad product ever");
        Assert.assertEquals("worst"+": objectionable content", feedbacks.get(0));
        Assert.assertEquals("Bad"+": objectionable content", feedbacks.get(1));
    }

    @Test
    public void validateWithNoObjectionableWordReturnsObjectionableWordsList(){
        FeedBackValidationService.init(Arrays.asList("worst", "bad"));
        List<String> feedbacks = FeedBackValidationService.validate("Best product ever");
        Assert.assertEquals(0, feedbacks.size());
    }

    @Test
    public void validateWithEmptySentenceReturnsEmptyList(){
        FeedBackValidationService.init(Arrays.asList("worst", "bad"));
        List<String> feedbacks = FeedBackValidationService.validate("");
        Assert.assertEquals(0, feedbacks.size());
    }
}