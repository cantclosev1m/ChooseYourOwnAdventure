package adventuregame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadeInOut extends JPanel {

    private float elapsedTime = 0f;
    private float transparency = 0f;
    private float duration;

    private JComponent lol = this;

    public FadeInOut(float totalTime)
    {
        duration = totalTime;
        setSize(600,800);

        repaint();
    }

    public void start()
    {
        Timer t = new Timer(50, e -> {
            elapsedTime += 0.05f;

            float alpha = elapsedTime / duration;

            // for nice quadratic interpolation from 0 -> 1 -> 0
            if(alpha <= 0.5f) {
                transparency = 2.0f * alpha * alpha;
            }
            else {
                alpha -= 0.5f;
                transparency = 2.0f * alpha * (1.0f - alpha) + 0.5f;
            }

            // we need interpolation to go from 1 -> 0 -> 1
            transparency = 1 - transparency;

            if(elapsedTime >= duration)
            {
                ((Timer)e.getSource()).stop();
            }

            lol.repaint();
        });

        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        System.out.println(transparency);

        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(alphaComposite);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}
