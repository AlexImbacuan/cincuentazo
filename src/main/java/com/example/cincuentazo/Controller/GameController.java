package com.example.cincuentazo.Controller;

import com.example.cincuentazo.Model.*;
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
import com.example.cincuentazo.View.alert.AlertBox;
import javafx.application.Platform;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;


/**
 * Controller class for managing the game logic and interactions.
 */
public class GameController {
    private final Lock turnLock = new ReentrantLock();
    private final Condition machine1Turn = turnLock.newCondition();
    private final Condition machine2Turn = turnLock.newCondition();
    private final Condition machine3Turn = turnLock.newCondition();
    private int currentTurn = 1; // 1 for machine1, 2 for machine2, 3 for machine3

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
    private ImageView card1maq3;

    @FXML
    private ImageView card2maq3;

    @FXML
    private ImageView card3maq3;

    @FXML
    private ImageView card4maq3;

    @FXML
    private HBox handmachine1; // HBox to hold the cards of machine 1

    @FXML
    private HBox handmachine2;

    @FXML
    private HBox handmachine3;

    @FXML
    private Label labelmachine1;

    @FXML
    private Label labelmachine2;

    @FXML
    private Label labelmachine3;

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
    private boolean playerEliminated;
    private String playedCard;
    private GameSubject gameSubject;

    /**
     * Constructor for the GameController class.
     */
    public GameController() {
        System.out.println("Hello World!");
        this.space1 = false;
        this.space2 = false;
        this.space3 = false;
        this.space4 = false;
        this.namecard = "";
        this.currentcard = "";
        this.playerEliminated = false;
        this.playedCard = null;

        this.gameSubject = new GameSubject();

    }

    /**
     * Initializes the controller class.
     */
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
                    //counter.setText(String.valueOf(count));
                    gameSubject.setCount(count);

                    if(playedCard != null) {
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                        //counter.setText(String.valueOf(count));
                        gameSubject.setCount(count);
                        System.out.println("Played card: " + playedCard + " Count: " + count);
                        Platform.runLater(() -> counter.setText(String.valueOf(count)));
                    }/*
                    if(machineThread1.isAlive()){

                        card1maq1.setImage(machineRunnable1.showCards(0));
                        card2maq1.setImage(machineRunnable1.showCards(1));
                        card3maq1.setImage(machineRunnable1.showCards(2));
                        card4maq1.setImage(machineRunnable1.showCards(3));
                    }//visualizar cartas de la maquina 1
                    if(machineThread2.isAlive()){

                        card1maq2.setImage(machineRunnable2.showCards(0));
                        card2maq2.setImage(machineRunnable2.showCards(1));
                        card3maq2.setImage(machineRunnable2.showCards(2));
                        card4maq2.setImage(machineRunnable2.showCards(3));
                    }//visualizar cartas de la maquina 2
                    if(machineThread3.isAlive()){

                        card1maq3.setImage(machineRunnable3.showCards(0));
                        card2maq3.setImage(machineRunnable3.showCards(1));
                        card3maq3.setImage(machineRunnable3.showCards(2));
                        card4maq3.setImage(machineRunnable3.showCards(3));
                    }//visualizar cartas de la maquina 3*/

                } else {
                    System.out.println("Image on mesa is null or has no URL" + imageName);
                }
            }
        });

        // Initialize and start machine threads
        machineRunnable1 = new MachineRunnable("Machine 1", deck);
        machineRunnable2 = new MachineRunnable("Machine 2", deck);
        machineRunnable3 = new MachineRunnable("Machine 3", deck);
        machineThread1 = new Thread(machineRunnable1);
        machineThread2 = new Thread(machineRunnable2);
        machineThread3 = new Thread(machineRunnable3);

        // Add observer
        GameObserver gameObserver = new GameObserver(counter);
        gameSubject.addObserver(gameObserver);
    }

    /**
     * Opens the instructions in a new browser window.
     *
     * @param actionEvent the event triggered by the user
     */
    public void openhtlm(ActionEvent actionEvent) {
        try {
            File file = new File(getClass().getResource("/com/example/cincuentazo/Instructions.html").toURI());
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game.
     *
     *
     */
    @FXML
    void ChooseMachine1(ActionEvent event) {
        machine2.setVisible(false);
        machineThread1.start();
        labelmachine2.setVisible(false);
        handmachine2.setVisible(false);
        labelmachine3.setVisible(false);
        handmachine3.setVisible(false);
        machine3.setVisible(false);
    }

    /**
     * Starts the game.
     *
     *
     */
    @FXML
    void ChooseMachine2(ActionEvent event) {
        machine1.setVisible(false);
        machineThread1.start();
        machineThread2.start();
        machine3.setVisible(false);
        labelmachine3.setVisible(false);
        handmachine3.setVisible(false);
    }

    /**
     * Starts the game.
     *
     *
     */
    @FXML
    void ChooseMachine3(ActionEvent event) {
        machineThread1.start();
        machineThread2.start();
        machineThread3.start();
        machine1.setVisible(false);
        machine2.setVisible(false);
    }

    /**
     * sent the cart to the players
     * @param event
     */
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

        // set a random card in the deck ask first card to play
        String initialCard = deck.getCard();
        String imageUrl = getClass().getResource("/com/example/cincuentazo/Images/cards/" + initialCard + ".jpg").toExternalForm();
        Image initialImage = new Image(imageUrl);
        mesa.setImage(initialImage);
        deck.addPlayedCard(initialCard);
        counter.setText(String.valueOf(count));

        // Agregar la carta inicial a las cartas jugadas
        System.out.println("maquina 1 puede jugar: "+machineRunnable1.getHand());
        System.out.println("maquina 2 puede jugar: "+machineRunnable1.getHand());

        System.out.println("maquina 1 esta viva: "+ machineThread1.isAlive());
        System.out.println("maquina 2 esta viva: "+ machineThread2.isAlive());
        System.out.println("maquina 3 esta viva: "+ machineThread3.isAlive());

    }

    /**
     * create the image in the interface
     * @param event
     */
    @FXML
    void enviarfoto(MouseEvent event) {
            if (playerEliminated){
                new AlertBox().showAlert("Eliminated", "You have been eliminated", "You cannot play anymore.");
                return;
            }

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

    /**
     * valid if you can play the card, if the card is not over 50
     * @param cardName
     * @return
     */
    private boolean canPlayCard(String cardName) {
        int cardValue = card.getValor(cardName);
        return (cardValue + count) <= 50;
    }

    /**
     * get the name of the card
     * @param imageView
     * @return
     */
    private String getCardNameFromImageView(ImageView imageView) {
        if (imageView.getImage() != null && imageView.getImage().getUrl() != null) {
            String imageUrl = imageView.getImage().getUrl();
            return imageUrl.substring(imageUrl.lastIndexOf('/') + 1, imageUrl.lastIndexOf('.'));
        }
        return null;
    }

    /**
     * schedule the machine after the player is eliminated
     */
    private void scheduleMachineAfterPlayer() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 1) {
                    machine1Turn.await();
                }
                if (machineThread1.isAlive() && !machineRunnable1.isLoser()) {
                    System.out.println("Machine 1 is playing");
                    labelmachine1.setStyle("-fx-text-fill:red;");
                    labelmachine2.setStyle("-fx-text-fill:white;");
                    labelmachine3.setStyle("-fx-text-fill:white;");
                    machineRunnable1.notifyTurn();
                    cardsmachine = machineRunnable1.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                    }
                }
                checkForElimination();
                currentTurn = 2;
                machine2Turn.signal();
                checkForWinner();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 0, 6, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 2) {
                    machine2Turn.await();
                }
                if (machineThread2.isAlive() && !machineRunnable2.isLoser()) {
                    System.out.println("Machine 2 is playing");
                    labelmachine2.setStyle("-fx-text-fill:red;");
                    labelmachine1.setStyle("-fx-text-fill:white;");
                    labelmachine3.setStyle("-fx-text-fill:white;");
                    machineRunnable2.notifyTurn();
                    cardsmachine = machineRunnable2.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                    }
                }
                checkForElimination();
                currentTurn = 3;
                machine3Turn.signal();
                checkForWinner();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 2, 6, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 3) {
                    machine3Turn.await();
                }
                if (machineThread3.isAlive() && !machineRunnable3.isLoser()) {
                    System.out.println("Machine 3 is playing");
                    labelmachine3.setStyle("-fx-text-fill:red;");
                    labelmachine1.setStyle("-fx-text-fill:white;");
                    labelmachine2.setStyle("-fx-text-fill:white;");
                    machineRunnable3.notifyTurn();
                    cardsmachine = machineRunnable3.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                        }
                }
                checkForElimination();
                currentTurn = 1;
                machine1Turn.signal();
                checkForWinner();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 4, 6, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            if (checkForWinner()) {
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * schedule the machine turns
     */
    private void scheduleMachineTurns(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 1) {
                    machine1Turn.await();
                }
                if (machineThread1.isAlive() && !machineRunnable1.isLoser()) {
                    System.out.println("Machine 1 is playing");
                    labelmachine1.setStyle("-fx-text-fill:red;");
                    labelmachine2.setStyle("-fx-text-fill:white;");
                    labelmachine3.setStyle("-fx-text-fill:white;");
                    machineRunnable1.notifyTurn();
                    cardsmachine = machineRunnable1.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                    }
                }
                Platform.runLater(() -> labelmachine1.setStyle("-fx-text-fill:white;"));
                checkForElimination();
                currentTurn = 2;
                machine2Turn.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 0, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 2) {
                    machine2Turn.await();
                }
                if (machineThread2.isAlive() && !machineRunnable2.isLoser()) {
                    System.out.println("Machine 2 is playing");
                    labelmachine1.setStyle("-fx-text-fill:white;");
                    labelmachine2.setStyle("-fx-text-fill:red;");
                    labelmachine3.setStyle("-fx-text-fill:white;");
                    machineRunnable2.notifyTurn();
                    cardsmachine = machineRunnable2.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                    }
                }
                checkForElimination();
                currentTurn = 3;
                machine3Turn.signal();
                Platform.runLater(() -> labelmachine2.setStyle("-fx-text-fill:white;"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 2, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            turnLock.lock();
            try {
                while (currentTurn != 3) {
                    machine3Turn.await();
                }
                if (machineThread3.isAlive() && !machineRunnable3.isLoser()) {
                    System.out.println("Machine 3 is playing");
                    labelmachine1.setStyle("-fx-text-fill:white;");
                    labelmachine2.setStyle("-fx-text-fill:white;");
                    labelmachine3.setStyle("-fx-text-fill:red;");
                    machineRunnable3.notifyTurn();
                    cardsmachine = machineRunnable3.setTurn(Integer.parseInt(counter.getText()));
                    if (cardsmachine != null) {
                        Platform.runLater(() -> mesa.setImage(cardsmachine));
                    }
                }
                checkForElimination();
                currentTurn = 1;
                machine1Turn.signal();
                Platform.runLater(() -> labelmachine3.setStyle("-fx-text-fill:white;"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                turnLock.unlock();
            }
        }, 4, TimeUnit.SECONDS);

        scheduler.shutdown();
    }

    /**
     * Handles the event when a player places a card.
     *
     * @param event the mouse event triggered by the player
     * @throws InterruptedException if the thread is interrupted
     */
    public void colocarcarta(MouseEvent event) throws InterruptedException {
        if (playerEliminated) {
            scheduleMachineAfterPlayer();
            new AlertBox().showAlert("Eliminated", "You have been eliminated", "You cannot play anymore.");
            checkForWinner();
        } else {
            ImageView clickedImageView = (ImageView) event.getSource();
            String cardName = getCardNameFromImageView(clickedImageView);
            System.out.println("Clicked ImageView ID: " + clickedImageView.getId());

            CardInterface card = new Card();
            if (cardName.charAt(0) == 'a') {
                card = new AceValueCardDecorator(card);
            }
            int valor = card.getValor(cardName);

            if (clickedImageView == carta1 && space1 && canPlayCard(cardName)) {
                //Platform.runLater(() -> {
                    mesa.setImage(carta1.getImage());
                    carta1.setImage(null);
                //});
                space1 = false;
                playedCard = cardName;

            } else if (clickedImageView == carta2 && space2 && canPlayCard(cardName)) {
                mesa.setImage(carta2.getImage());
                carta2.setImage(null);
                space2 = false;
                playedCard = cardName;
            } else if (clickedImageView == carta3 && space3 && canPlayCard(cardName)) {
                mesa.setImage(carta3.getImage());
                carta3.setImage(null);
                space3 = false;
                playedCard = cardName;
            } else if (clickedImageView == carta4 && space4 && canPlayCard(cardName)) {
                mesa.setImage(carta4.getImage());
                carta4.setImage(null);
                space4 = false;
                playedCard = cardName;
            } else {
                new AlertBox().showAlert("Error", "Invalid card", "You can't play this card");
                checkForElimination();
                return;
            }
            System.out.println("Played card: " + playedCard);
            deck.addPlayedCard(playedCard);


        }

        checkForElimination();
        scheduleMachineTurns();
        checkForWinner();
    }


    /**
     * Retrieves the card name from the given ImageView.
     *
     * @param imageView the ImageView containing the card image
     * @return the name of the card
     */
    private String getCardName (ImageView imageView) {
        return "cardName";
    }


    /**
     * Checks if the player should be eliminated based on the current state.
     *
     * @return true if the player should be eliminated, false otherwise
     */
    private boolean shouldPlayerBeEliminated() {
        if (!space1 || !space2 || !space3 || !space4){
            return false;
        }

        int totalValue = 0;
        if (space1 && carta1.getImage() != null) totalValue += card.getValor(getCardNameFromImageView(carta1));
        if (space2 && carta2.getImage() != null) totalValue += card.getValor(getCardNameFromImageView(carta2));
        if (space3 && carta3.getImage() != null) totalValue += card.getValor(getCardNameFromImageView(carta3));
        if (space4 && carta4.getImage() != null) totalValue += card.getValor(getCardNameFromImageView(carta4));
        return (totalValue + count) > 50;
    }


    /**
     * Checks for the elimination of players and machines.
     */
    private void checkForElimination() {
        if (machineRunnable1.isLoser()) {
            handmachine1.setVisible(false);
            labelmachine1.setText("Eliminated");
            machineRunnable1.moveCardsEliminated();
            System.out.println("Machine 1 is eliminated");
            checkForWinner();
        }
        if (machineRunnable2.isLoser()) {
            handmachine2.setVisible(false);
            labelmachine2.setText("Eliminated");
            machineRunnable2.moveCardsEliminated();
            System.out.println("Machine 2 is eliminated");
            checkForWinner();
        }
        if (machineRunnable3.isLoser()) {
            handmachine3.setVisible(false);
            labelmachine3.setText("Eliminated");
            machineRunnable3.moveCardsEliminated();
            System.out.println("Machine 3 is eliminated");
            checkForWinner();
        }
        if (shouldPlayerBeEliminated()) {
            playerEliminated = true;
            System.out.println("Player is eliminated");
            moveCardsToPlayedIfEliminated();
            scheduleMachineAfterPlayer();

        }
    }

    /**
     * Checks for the winner of the game.
     *
     * @return true if there is a winner, false otherwise
     */
    private boolean checkForWinner() {
        int activeMachines = 0;
        if (machineThread1.isAlive() && !machineRunnable1.isLoser()) activeMachines++;
        if (machineThread2.isAlive() && !machineRunnable2.isLoser()) activeMachines++;
        if (machineThread3.isAlive() && !machineRunnable3.isLoser()) activeMachines++;

        if (!playerEliminated && activeMachines == 0) {
            Platform.runLater(() -> {
                new AlertBox().showAlert("Winner", "You are the winner!", "Congratulations!");
                Platform.exit();
            });
            return true;
        }

        if (playerEliminated && activeMachines == 1) {
            Platform.runLater(() -> {
                if (machineThread1.isAlive() && !machineRunnable1.isLoser()) {
                    new AlertBox().showAlert("Winner", "Machine 1 is the winner!", "Congratulations!");
                } else if (machineThread2.isAlive() && !machineRunnable2.isLoser()) {
                    new AlertBox().showAlert("Winner", "Machine 2 is the winner!", "Congratulations!");
                } else if (machineThread3.isAlive() && !machineRunnable3.isLoser()) {
                    new AlertBox().showAlert("Winner", "Machine 3 is the winner!", "Congratulations!");
                }
                Platform.exit();
            });
            return true;
        }

        return false;
    }

    /**
     * Moves the player's cards to the played cards if the player is eliminated.
     */
    private void moveCardsToPlayedIfEliminated() {
        if (playerEliminated) {
            if (carta1.getImage() != null) {
                deck.addPlayedCard(getCardNameFromImageView(carta1));
                carta1.setImage(null);
            }
            if (carta2.getImage() != null) {
                deck.addPlayedCard(getCardNameFromImageView(carta2));
                carta2.setImage(null);
            }
            if (carta3.getImage() != null) {
                deck.addPlayedCard(getCardNameFromImageView(carta3));
                carta3.setImage(null);
            }
            if (carta4.getImage() != null) {
                deck.addPlayedCard(getCardNameFromImageView(carta4));
                carta4.setImage(null);
            }
            System.out.println("Player's cards have been moved to played cards.");
        }
    }



}


