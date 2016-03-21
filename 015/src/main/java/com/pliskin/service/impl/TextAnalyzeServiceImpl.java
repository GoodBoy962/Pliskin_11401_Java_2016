package com.pliskin.service.impl;

import com.pliskin.service.TextAnalyzeService;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrpliskin on 21.03.16.
 */
@Service
public class TextAnalyzeServiceImpl implements TextAnalyzeService {

    @Override
    public String analyzeText(String text, String operation) {
        String result = "";
        switch (operation) {
            case "symbols-cnt":
                result = symbolsCounter(text);
                break;
            case "words-cnt":
                result = wordsCounter(text);
                break;
            case "sentences-cnt":
                result = sentencesCounter(text);
                break;
            case "paragraphs-cnt":
                result = paragraphsCounter(text);
                break;
        }
        return result;
    }

    private String paragraphsCounter(String text) {
        return String.valueOf(text.split("\\t+").length);
    }

    private String sentencesCounter(String text) {
        return String.valueOf(text.split("\\.|!|\\?").length);
    }

    private String wordsCounter(String text) {
        return String.valueOf((text.split(" ")).length);
    }

    private String symbolsCounter(String text) {
        return String.valueOf(text.length());
    }
}
