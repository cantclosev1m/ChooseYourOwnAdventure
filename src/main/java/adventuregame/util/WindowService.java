package adventuregame.util;

import adventuregame.gui.FadeInOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A service class to manage window operations in a Swing application.
 */
public class WindowService extends JFrame {

    private CardLayout cardLayout;
    JComponent currentShownComponent;

    /**
     * Constructs a new WindowService instance, setting up the frame properties and layout.
     */
    public WindowService() {
        this.cardLayout = new CardLayout();
        this.getContentPane().setLayout(cardLayout);
        setSize(975, 715);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ChooseYourOwnAdventure!");
        this.setVisible(true);
    }

    /**
     * Activates a specified component to be visible within the JFrame's content pane.
     * @param component The component to be activated.
     */
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

    /**
     * Registers a new component to the window's content pane.
     * @param component The component to be registered.
     */
    public void registerComponent(JComponent component) {
        String componentName = component.getClass().getName();
        if (getComponentByName(componentName) != null) {
            System.err.println("Error: Component with name " + componentName + " already exists. Component not registered.");
            return;
        }
        getContentPane().add(component, componentName);
    }

    /**
     * Unregisters a component from the window's content pane.
     * @param component The component to be unregistered.
     */
    public void unregisterComponent(JComponent component) {
        if (!getContentPane().isAncestorOf(component)) {
            System.err.println("Error: Component is not part of the window's content pane. Cannot unregister.");
            return;
        }
        getContentPane().remove(component);
        revalidate();
        repaint();
    }

    /**
     * Returns the currently active (visible) component in the JFrame.
     * @return The currently shown component.
     */
    public JComponent getCurrentShownComponent()
    {
        return currentShownComponent;
    }

    /**
     * Retrieves a component by its name from the window's content pane.
     * @param name The name of the component to find.
     * @return The found component, or null if no such component exists.
     */
    private Component getComponentByName(String name) {
        for (Component component : getContentPane().getComponents()) {
            if (component.getName() != null && component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }
}
