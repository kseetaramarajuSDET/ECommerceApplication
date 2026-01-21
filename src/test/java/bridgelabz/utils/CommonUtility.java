package bridgelabz.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtility {
    public static String randomString(int noOfCharacters) {
        return RandomStringUtils.randomAlphabetic(noOfCharacters);
    }

    public static String randomNumber(int noOfDigits) {
        return RandomStringUtils.randomNumeric(noOfDigits);
    }

    public static String randomPassword(int noOfCharacters) {
        return   RandomStringUtils.randomAlphabetic(noOfCharacters/2)+"@"+RandomStringUtils.randomNumeric(noOfCharacters/2) ;
    }
}
