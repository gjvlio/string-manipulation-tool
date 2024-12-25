import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class StringManipulationToolkit {
    public static void main(String[] args) {
        while (true) {
            // Get the user input string
            String strUserInput = JOptionPane.showInputDialog(null,
                    "Input your word(s) here:", "Enter String",
                    JOptionPane.QUESTION_MESSAGE);

            // Exit if the user cancels or enters an empty string
            if (strUserInput == null || strUserInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Menu loop
            while (true) {
                // Create a custom panel with GridBagLayout
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10); // Spacing between buttons
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0; // Center horizontally

                // Define menu options
                String[] options = {
                        "String Reversal", "Palindrome Checker", "Anagram Checker",
                        "Word Count", "Character Count", "Substring Finder",
                        "Lower Case Converter", "Upper Case Converter",
                        "Vowel Remover", "Consonant Remover"
                };

                for (int i = 0; i < options.length; i++) {
                    final int index = i; // Create a final variable to store the current index
                    JButton button = new JButton(options[i]);
                    button.addActionListener(_ -> {
                        handleAction(options[index], strUserInput);
                        // Immediately return to the string input dialog after an action is taken
                        throw new RuntimeException("Reset to String Input");
                    });
                    gbc.gridy = i; // Vertical position for each button
                    panel.add(button, gbc);
                }

                // Display menu in a dialog
                try {
                    JOptionPane.showConfirmDialog(null, panel, "String Manipulation Toolkit",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                } catch (RuntimeException e) {
                    if ("Reset to String Input".equals(e.getMessage())) {
                        break; // Exit the menu loop and go back to the string input
                    } else {
                        throw e; // Propagate other exceptions
                    }
                }
            }
        }
    }

    private static void handleAction(String action, String strUserInput) {
        switch (action) {
            case "String Reversal" -> JOptionPane.showMessageDialog(null,
                    "Reversed String: " + stringReversal(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Palindrome Checker" -> JOptionPane.showMessageDialog(null,
                    palindromeChecker(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Anagram Checker" -> JOptionPane.showMessageDialog(null,
                    anagramChecker(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Word Count" -> JOptionPane.showMessageDialog(null,
                    wordCount(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Character Count" -> JOptionPane.showMessageDialog(null,
                    characterCount(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Substring Finder" -> JOptionPane.showMessageDialog(null,
                    substringFinder(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Lower Case Converter" -> JOptionPane.showMessageDialog(null,
                    "Lower Case: " + strUserInput.toLowerCase(), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Upper Case Converter" -> JOptionPane.showMessageDialog(null,
                    "Upper Case: " + strUserInput.toUpperCase(), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Vowel Remover" -> JOptionPane.showMessageDialog(null,
                    "Without Vowels: " + vowelRemover(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
            case "Consonant Remover" -> JOptionPane.showMessageDialog(null,
                    "Without Consonants: " + consonantRemover(strUserInput), "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Utility methods remain the same
    public static String stringReversal(String strInput) {
        return new StringBuilder(strInput).reverse().toString();
    }

    public static String palindromeChecker(String strInput) {
        String cleanedInput = strInput.replaceAll("\\s+", "").toLowerCase();
        return cleanedInput.equals(stringReversal(cleanedInput)) ?
                "The input is a palindrome." : "The input is NOT a palindrome.";
    }

    public static String anagramChecker(String strInput1) {
        String strInput2 = JOptionPane.showInputDialog(null,
                "Enter another string to compare:", "Anagram Checker",
                JOptionPane.QUESTION_MESSAGE);

        if (strInput2 == null || strInput2.isEmpty()) {
            return "No comparison string provided.";
        }

        strInput1 = strInput1.replaceAll("\\s+", "").toLowerCase();
        strInput2 = strInput2.replaceAll("\\s+", "").toLowerCase();

        char[] arr1 = strInput1.toCharArray();
        char[] arr2 = strInput2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2) ?
                "The input is an anagram." : "The input is NOT an anagram.";
    }

    public static String wordCount(String strInput) {
        String[] words = strInput.trim().split("\\s+");
        return "Words: " + Arrays.toString(words) + "\nCount: " + words.length;
    }

    public static String characterCount(String strInput) {
        String cleanedInput = strInput.replaceAll("\\s+", "");
        return "Without spaces: " + cleanedInput + "\nCharacter Count: " + cleanedInput.length();
    }

    public static String substringFinder(String strInput) {
        String substring = JOptionPane.showInputDialog(null,
                "Enter a substring to find:", "Substring Finder",
                JOptionPane.QUESTION_MESSAGE);

        if (substring == null || substring.isEmpty()) {
            return "No substring provided.";
        }

        int index = strInput.indexOf(substring);
        return index == -1 ?
                "Substring not found." :
                "Substring found at index " + index + ".";
    }

    public static String vowelRemover(String strInput) {
        return strInput.replaceAll("[AEIOUaeiou]", "");
    }

    public static String consonantRemover(String strInput) {
        return strInput.replaceAll("[BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz]", "");
    }
}
