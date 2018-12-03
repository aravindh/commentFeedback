package com.aravindh.commentFeedback.service;

import spark.utils.StringUtils;

import java.util.*;

public class FeedBackValidationService {
    private static List<String> objectionableWords = new LinkedList<>();
    private static String SPACE = " ";
    private static boolean isCacheUpdateInProcess = false;

    /*
    * Update the list of objectionable words list.
    * */
    public static void init(List<String> wordsToBeAdded){
        isCacheUpdateInProcess = true;
        objectionableWords.addAll(wordsToBeAdded);
        isCacheUpdateInProcess = false;
    }

    /*
    * Given a sentence checks whether it has any Objectionable words or not, if it has it returns the list of objectionable words, else it returns empty list
    * */
    public static List<String> validate(String body) {
        if(StringUtils.isEmpty(body)){
            return Collections.EMPTY_LIST;
        }
        if(isCacheUpdateInProcess){
            throw new IllegalArgumentException("Cache update in progress");
        }
        List<String> feedback = new LinkedList<>();
        validateWords(body, feedback);
        return feedback;
    }

    private static void validateWords(String body, List<String> feedback) {
        String[] words = body.split(SPACE);
        for(String word : words){
            if(objectionableWords.contains(word.toLowerCase())){
                feedback.add(word+": objectionable content");
            }
        }
    }

}
