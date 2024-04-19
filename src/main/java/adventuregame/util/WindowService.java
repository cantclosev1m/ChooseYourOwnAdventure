package adventuregame.util;

import adventuregame.gui.FadeInOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowService extends JFrame {

    private CardLayout cardLayout;
    JComponent currentShownComponent;

    public WindowService() {
        this.cardLayout = new CardLayout();
        this.getContentPane().setLayout(cardLayout);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ChooseYourOwnAdventure!");
        this.setVisible(true);
    }

    public void activateComponent(JComponent component) {
        if (!getContentPane().isAncestorOf(component)) {
            System.err.println("Error: Component is not part of the window's content pane. Cannot activate.");
            return;
        }
        if (currentShownComponent != null)
        {
            currentShownComponent.setVisible(false);
        }
        cardLayout.show(getContentPane(), component.getName());
        component.setVisible(true);
        currentShownComponent = component;
    }

    public void asyncFadeScreenInOut(float duration) throws InterruptedException {
        // ignore this for now
    }

    public void registerComponent(JComponent component) {
        String componentName = component.getClass().getName();
        if (getComponentByName(componentName) != null) {
            System.err.println("Error: Component with name " + componentName + " already exists. Component not registered.");
            return;
        }
        getContentPane().add(component, componentName);
    }

    public void unregisterComponent(JComponent component) {
        if (!getContentPane().isAncestorOf(component)) {
            System.err.println("Error: Component is not part of the window's content pane. Cannot unregister.");
            return;
        }
        getContentPane().remove(component);
        revalidate();
        repaint();
    }

    public JComponent getCurrentShownComponent()
    {
        return currentShownComponent;
    }

    private Component getComponentByName(String name) {
        for (Component component : getContentPane().getComponents()) {
            if (component.getName() != null && component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }
}
