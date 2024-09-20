import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static AtomicInteger counterPrettyWordWithLength3 = new AtomicInteger(0);
    private static AtomicInteger counterPrettyWordWithLength4 = new AtomicInteger(0);
    private static AtomicInteger counterPrettyWordWithLength5 = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }


        Thread threadForCheckIsWordPallindrom = new Thread(() -> {
            for (String word : texts) {
                if (isPalindrom(word)) {
                    switch (word.length()) {
                        case 3: {
                            counterPrettyWordWithLength3.addAndGet(1);
                            break;
                        }
                        case 4: {
                            counterPrettyWordWithLength4.addAndGet(1);
                            break;
                        }
                        case 5: {
                            counterPrettyWordWithLength5.addAndGet(1);
                            break;
                        }
                    }
                }
            }
        });
        Thread threadForCheckIsWordContainsOnlyOneSymbol = new Thread(() -> {
            for (String word : texts) {
                if (isOnlyOneSymbol(word)) {
                    switch (word.length()) {
                        case 3: {
                            counterPrettyWordWithLength3.addAndGet(1);
                            break;
                        }
                        case 4: {
                            counterPrettyWordWithLength4.addAndGet(1);
                            break;
                        }
                        case 5: {
                            counterPrettyWordWithLength5.addAndGet(1);
                            break;
                        }
                    }
                }
            }
        });
        Thread threadForCheckIsRisingSymbolsInWord = new Thread(() -> {
            for (String word : texts) {
                if (isRising(word)) {
                    switch (word.length()) {
                        case 3: {
                            counterPrettyWordWithLength3.addAndGet(1);
                            break;
                        }
                        case 4: {
                            counterPrettyWordWithLength4.addAndGet(1);
                            break;
                        }
                        case 5: {
                            counterPrettyWordWithLength5.addAndGet(1);
                            break;
                        }
                    }
                }
            }
        });
        threadForCheckIsWordPallindrom.start();
        threadForCheckIsWordContainsOnlyOneSymbol.start();
        threadForCheckIsRisingSymbolsInWord.start();

        threadForCheckIsWordPallindrom.join();
        threadForCheckIsWordContainsOnlyOneSymbol.join();
        threadForCheckIsRisingSymbolsInWord.join();

        System.out.println("Counter pretty word with length 3 : " + counterPrettyWordWithLength3);
        System.out.println("Counter pretty word with length 4 : " + counterPrettyWordWithLength4);
        System.out.println("Counter pretty word with length 5 : " + counterPrettyWordWithLength5);
    }

    //метод для провреки слова на палидром
    public static boolean isPalindrom(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    //метод для проверки на то ,Что слово состоит из одной и той же буквы
    public static boolean isOnlyOneSymbol(String word) {
        for (char symbol : word.toCharArray()) {
            if (symbol != word.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    //метод для проверки слова на то ,что все буквы в слове идут по возрастающей
    public static boolean isRising(String word) {
        for (int counter = 0; counter < word.length() - 1; counter++) {
            if ((int) word.charAt(counter) > (int) word.charAt(counter + 1)) {
                return false;
            }
        }
        return true;
    }

    //метод лдя генерации слов
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}