package com.wxweven.concurrent;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author wxweven
 * @date 2014年9月10日
 * @version 1.0
 * @email wxweven@163.com
 * @blog http://wxweven.blog.163.com/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description:Illustrate Unresponsive UI problem caused by "busy"
 *                         Event-Dispatching Thread
 */
public class UnresponsiveUI extends JFrame {
    private boolean    stop  = false; // start or stop the counter
    private JTextField tfCount;
    private int        count = 1;

    /** Constructor to setup the GUI components */
    public UnresponsiveUI() {
        Container cp = this.getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cp.add(new JLabel("Counter"));
        tfCount = new JTextField(count + "", 10);
        tfCount.setEditable(false);
        cp.add(tfCount);

        /**
         * add start button and start event
         */
        JButton btnStart = new JButton("Start Counting");
        cp.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                stop = false;
                for (int i = 0; i < 100000; ++i) {
                    if (stop)
                    {
                        break; // check if STOP button has been pushed,
                    }
                    // which changes the stop flag to true
                    tfCount.setText(count + "");
                    ++count;
                }
            }
        });

        /**
         * add stop button and stop event
         */
        JButton btnStop = new JButton("Stop Counting");
        cp.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                stop = true; // set the stop flag
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Counter");
        setSize(300, 120);
        setVisible(true);
    }

    /** The entry main method */
    public static void main(String[] args) {
        // Run GUI codes in Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UnresponsiveUI(); // Let the constructor do the job
            }
        });
    }
}