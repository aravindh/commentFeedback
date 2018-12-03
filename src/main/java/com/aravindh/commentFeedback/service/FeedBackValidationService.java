package com.aravindh.commentFeedback.service;

import spark.utils.StringUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FeedBackValidationService {
    private static List<String> objectionableWords = Arrays.asList("worst", "bad");
    private static String SPACE = " ";

    public static List<String> validate(String body) {
        if(StringUtils.isEmpty(body)){
            return Collections.EMPTY_LIST;
        }
        List<String> feedback = new LinkedList<>();
        validateWords(body, feedback);
        return feedback;
    }

    private static void validateWords(String body, List<String> feedback) {
        String[] words = body.split(SPACE);
        for(String word : words){
            if(containsCaseInsensitive(objectionableWords, word)){
                feedback.add(word+": objectionable content");
            }
        }
    }

    private static boolean containsCaseInsensitive(List<String> objectionableWords, String word) {
        for(String objectionableWord:objectionableWords){
            if(word.equalsIgnoreCase(objectionableWord)){
                return true;
            }
        }
        return false;
    }

}
