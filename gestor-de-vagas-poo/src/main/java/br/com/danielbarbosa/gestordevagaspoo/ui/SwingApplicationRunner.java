package br.com.danielbarbosa.gestordevagaspoo.ui;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.GraphicsEnvironment;
import javax.swing.SwingUtilities;

@Component
public class SwingApplicationRunner implements ApplicationRunner {

    private final MainWindow mainWindow;

    public SwingApplicationRunner(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        SwingUtilities.invokeLater(mainWindow::show);
    }
}
