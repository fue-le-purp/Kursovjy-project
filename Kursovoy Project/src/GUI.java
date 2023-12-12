import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea punctuationTextArea;

    public GUI() {
        setTitle("Счётчик Пунктуации");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        inputTextArea = new JTextArea();
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        add(inputScrollPane, BorderLayout.CENTER);

        punctuationTextArea = new JTextArea();
        punctuationTextArea.setEditable(false);
        JScrollPane punctuationScrollPane = new JScrollPane(punctuationTextArea);
        add(punctuationScrollPane, BorderLayout.SOUTH);

        JButton countButton = new JButton("Запустить программу");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PunctuationCounter.countPunctuation(inputTextArea.getText(), punctuationTextArea);
            }
        });
        add(countButton, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void init() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
}
