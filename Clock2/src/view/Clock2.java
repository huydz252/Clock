package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock2 extends JFrame {
    private JLabel timeLabel;
    private JTextField timeZoneField;

    public Clock2() {
        setTitle("World Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(547, 300);
        setLocationRelativeTo(null);

        // Label hiển thị thời gian
        timeLabel = new JLabel();
        timeLabel.setBounds(153, 38, 245, 100);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateTime();
        getContentPane().setLayout(null);
        getContentPane().add(timeLabel);
        JLabel label = new JLabel("Time Zone:");
        label.setBounds(21, 182, 116, 43);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(label);
        timeZoneField = new JTextField(20);
        timeZoneField.setBounds(147, 186, 166, 35);
        timeZoneField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        getContentPane().add(timeZoneField);
        
                JButton openButton = new JButton("Open");
                openButton.setBounds(360, 165, 136, 77);
                openButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
                getContentPane().add(openButton);
                openButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String timeZone = timeZoneField.getText();
                        if (!timeZone.isEmpty()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    new WorldClock(timeZone).setVisible(true);
                                }
                            }).start();
                        } else {
                            JOptionPane.showMessageDialog(Clock2.this, "Please enter a time zone.");
                        }
                    }
                });

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(dateFormat.format(new Date()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Clock2().setVisible(true);
            }
        });
    }
    
    class WorldClock extends JFrame {
        private JLabel timeLabel;

        public WorldClock(String timeZone) {
            this.setSize(547, 300);
            this.setLocationRelativeTo(null);

            initializeUI(timeZone);
            
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTime(timeZone);
                }
            });
            timer.start();
        }

        private void initializeUI(String timeZone) {
            
            getContentPane().setLayout(null);

            timeLabel = new JLabel();
            timeLabel.setBounds(153, 38, 245, 100);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
            timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            updateTime(timeZone);
            getContentPane().add(timeLabel);

            JLabel label = new JLabel("Time Zone:");
            label.setBounds(21, 182, 116, 43);
            label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            getContentPane().add(label);

            JTextField timeZoneField = new JTextField(20);
            timeZoneField.setBounds(147, 186, 166, 35);
            timeZoneField.setFont(new Font("Tahoma", Font.PLAIN, 20));
            getContentPane().add(timeZoneField);

            JButton openButton = new JButton("Open");
            openButton.setBounds(360, 165, 136, 77);
            openButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            getContentPane().add(openButton);
            openButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String timeZone = timeZoneField.getText();
                    if (!timeZone.isEmpty()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new WorldClock(timeZone).setVisible(true);
                            }
                        }).start();
                    } else {
                        JOptionPane.showMessageDialog(WorldClock.this, "Please enter a time zone.");
                    }
                }
            });
        }

        private void updateTime(String timeZone) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(java.util.TimeZone.getTimeZone(timeZone));
            timeLabel.setText(dateFormat.format(new Date()));
        }
    }
private void updateTime(String timeZone) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    dateFormat.setTimeZone(java.util.TimeZone.getTimeZone(timeZone));
    timeLabel.setText(dateFormat.format(new Date()));
}
}


