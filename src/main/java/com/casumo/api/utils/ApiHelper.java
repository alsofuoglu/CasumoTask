package com.casumo.api.utils;

public class ApiHelper {

  /**
   * Extract the vowels from String
   *
   * @param String
   */
  public static String extractVowels(String input) {
    String notVowel = "";
    int length = input.length();
    for (int z = 0; z < length; z++) {
      if (!isVowel(input.charAt(z))) {
        char character = input.charAt(z);
        notVowel = notVowel + character;
      }
    }
    return notVowel;
  }

  /**
   * Check if char is vowel
   *
   * @param char
   */
  public static boolean isVowel(char letter) {
    if (letter == 'a') {
      return true;
    } else if (letter == 'e') {
      return true;
    } else if (letter == 'i') {
      return true;
    } else if (letter == 'o') {
      return true;
    } else if (letter == 'u') {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Extract the vowels from String by recursive way
   *
   * @param String
   */
  public static String extractVowelsRecursive(String input) {
    if (input.length() == 0) {
      return input;
    }
    char letter = input.charAt(0);
    if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
      return extractVowelsRecursive(input.substring(1));
    } else {
      return letter + extractVowelsRecursive(input.substring(1));
    }
  }
}
