// Создаем класс для хранения символов и знаков препинания
class Symbol {
    char value; // Значение символа
    boolean punctuation; // Является ли символ знаком препинания

    // Конструктор класса
    public Symbol(char value, boolean punctuation) {
        this.value = value;
        this.punctuation = punctuation;
    }

    // Геттеры и сеттеры
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isPunctuation() {
        return punctuation;
    }

    public void setPunctuation(boolean punctuation) {
        this.punctuation = punctuation;
    }
}
