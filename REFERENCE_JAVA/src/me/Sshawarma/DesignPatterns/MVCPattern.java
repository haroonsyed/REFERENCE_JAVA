package me.Sshawarma.DesignPatterns;

//Client uses controller to change a model and updates the view independently
//Separates the calculations and the interface

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Doesn't even know view exists, since the controller handles that
class CalculatorModel {

    private int result;

    public void add(int n1, int n2) {
        result = n1 + n2;
    }

    public void subtract(int n1, int n2) {
        result = n1-n2;
    }
    //And you can add the rest of the calculations here

    //A way for the view to receive the calculation
    public int getResult() {
        return result;
    }

}

//The view updates when the model is modified by the controller
class CalculatorView extends JFrame {

    //Tutorial decides to use swing
    private JTextField firstNum = new JTextField(10);
    private JTextField secondNum = new JTextField(10);
    private JButton addButton = new JButton("ADD");
    private JButton subButton = new JButton("SUBTRACT");
    private JTextField solution = new JTextField(10);
    private JLabel equalLabel = new JLabel("=");

    CalculatorView() {

        JPanel calcPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,500);

        calcPanel.add(firstNum);
        calcPanel.add(secondNum);
        calcPanel.add(addButton);
        calcPanel.add(subButton);
        calcPanel.add(equalLabel);
        calcPanel.add(solution);

        add(calcPanel);

    }

    public int getFirstNumber() {
        return Integer.parseInt(firstNum.getText());
    }
    public int getSecondNumber() {
        return Integer.parseInt(secondNum.getText());
    }
    public int getSolution() {
        return Integer.parseInt(solution.getText());
    }
    public void setSolution(int solution) {
        this.solution.setText(Integer.toString(solution));
    }
    void addListener(ActionListener listenForAdd) {
        addButton.addActionListener(listenForAdd);
    }
    void subListener(ActionListener listenForSub) {
        subButton.addActionListener(listenForSub);
    }

}

//Controls when and how things update
class CalculatorController {

    private CalculatorView view;
    private CalculatorModel model;

    //Constructor sets our instances of the model and view that we are using
    public CalculatorController(CalculatorView view, CalculatorModel model) {

        this.view = view;
        this.model = model;

        //Add listeners as well
        this.view.addListener(new AddListener());
        this.view.subListener(new SubListener());

    }

    //Inner class for the listeners
    //Assuming no errors, not necessary for the demo purposes
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Perform addition and update the view
            model.add(view.getFirstNumber(), view.getSecondNumber());
            view.setSolution(model.getResult());
        }

    }

    class SubListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Perform addition and update the view
            model.subtract(view.getFirstNumber(), view.getSecondNumber());
            view.setSolution(model.getResult());
        }

    }



}


public class MVCPattern {

    public MVCPattern() {

        //Make reference to view so that I can set it visible
        CalculatorView view = new CalculatorView();
        CalculatorController calculator = new CalculatorController(view, new CalculatorModel());
        view.setVisible(true);


    }

}
