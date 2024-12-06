package com.example.cincuentazo.Controller;

import com.example.cincuentazo.Model.Card;
import com.example.cincuentazo.Model.Deck;
import com.example.cincuentazo.Model.MachineRunnable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameController {

    @FXML
    private Button machine1;

    @FXML
    private Button btnStart;

    @FXML
    private Button machine2;

    @FXML
    private Button machine3;

    @FXML
    private Pane paneinitial;

    @FXML
    private ImageView carta1;

    @FXML
    private ImageView carta2;
    @FXML
    private ImageView carta3;

    @FXML
    private ImageView carta4;
    @FXML
    private ImageView mesa;

    @FXML
    private Label counter;

    @FXML
    private ImageView mazo;

    @FXML
    private ImageView card1maq1;

    @FXML
    private ImageView card1maq2;

    @FXML
    private ImageView card2maq1;

    @FXML
    private ImageView card2maq2;

    @FXML
    private ImageView card3maq1;

    @FXML
    private ImageView card3maq2;

    @FXML
    private ImageView card4maq1;

    @FXML
    private ImageView card4maq2;

    @FXML
    private HBox handmachine1; // HBox to hold the cards of machine 1

    @FXML
    private HBox handmachine2;

    @FXML
    private Label labelmachine1;

    @FXML
    private Label labelmachine2;

    private Image cardsmachine;
    private int count = 0;
    private int countletter  = 0;
    private Deck deck= new Deck();
    private Card card= new Card();
    private boolean space1;
    private boolean space2;
    private boolean space3;
    private boolean space4;
    private String namecard;
    private String currentcard;
    private Thread machineThread1;
    private Thread machineThread2;
    private Thread machineThread3;
    private MachineRunnable machineRunnable1;
    private MachineRunnable machineRunnable2;
    private MachineRunnable machineRunnable3;

    public GameController() {
        System.out.println("Hello World!");
        this.space1 = false;
        this.space2 = false;
        this.space3 = false;
        this.space4 = false;
        this.namecard = "";
        this.currentcard = "";



    }

    @FXML
    public void initialize() {
        assert mesa != null : "fx:id=\"mesa\" was not injected: check your FXML file 'hello-view.fxml'.";
        mesa.imageProperty().addListener(new ChangeListener<Image>() {
            @Override
            public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
                String imageName = null;
                if (newValue != null && newValue.getUrl() != null) {
                    String imageUrl = newValue.getUrl(); // Get the URL of the new image
                    imageName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
                    System.out.println("Image on mesa: " + imageName);

                    // Update count and counter
                    count += card.getValor(imageName);
                    counter.setText(String.valueOf(count));

                } else {
                    System.out.println("Image on mesa is null or has no URL" + imageName);
                }
            }
        });

        // Initialize and start machine threads
        machineRunnable1 = new MachineRunnable("Machine 1");
        machineRunnable2 = new MachineRunnable("Machine 2");
        machineRunnable3 = new MachineRunnable("Machine 3");
        machineThread1 = new Thread(machineRunnable1);
        machineThread2 = new Thread(machineRunnable2);
        machineThread3 = new Thread(machineRunnable3);
    }

    public void openhtlm(ActionEvent actionEvent) {
        System.out.println("maquina 1 esta viva: "+ machineThread1.isAlive());
        System.out.println("maquina 2 esta viva: "+ machineThread2.isAlive());
    }
    @FXML
    void ChooseMachine1(ActionEvent event) {
        machine2.setVisible(false);
        machineThread1.start();
        labelmachine2.setVisible(false);
        handmachine2.setVisible(false);
        machine3.setVisible(false);
    }

    @FXML
    void ChooseMachine2(ActionEvent event) {
        machine1.setVisible(false);
        machineThread1.start();
        machineThread2.start();
        machine3.setVisible(false);
    }

    @FXML
    void ChooseMachine3(ActionEvent event) {
        machineThread1.start();
        machineThread2.start();
        machineThread3.start();
        machine1.setVisible(false);
        machine2.setVisible(false);
    }

    @FXML
    void enviar(ActionEvent event) {
        paneinitial.setVisible(false);
        deck.generateDeck();
        System.out.println(deck.getDeck());
        if(machineThread1.isAlive()){
           for(int i=0; i<4; i++){
               machineRunnable1.takeCard(deck.getCard());
           }
        }
        if(machineThread2.isAlive()){
            for(int i=0; i<4; i++){
                machineRunnable2.takeCard(deck.getCard());
            }
        }

        if(machineThread3.isAlive()){
            for(int i=0; i<4; i++){
                machineRunnable3.takeCard(deck.getCard());
            }
        }

        if(machineThread1.isAlive()){

            card1maq1.setImage(machineRunnable1.showCards(0));
            card2maq1.setImage(machineRunnable1.showCards(1));
            card3maq1.setImage(machineRunnable1.showCards(2));
            card4maq1.setImage(machineRunnable1.showCards(3));
        }


        System.out.println("maquina 1 puede jugar: "+machineRunnable1.getHand());
        System.out.println("maquina 2 puede jugar: "+machineRunnable1.getHand());

        System.out.println("maquina 1 esta viva: "+ machineThread1.isAlive());
        System.out.println("maquina 2 esta viva: "+ machineThread2.isAlive());
        System.out.println("maquina 3 esta viva: "+ machineThread3.isAlive());

    }
    @FXML
    void enviarfoto(MouseEvent event) {

            if (!space1) {
                namecard = deck.getCard();
                String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
                Image ima = new Image(imageUrl);
                carta1.setImage(ima);
                space1 = true;
            } else if (!space2) {
                namecard = deck.getCard();
                String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
                Image ima = new Image(imageUrl);
                carta2.setImage(ima);
                space2 = true;
            } else if (!space3) {
                namecard = deck.getCard();
                String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
                Image ima = new Image(imageUrl);
                carta3.setImage(ima);
                space3 = true;
            } else if (!space4) {
                namecard = deck.getCard();
                String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + namecard + ".jpg").toExternalForm();
                Image ima = new Image(imageUrl);
                carta4.setImage(ima);
                space4 = true;
            }

    }

    public void colocarcarta(MouseEvent event) throws InterruptedException {

        ImageView clickedImageView = (ImageView) event.getSource();
        System.out.println("Clicked ImageView ID: " + clickedImageView.getId());


        if (clickedImageView == carta1 && space1) {
            mesa.setImage(carta1.getImage());
            carta1.setImage(null);
            space1 = false;

        } else if (clickedImageView == carta2 && space2) {
            mesa.setImage(carta2.getImage());
            carta2.setImage(null);
            space2 = false;
        } else if (clickedImageView == carta3 && space3) {
            mesa.setImage(carta3.getImage());
            carta3.setImage(null);
            space3 = false;
        } else if (clickedImageView == carta4 && space4) {
            mesa.setImage(carta4.getImage());
            carta4.setImage(null);
            space4 = false;
        }

        if (machineThread1.isAlive()) {

            machineRunnable1.notifyTurn();
            cardsmachine = machineRunnable1.setTurn(Integer.parseInt(counter.getText()));
            mesa.setImage(cardsmachine);

        }

    }


}
