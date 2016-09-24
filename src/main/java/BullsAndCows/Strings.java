package BullsAndCows;

/**
 * Created by onotole on 9/22/16.
 */
public class Strings {
    public static final String help = "Это классическая игра 'Быки и Коровы'.\n" +
            "Бот загадывает четырехзначное число без повторяющихся цифр. Ваша задача — угадать это число. \n" +
            "Вы делаете предположение и бот отвечает сколько там быков и коров. \n" +
            "Бык — в вашем числе и в загаданном цифра совпадает по значению и стоит на том же самом месте.\n" +
            "Корова — в вашем числе и загаданном цифра совпадает по значению, но стоит на другом месте.\n" +
            "\n" +
            "Например:\n" +
            "Загадано число: 1234\n" +
            "Попытка: 2834 - 2 быка (3 и 4 совпали и ) и 1 корова (2 совпала по значению, но стоит на другом месте. \n" +
            "\n" +
            "Начать игру: " + Commands.start + "\n" +
            "Сделать предположение: просто вводи " + Games.getSize() + "-х значные числа после начала игры\n" +
            "(В некоторых, больших чатах, бот не видит все сообщения и надо вводить '/ число'\n" +
            "Справка: " + Commands.help + "\n" +
            "Подробнее: https://ru.wikipedia.org/wiki/Быки_и_коровы";
    public static final String zeroBull = "быков";
    public static final String oneBull = "бык";
    public static final String manyBulls = "быка";
    public static final String zeroCow = "коров";
    public static final String oneCow = "корова";
    public static final String manyCows = "коровы";
    public static final String win = "Ты правильно угадал число!";
    public static final String startGame = "Игра началась!";
    public static final String notStartYet = "Ты еще не начал игру!";
    public static final String useCorrectNumber = "Не пытайся обмануть меня, человечишко! Вводи корректное, четырехзначное число";
    public static final String gameAlreadyStarted = "В этом чате уже начата игра";
    public static final String dublicateProhibited = "Нельзя использовать числа с повторяющимися цифрами";
    public static final String and = "и";
}
