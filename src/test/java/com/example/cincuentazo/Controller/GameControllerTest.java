package com.example.cincuentazo.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest extends ApplicationTest {
    private boolean space1;
    private boolean space2;
    private boolean space3;
    private boolean space4;

    private ImageView carta1;
    private ImageView carta2;
    private ImageView carta3;
    private ImageView carta4;
    private String namecard;

    @Override
    public void start(Stage stage) {
        // This method is required to initialize the JavaFX toolkit
    }

    @BeforeEach
    void setUp() {
        // Initialize state variables
        this.space1 = false;
        this.space2 = false;
        this.space3 = false;
        this.space4 = false;
        carta1 = new ImageView();
        carta2 = new ImageView();
        carta3 = new ImageView();
        carta4 = new ImageView();
        namecard = "";
    }

    @Test
    void testsendImagencase3d() {
        namecard = "3d";
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 1, false, false, false, false,
                false, false, false, false, false, false, null);

        sendImagen(event);
        assertNotNull(carta1.getImage());
        assertTrue(space1);
    }

    @Test
    void testsendImagencasead() {
        namecard = "ad";
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 1, false, false, false, false,
                false, false, false, false, false, false, null);

        sendImagen(event);
        assertNotNull(carta1.getImage());
        assertTrue(space1);
    }

    @Test
    void testsendImagencase2c() {
        namecard = "2c";
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 1, false, false, false, false,
                false, false, false, false, false, false, null);

        sendImagen(event);
        sendImagen(event);
        assertNotNull(carta1.getImage());
        assertTrue(space1);
    }

    void sendImagen(MouseEvent event) {
        if (!space1) {
            String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
            Image ima = new Image(imageUrl);
            carta1.setImage(ima);
            space1 = true;
        } else if (!space2) {
            String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
            Image ima = new Image(imageUrl);
            carta2.setImage(ima);
            space2 = true;
        } else if (!space3) {
            String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
            Image ima = new Image(imageUrl);
            carta3.setImage(ima);
            space3 = true;
        } else if (!space4) {
            String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
            Image ima = new Image(imageUrl);
            carta4.setImage(ima);
            space4 = true;
        }
    }
}