import javax.swing.JTextArea;

public class PunctuationCounter {

    public static void countPunctuation(String inputText, JTextArea punctuationTextArea) {
        StringBuilder punctuationBuilder = new StringBuilder();
        int punctuationCount = 0;

        for (char c : inputText.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                punctuationBuilder.append(c).append(" ");
                punctuationCount++;
            }
        }

        punctuationTextArea.setText("Кол-во знаков препинания: " + punctuationCount + "\n\nЗнаки препинания: \n" + punctuationBuilder.toString());
    }
}
