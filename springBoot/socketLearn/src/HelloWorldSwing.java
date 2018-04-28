import javax.swing.*;

public class HelloWorldSwing {
    public static void createAndShowGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        JPanel panel = new JPanel();
        JButton btn = new JButton("测试按钮");
        panel.add(btn);
        frame.setContentPane(panel);
        JLabel label = new JLabel("hello world");
        frame.getContentPane().add(label);
        frame.setVisible(true);
    }
}
