import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Создаем класс для графического интерфейса
class GUI extends JFrame {
    // Объявляем компоненты интерфейса
    private JTextArea inputArea; // Текстовая область для ввода текста
    private JButton runButton; // Кнопка для запуска обработки текста
    private JButton cancelButton; // Кнопка для отмены обработки текста
    private JButton exitButton; // Кнопка для выхода из программы
    private JTable outputTable; // Таблица для вывода знаков препинания
    private JLabel outputLabel; // Метка для вывода количества знаков препинания

    // Конструктор класса
    public GUI() {
        // Устанавливаем заголовок, размер и расположение окна
        setTitle("Программа для обработки текста");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Создаем панель для текстовой области и кнопок
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // Создаем текстовую область для ввода текста
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Создаем кнопку для запуска обработки текста
        runButton = new JButton("Запустить");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем текст из текстовой области
                String text = inputArea.getText();

                // Проверяем, что текст не пустой
                if (text.isEmpty()) {
                    // Выводим сообщение об ошибке
                    JOptionPane.showMessageDialog(GUI.this, "Введите текст для обработки", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Запускаем обработку текста
                    processText(text);
                }
            }
        });

        // Создаем кнопку для отмены обработки текста
        cancelButton = new JButton("Сброс");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Очищаем текстовую область, таблицу и метку
                inputArea.setText("");
                outputTable.setModel(new DefaultTableModel());
                outputLabel.setText("");
            }
        });

        // Создаем кнопку для выхода из программы
        exitButton = new JButton("Выход");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Закрываем окно программы
                dispose();
            }
        });

        // Добавляем кнопки на панель
        buttonPanel.add(runButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(exitButton);

        // Добавляем текстовую область и панель кнопок на панель ввода
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Создаем панель для таблицы и метки
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        // Создаем таблицу для вывода знаков препинания
        outputTable = new JTable();
        outputTable.setFillsViewportHeight(true);

        // Создаем метку для вывода количества знаков препинания
        outputLabel = new JLabel();
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputLabel.setFont(new Font("Arial", Font.BOLD, 20));



        // Добавляем таблицу и метку на панель вывода
        outputPanel.add(new JScrollPane(outputTable), BorderLayout.CENTER);
        outputPanel.add(outputLabel, BorderLayout.SOUTH);

        // Добавляем панели ввода и вывода на окно
        add(inputPanel, BorderLayout.WEST);
        add(outputPanel, BorderLayout.EAST);

        // Делаем окно видимым
        setVisible(true);
    }

    // Метод для обработки текста
    public void processText(String text) {
        // Создаем список для хранения символов и знаков препинания
        ArrayList<Symbol> symbols = new ArrayList<>();

        // Создаем счетчик для подсчета количества знаков препинания
        int punctuationCount = 0;

        // Проходим по каждому символу в тексте
        for (char c : text.toCharArray()) {
            // Проверяем, является ли символ знаком препинания
            if (c == '.' || c == ',' || c == ';' || c == ':' || c == '!' || c == '?' || c == '"' || c == '\'' || c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == '-' || c == '_') {
                // Создаем объект класса Symbol с пометкой, что это знак препинания
                Symbol symbol = new Symbol(c, true);

                // Добавляем объект в список
                symbols.add(symbol);

                // Увеличиваем счетчик знаков препинания
                punctuationCount++;
            } else {
                // Создаем объект класса Symbol с пометкой, что это не знак препинания
                Symbol symbol = new Symbol(c, false);

                // Добавляем объект в список
                symbols.add(symbol);
            }
        }

        // Создаем массив для хранения данных для таблицы
        Object[][] data = new Object[punctuationCount][2];

        // Создаем массив для хранения заголовков для таблицы
        Object[] columnNames = {"Знак препинания", "Позиция в тексте"};

        // Создаем переменную для хранения индекса строки в таблице
        int rowIndex = 0;

        // Проходим по списку символов и знаков препинания
        for (int i = 0; i < symbols.size(); i++) {
            // Получаем текущий объект из списка
            Symbol symbol = symbols.get(i);

            // Проверяем, является ли объект знаком препинания
            if (symbol.isPunctuation()) {
                // Записываем значение и позицию знака препинания в массив данных
                data[rowIndex][0] = symbol.getValue();
                data[rowIndex][1] = i + 1;

                // Увеличиваем индекс строки
                rowIndex++;
            }
        }

        // Создаем модель для таблицы с данными и заголовками
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Устанавливаем модель для таблицы
        outputTable.setModel(model);

        // Устанавливаем текст для метки с количеством знаков препинания
        outputLabel.setText("Количество знаков препинания в тексте: " + punctuationCount);
    }
}
