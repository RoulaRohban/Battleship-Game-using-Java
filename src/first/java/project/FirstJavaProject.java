package first.java.project;

import com.sun.javafx.robot.impl.FXRobotHelper;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static first.java.project.FirstJavaProject.b_sh;
import first.java.project.GridDisplay.SquareDisplay;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import static java.nio.file.Files.list;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Callback;

public class FirstJavaProject extends Application {

    public Stage Windows;
    ComboBox Number = new ComboBox<>();
    ComboBox[] Size2 = new ComboBox[5];
    ComboBox W = new ComboBox<>();
    ComboBox H = new ComboBox<>();
    static Game b_sh;
    Battleshipplayer random_player;
    Battleshipplayer Roulla;
    GridDisplay myGrid;
    GridDisplay gridcomputer;
    GridDisplay gridhuman;
    ComboBox sleeep = new ComboBox<>();
    boolean isAlive1 = true, isAlive2 = true;
    Thread MyTimer, humadshoot, computershoot;

    Text TextTimer = new Text("1");
    int i, N;
    boolean winner = false;
    GridDisplay special;
    GridDisplay temp;
    ListView<String> listv;
    boolean falg = false; /// Roulla Turn

    public static void main(String[] args) {
        launch(args);
//        Game b_sh = new Battleshipgame();
//        Player Roulla = new Humanplayer();
//        Player random_player = new Randomcomputerplayer();
//        b_sh.subscribe(Roulla);
//        Roulla.subscribeto(b_sh);
//        //add the second player
//        //   Player robotic = new Humanplayer();
//        b_sh.subscribe(random_player);
//        random_player.subscribeto(b_sh);
//        //b_sh.start(); will calls abstract method start in game
//        ((Humanplayer) Roulla).bulid_Grid();
//        ((Computerplayer) random_player).bulid_Grid();
//        ((Battleshipgame) b_sh).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Windows = primaryStage;
        MAINMENU();
        setDefualt();
        Windows.show();
    }

    public void MAINMENU() {
        Scene screen;
        Pane layout = new Pane();
        ImageView imageview = new ImageView(new Image(getClass().getResource("war2.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(670);
        VBox v = new VBox();
        Text Name = new Text("B a t t l e  S h i p  W a r");
        Name.setFont(new Font(" Arial Black",60));
        Button newgame = new Button("New Game");
        newgame.setPrefSize(250, 40);
        newgame.setFont(new Font("Times New Roman", 20));
        newgame.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button load = new Button("L o a d");
        load.setPrefSize(250, 40);
        load.setFont(new Font("Times New Roman", 20));
        load.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button previous_games = new Button("Previous Games");
        previous_games.setPrefSize(250, 40);
        previous_games.setFont(new Font("Times New Roman", 20));
        previous_games.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
       // previous_games.setPrefSize(150,200);
        Button scoreboard = new Button("Socre Board");
        scoreboard.setPrefSize(250, 40);
        scoreboard.setFont(new Font("Times New Roman", 20));
        scoreboard.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button Setting = new Button("Setting");
        Setting.setPrefSize(250, 40);
        Setting.setFont(new Font("Times New Roman", 20));
        Setting.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button Exit = new Button("Exit");
        Exit.setPrefSize(250, 40);
        Exit.setFont(new Font("Times New Roman", 20));
        Exit.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");

        v.getChildren().addAll(Name, newgame, previous_games, scoreboard, load, Setting, Exit);
        v.setTranslateX(200);
        v.setTranslateY(30);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageview, v);
        screen = new Scene(layout, 1000, 670);
        Windows.setScene(screen);
//    Event
        newgame.setOnAction(e
                -> {
            b_sh = new Battleshipgame();
            b_sh.information = new prev_games();

            /// set ID 
            int Id = 1;
            File folder = new File("src/prev_games/");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                Id = Math.max(Id, Integer.valueOf(listOfFiles[i].getName()));
            }
            b_sh.information.setId_game(Id + 1);

            Roulla = new Humanplayer();
            b_sh.subscribe(Roulla);
            Roulla.subscribeto(b_sh);
            NEWGAME();
        });
        Setting.setOnAction(e -> SETTING());
        Exit.setOnAction(e -> Windows.close());
        previous_games.setOnAction(e -> {
            try {
                pr_games();
            } catch (Exception ex) {
                Logger.getLogger(FirstJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        load.setOnAction(e -> load());

        scoreboard.setOnAction(e -> scoreboard());
    }

    public void pr_games() throws Exception {
        Scene screen;
        Pane layout = new Pane();
        VBox v = new VBox();
        Button back = new Button("B A C K");
        back.setFont(new Font("Times New Roman", 30));
        back.setStyle("-fx-font: 30 ariel; -fx-base: #3311aa");
        ImageView imageview = new ImageView(new Image(getClass().getResource("prev_game_photo.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(700);
        TableView<prev_games> t = new TableView<>();
        t.setMinSize(750, 400);
        t.setTranslateX(50);
        t.setEditable(true);
        TableColumn tablecolview = new TableColumn("ID Game");
        TableColumn<prev_games, String> tablecolview1 = new TableColumn("Name Human");
        TableColumn<prev_games, String> tablecolview2 = new TableColumn("Type Computer");
        TableColumn<prev_games, String> tablecolview3 = new TableColumn("First Time");
        TableColumn<prev_games, String> tablecolview4 = new TableColumn("Last Time");
        TableColumn<prev_games, String> tablecolview5 = new TableColumn("Winner");
        TableColumn<prev_games, String> tablecolview6 = new TableColumn("Load");
        t.getColumns().addAll(tablecolview, tablecolview1, tablecolview2, tablecolview3, tablecolview4, tablecolview5, tablecolview6);

        tablecolview.setCellValueFactory(new PropertyValueFactory<>("id_game"));
        tablecolview1.setCellValueFactory(new PropertyValueFactory<>("Name_Player"));
        tablecolview2.setCellValueFactory(new PropertyValueFactory<>("strategy_computer"));
        tablecolview3.setCellValueFactory(new PropertyValueFactory<>("first_time"));
        tablecolview4.setCellValueFactory(new PropertyValueFactory<>("last_time"));
        tablecolview5.setCellValueFactory(new PropertyValueFactory<>("winner"));
        tablecolview6.setCellValueFactory(new PropertyValueFactory<>("Load"));

        File folder = new File("src/prev_games/");
        File[] listFile = folder.listFiles();
        ObservableList<prev_games> data = FXCollections.observableArrayList();
        for (int i = 0; i < listFile.length; i++) {
            try {
                prev_games pre = (prev_games) (save_load.load("prev_games/" + listFile[i].getName()));
            } catch (Exception ex) {
                Logger.getLogger(FirstJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
            prev_games x = (prev_games) (save_load.load("prev_games/" + listFile[i].getName()));
            data.add(x);
        }
        t.setItems(data);
        
        back.setOnAction(e-> MAINMENU());
        /**
         * *column of button for restore**
         *///
        Callback<TableColumn<prev_games, String>, TableCell<prev_games, String>> cellFactory
                = new Callback<TableColumn<prev_games, String>, TableCell<prev_games, String>>() {
            @Override
            public TableCell<prev_games, String> call(TableColumn<prev_games, String> param) {
                final TableCell<prev_games, String> cell = new TableCell<prev_games, String>() {

                    final Button btn = new Button("Load");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                prev_games SelecetGame = getTableView().getItems().get(getIndex());
                                //*** show allocation ship for two player**//
                                show_allocation_ship(SelecetGame);

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }

                };
                return cell;
            }
        };
        tablecolview6.setCellFactory(cellFactory);
        v.getChildren().addAll(t,back);
        v.setTranslateX(70);
        v.setTranslateY(65);
        v.setSpacing(50);
        v.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(imageview,v);
        screen = new Scene(layout, 1000, 600);
        Windows.setScene(screen);

    }

    public void DisplayMove(List<info_goal> info_goals) {
        Scene screeen;
        Pane layoout = new Pane();
        Button back1 = new Button("back");
        TableView<info_goal> t = new TableView<>();
      //  t.setMinSize(700, 400);
      //  t.setTranslateX(50);
        t.setEditable(true);
        TableColumn t1 = new TableColumn("X");
       // t1.setMaxWidth(50);
        TableColumn t2 = new TableColumn("Y");
       // t2.setMaxWidth(50);
        TableColumn t3 = new TableColumn("History");
       // t3.setMaxWidth(50);
        TableColumn t4 = new TableColumn("Result");
       // t4.setMaxWidth(50);
        t.getColumns().addAll(t1, t2, t3, t4);
        t1.setCellValueFactory(new PropertyValueFactory<>("x"));
        t2.setCellValueFactory(new PropertyValueFactory<>("y"));
        t3.setCellValueFactory(new PropertyValueFactory<>("last_time"));
        t4.setCellValueFactory(new PropertyValueFactory<>("result"));
        ObservableList<info_goal> data = FXCollections.observableArrayList();

        for (int i = 0; i < info_goals.size(); i++)
        {
            data.add(info_goals.get(i));
        }

        t.setItems(data);
        VBox v4 = new VBox(25);
        v4.getChildren().addAll(t,back1);
        v4.setAlignment(Pos.CENTER);
        back1.setPrefSize(300, 35);

//        back1.setTranslateX(200);
//        back1.setTranslateY(600);

        layoout.getChildren().addAll(v4);
        screeen = new Scene(layoout, 400,500);
        Windows.setScene(screeen);

        back1.setOnAction(e -> {
            MAINMENU();
        });

    }

    public void show_allocation_ship(prev_games SelecetGame) {
        Scene screen;
        Pane layout = new Pane();
        Text name = new Text("S H O W   A L L O C A T I O N   S H I P S");
        name.setFont(new Font("Times New Roman",40));
        Text name1 = new Text("C o m p u t e r   G r i d");
        name1.setFont(new Font("Times New Roman", 40));
        gridcomputer = new GridDisplay(10, 10);
        Button computer_goal = new Button("Computer Goals");
        computer_goal.setFont(new Font("Times New Roman", 20));
        computer_goal.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        VBox v = new VBox();
        v.getChildren().addAll(name1, gridcomputer, computer_goal);
        Text name2 = new Text("H u m a n   G r i d");
        name2.setFont(new Font("Times New Roman", 40));
        gridhuman = new GridDisplay(10, 10);
        Button human_goal = new Button("Human Goals");
        human_goal.setFont(new Font("Times New Roman", 20));
        human_goal.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        VBox v1 = new VBox();
        v1.getChildren().addAll(name2, gridhuman, human_goal);
        HBox h = new HBox();
        h.getChildren().addAll(v, v1);
        h.setSpacing(30);
        VBox v2 = new VBox();
        v2.getChildren().addAll(name, h);
        v2.setTranslateX(70);
        v2.setTranslateY(50);
        v2.setSpacing(50);
        v2.setAlignment(Pos.CENTER);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                SquareDisplay cell = gridhuman.getCell(j, i);
                cell.Color(SelecetGame.getHumangrid().get_status_square(i, j));
                cell = gridcomputer.getCell(j, i);
                cell.Color(SelecetGame.getComputergrid().get_status_square(i, j));
            }
        }

        layout.getChildren().addAll(v2);
        screen = new Scene(layout, 1000, 800);
        Windows.setScene(screen);

        //event 
        human_goal.setOnAction(e -> {
            DisplayMove(SelecetGame.getInfo_goals_human());
        });

        computer_goal.setOnAction(e -> {
            DisplayMove(SelecetGame.getInfo_goals_computer());
        });

    }

    public void load() {
        Scene screen;
        Pane layout = new Pane();
        Text load = new Text("L O A D  G A M E");
        load.setFont(new Font("Times New Roman", 40));
        ImageView imageview = new ImageView(new Image(getClass().getResource("imagee_load.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(700);
        ListView listv = new ListView<>();
        //listv.;
        HBox h = new HBox();
        Button loaad = new Button("L O A D");
        loaad.setFont(new Font("Times New Roman", 20));
        loaad.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button delete = new Button("D E L E T E");
        delete.setFont(new Font("Times New Roman", 20));
        delete.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button cancel = new Button("B a c k");
        cancel.setFont(new Font("Times New Roman", 20));
        cancel.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        h.getChildren().addAll(loaad, delete, cancel);
        h.setSpacing(50);
        VBox v = new VBox(load, listv, h);
        
        File folder = new File("src/save_game/");
        File[] listFile = folder.listFiles();

        for (int i = 0; i < listFile.length; i++) {
            listv.getItems().add(listFile[i].getName());
        }

        v.setTranslateX(200);
        v.setTranslateY(50);
        v.setSpacing(40);
        v.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(imageview, v);
        screen = new Scene(layout, 1000, 650);
        Windows.setScene(screen);

        loaad.setOnAction(e -> {
            String namesave = (String) listv.getSelectionModel().getSelectedItem();
            try {
                b_sh = (Game) save_load.load("save_game/" + namesave);
                Roulla = (Battleshipplayer) b_sh.players.get(0);
                random_player = (Battleshipplayer) b_sh.players.get(1);
                GoToPlay();
                //RestoreGame();
            } catch (Exception ex) {
                Logger.getLogger(FirstJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delete.setOnAction(e -> {
            // File folder = new File("src/save_game/");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles.length == 0) {
                    continue;
                }
                if (listOfFiles[i].getName() == null ? (String) listv.getSelectionModel().getSelectedItem() == null : listOfFiles[i].getName().equals((String) listv.getSelectionModel().getSelectedItem())) {

                    listv.getItems().remove(i);
                    if (!listv.getItems().isEmpty()) {
                        listv.getSelectionModel().selectFirst();
                    }
                    listOfFiles[i].delete();
                }
            }

        });
        cancel.setOnAction(e -> MAINMENU());

    }

    public void NEWGAME() {
        Scene screen;
        Pane layout = new Pane();
        ImageView imageview = new ImageView(new Image(getClass().getResource("war4.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(700);
        VBox v = new VBox();
        Text Name = new Text("N e w  G a m e");
        Name.setFont(new Font("Times New Roman", 60));
        Name.setFill(Color.CYAN);
        Text name_human = new Text("E N T E R  Y O U R  N A M E ");
        name_human.setFont(new Font("Times New Roman", 25));
        name_human.setFill(Color.DARKSLATEGREY);
        TextField name_player = new TextField();

        ////****link button random and button smart with textfield by bind ****////
        BooleanBinding b = new BooleanBinding() {
            {
                super.bind(name_player.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return name_player.getText().isEmpty();
            }
        };
        Button Random = new Button("Random");
        Random.setFont(new Font("Times New Roman", 20));
        Random.setStyle("-fx-font: 30 ariel; -fx-base: #aaaaaa");
        Button Smart = new Button("Smart");
        Smart.setFont(new Font("Times New Roman", 20));
        Smart.setStyle("-fx-font: 30 ariel; -fx-base: #aaaaaa");
        Random.disableProperty().bind(b);
        Smart.disableProperty().bind(b);
        Button Back = new Button("Back");
        Back.setFont(new Font("Times New Roman", 20));
        Back.setStyle("-fx-font: 30 ariel; -fx-base: #aaaaaa");
        v.getChildren().addAll(Name, name_human, name_player, Random, Smart, Back);
        v.setTranslateX(380);
        v.setTranslateY(100);
        v.setSpacing(30);
        v.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(imageview, v);

        screen = new Scene(layout, 1000, 700);
        Windows.setScene(screen);

        /// Events
        Random.setOnAction(e
                -> {
            b_sh.information.setName_player(name_player.getText());
            b_sh.information.setStrategy_computer("Random");
            random_player = new Randomcomputerplayer();
            b_sh.subscribe(random_player);
            random_player.subscribeto(b_sh);
            initilizeshipongrid();
        }
        );
        Smart.setOnAction(e
                -> {
            b_sh.information.setName_player(name_player.getText());
            b_sh.information.setStrategy_computer("Smart");
            random_player = new Smartcomputerplayer();
            b_sh.subscribe(random_player);
            random_player.subscribeto(b_sh);
            initilizeshipongrid();
        });
        Back.setOnAction(e
                -> MAINMENU());

    }

    private void SETTING() {
        Scene screen;
        Pane layout = new Pane();
        VBox v = new VBox();
        Text Name = new Text("S e t t i n g");
        Name.setFont(new Font("Times New Roman", 40));
        Name.setFill(Color.CRIMSON);
        ImageView imageview = new ImageView(new Image(getClass().getResource("rola.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(700);
        Text SizeGrid = new Text("S i z e   G r i d");
        SizeGrid.setFont(new Font("Times New Roman", 30));
        SizeGrid.setFill(Color.DEEPPINK);
        Text SetShip = new Text("Set Ship");
        SetShip.setFont(new Font("Times New Roman", 30));
        SetShip.setFill(Color.DEEPPINK);
        Text Width = new Text("Width");
        Width.setFont(new Font("Times New Roman", 20));
        Width.setFill(Color.DEEPPINK);
        Text Height = new Text("Height");
        Height.setFont(new Font("Times New Roman", 20));
        Height.setFill(Color.DEEPPINK);
        ComboBox W = new ComboBox<>();

        ComboBox H = new ComboBox<>();

//new for shoot
        Text sleep = new Text(" t i m e  f o r  m o v e  s h o o t");
        sleep.setFont(new Font("Times New Roman", 20));
        sleep.setFill(Color.DEEPPINK);
        for (int i = 1; i <= 20; i++) {
            sleeep.getItems().add(i);
        }
        sleeep.getSelectionModel().selectLast();
        HBox h1 = new HBox(Width, W);
        HBox h2 = new HBox(Height, H);
        Text[] Size1 = new Text[5];
        HBox[] h3 = new HBox[5];

        Button back = new Button("B a c k");
        back.setFont(new Font("Times New Roman", 25));
        back.setStyle("-fx-font: 30 ariel; -fx-base: #aa1244");
        back.setOnAction(e -> MAINMENU());

        Text NumberOfShips = new Text("N u m b e r  O f  S h i p s");
        NumberOfShips.setFont(new Font("Times New Roman", 30));
        NumberOfShips.setFill(Color.BROWN);

        for (int i = 1; i <= 5; i++) {
            Number.getItems().add(i);
            Size1[i - 1] = new Text("Size Ship " + i);
            Size1[i - 1].setFont(new Font("Times New Roman", 20));
            Size1[i - 1].setFill(Color.BLANCHEDALMOND);
            Size2[i - 1] = new ComboBox<>();

            h3[i - 1] = new HBox();
            h3[i - 1].setSpacing(50);
        }
        Number.getSelectionModel().selectLast();
        for (int j = 0; j < 5; j++) {
            for (int i = 1; i <= 5; i++) /// 
            {
                Size2[j].getItems().add(i);
            }
            Size2[j].getSelectionModel().select(j);
        }

        for (int i = 0; i < 5; i++) {
            h3[i].getChildren().addAll(Size1[i], Size2[i]);
        }

        h1.setSpacing(50);
        h2.setSpacing(50);

        for (int i = 1; i <= 10; i++) {
            W.getItems().add(i);
            H.getItems().add(i);
        }
        H.getSelectionModel().selectLast();
        W.getSelectionModel().selectLast();

        v.getChildren().addAll(Name, SizeGrid, h1, h2, SetShip, NumberOfShips, Number, h3[0], h3[1], h3[2], h3[3], h3[4], sleep, sleeep, back);
        v.setTranslateX(350);
        v.setTranslateY(40);
        v.setSpacing(15);
        v.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(imageview, v);

        screen = new Scene(layout, 1000, 900);

        Windows.setScene(screen);

        ////
        Number.valueProperty().addListener(e
                -> {
            int Num = (int) Number.getValue();

            for (int i = 0; i < Num; i++) {
                Size2[i].setDisable(false);
            }
            for (int i = Num; i < 5; i++) {
                Size2[i].setDisable(true);
            }
        });

    }

    public void initilizeshipongrid()
    {
        Scene screen;
        Pane layout = new Pane();
        ImageView imageview = new ImageView(new Image(getClass().getResource("download.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(700);
        Text ran = new Text("I n i t  s h i p  o n  g r i d ");
        ran.setFont(new Font("Times New Roman", 30));
        ran.setFill(Color.CADETBLUE);
        VBox v = new VBox();
        VBox v1 = new VBox();
        Text SizeText = new Text("Size");
        SizeText.setFont(new Font("Times New Roman", 20));
        SizeText.setFill(Color.MAGENTA);
        Button Go = new Button("G o");
        Go.setFont(new Font("Times New Roman", 25));
        Go.setStyle("-fx-font: 30 ariel; -fx-base: #ff5566");
        ComboBox SizeOfShip = new ComboBox<>();
        ComboBox Orient = new ComboBox<>();
        myGrid = new GridDisplay(10, 10,
                event
                -> {
            if (!SizeOfShip.getItems().isEmpty()) {
                SquareDisplay cell = (SquareDisplay) event.getSource();

                int length = (int) SizeOfShip.getValue();
                Ship NewShip;
                if ("Vertical".equals((String) Orient.getValue())) {
                    NewShip = new Ship(length, "good", cell.x, cell.y, cell.x + length - 1, cell.y);
                } else {
                    NewShip = new Ship(length, "good", cell.x, cell.y, cell.x, cell.y + length - 1);
                }

                if (((Battleshipplayer) Roulla).checkship(NewShip)) {
                    ((Battleshipplayer) Roulla).add_ship(NewShip);
                    myGrid.allocationShip(NewShip);
                    ((Battleshipplayer) random_player).add_ship(NewShip);

                    SizeOfShip.getItems().remove(SizeOfShip.getValue());
                    SizeOfShip.getSelectionModel().selectFirst();
                }
            }

        },
                (event)
                -> {
            SquareDisplay cell = (SquareDisplay) event.getSource();
            cell.ChangeColor1();
        }, event
                -> {
            SquareDisplay cell = (SquareDisplay) event.getSource();
            cell.ChangeColor2();
        }
        );

        Orient.getItems().add("Horizal");
        Orient.getItems().add("Vertical");
        Orient.getSelectionModel().selectLast();

        for (int i = 0; i < (int) Number.getValue(); i++) {
            int x = (int) Size2[i].getValue();
            SizeOfShip.getItems().add(x);
        }
        SizeOfShip.getSelectionModel().selectFirst();
        v1.getChildren().addAll(SizeText, SizeOfShip, Orient);
        v1.setTranslateX(80);
        v1.setTranslateY(160);
        v1.setSpacing(20);

        ran.setFont(new Font(40));
        ran.setFill(Color.BLUE);
        v.getChildren().addAll(ran, myGrid, Go);
        v.setTranslateX(250);
        v.setTranslateY(50);
        v.setSpacing(30);
        layout.getChildren().addAll(imageview, v, v1);
        screen = new Scene(layout, 1000, 700);
        Windows.setScene(screen);

        /// Event
        Go.setOnAction(e
                -> {
            //      if (SizeOfShip.getItems().isEmpty())
            {
                b_sh.information.setFirst_time(new Date());
                b_sh.information.setHumangrid(Roulla.getSpecial_grid().Copy());
                b_sh.information.setComputergrid(random_player.getSpecial_grid().Copy());

                ((Battleshipplayer) Roulla).addshoot();
                ((Battleshipplayer) random_player).addshoot();
                GoToPlay();
            }
        });
    }

    public void setDefualt() {

        for (int i = 1; i <= 5; i++) {
            Number.getItems().add(i);
            Size2[i - 1] = new ComboBox<>();

        }
        Number.getSelectionModel().selectLast();
        for (int j = 0; j < 5; j++) {
            for (int i = 1; i <= 5; i++) /// 
            {
                Size2[j].getItems().add(i);
            }
            Size2[j].getSelectionModel().select(j);
        }

        for (int i = 1; i <= 10; i++) {
            W.getItems().add(i);
            H.getItems().add(i);
        }
        H.getSelectionModel().selectLast();
        W.getSelectionModel().selectLast();

        ///
    }

    public void ComputerThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int waiting = (new Random()).nextInt(13);
                N = 1;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        N++;
                        if (N >= 10 || N > waiting) {
                            break;
                        }
                    } catch (InterruptedException ex) {
                        break;
                    }
                }

                if (N < 10) {
                    if (((Battleshipgame) b_sh).Rungame(new Battleshipmove(0, 0), "com")) {
                        winner = true;
                    }

                    Battleshipmove cur = ((Battleshipplayer) random_player).getCurrent_move();

                    Platform.runLater(() -> {
                        SquareDisplay cell = special.getCell(cur.getY(), cur.getX());
                        String res = ((Battleshipplayer) random_player).get_state_sq(cur.getX(), cur.getY());
                        if ("water".equals(res)) {
                            cell.set(1);
                        } else {
                            cell.set(0);
                        }
                    });
                    if ("destroyed shoot".equals(((Battleshipplayer) random_player).getResult())) {
                        isAlive2 = false;
                        int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
                        int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
                        for (int i = 0; i < 8; i++) {
                            int nx = cur.getX() + dx[i];
                            int ny = cur.getY() + dy[i];
                            if ((nx >= 0 && nx < 10) && (ny >= 0 && ny < 10)) //         this.temp_Grid.setSquare(square);
                            {
                                Platform.runLater(() -> {
                                    SquareDisplay cell = special.getCell(ny, nx);
                                    String res = ((Battleshipplayer) random_player).get_state_sq(nx, ny);
                                    if ("water".equals(res)) {
                                        cell.set(1);
                                    } else {
                                        cell.set(0);
                                    }
                                });
                            }
                        }
                    }
                }
                Platform.runLater(() -> {
                    reset_timer();
                });
                falg = false;
            }
        }).start();

    }

    private void GoToPlay() {

        Scene screen;
        Pane layout = new Pane();
        ImageView imageview = new ImageView(new Image(getClass().getResource("image2.gif").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(650);
        Button save = new Button("S A V E");
        save.setFont(new Font("Times New Roman", 25));
        save.setStyle("-fx-font: 30 ariel; -fx-base: #ff5500");
        special = new GridDisplay(10, 10);
        VBox v = new VBox();
        int numberofsleep = 12;//(int)sleeep.getValue();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                SquareDisplay cell = special.getCell(y, x);
                String state = ((Battleshipplayer) Roulla).get_state_sq_special(x, y);
                cell.Color(state); /// finish
            }
        }

        temp = new GridDisplay(10, 10, (event)
                -> {
            if (falg == true) {
                return;
            }
            falg = true;
            SquareDisplay cell = (SquareDisplay) event.getSource();

            if (!"unknown".equals(((Battleshipplayer) Roulla).get_state_sq(cell.x, cell.y))) {
                return;
            }

            if (((Battleshipgame) b_sh).Rungame(new Battleshipmove(cell.x, cell.y), "human")) {
                Roulla("Win");
                b_sh.stop();
                return;

            }

            String res = ((Battleshipplayer) Roulla).get_state_sq(cell.x, cell.y);
            if ("water".equals(res)) {
                cell.set(1);
            } else {
                cell.set(0);
            }
            if ("destroyed shoot".equals(((Battleshipplayer) Roulla).getResult())) {
                isAlive1 = false;
                int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
                int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
                for (int i = 0; i < 8; i++) {
                    int nx = cell.x + dx[i];
                    int ny = cell.y + dy[i];
                    if ((nx >= 0 && nx < 10) && (ny >= 0 && ny < 10)) //         this.temp_Grid.setSquare(square);
                    {
                        SquareDisplay cell1 = temp.getCell(ny, nx);
                        res = ((Battleshipplayer) Roulla).get_state_sq(nx, ny);
                        if ("water".equals(res)) {
                            cell1.set(1);
                        } else {
                            cell1.set(0);
                        }

                    }

                }

            }
            reset_timer();

            ComputerThread();

            // reset_timer();
            if (winner) {
                Roulla("Lose");
            }

        }, event
                -> {
            SquareDisplay cell = (SquareDisplay) event.getSource();
            //  cell.ChangeColor1();

        },
                event
                -> {
            SquareDisplay cell = (SquareDisplay) event.getSource();
            //   cell.ChangeColor2();
        }
        );

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                SquareDisplay cell = temp.getCell(j, i);
                cell.Color1(((Battleshipplayer) Roulla).get_state_sq(i, j));

            }
        }

        TextTimer.setFont(new Font(30));
        TextTimer.setFill(Color.BLUE);
        // TextTimer.setTranslateX(300);
        // TextTimer.setTranslateX(300);
        HBox h = new HBox();
        h.getChildren().addAll(special, temp);
        h.setSpacing(50);
        // h.setTranslateY(10);
        // h.setTranslateX(80);
        VBox m = new VBox();
        TextField name_file = new TextField();
        name_file.setPrefWidth(800);
        Text file = new Text("Enter your name file to save it :3");
        file.setFill(Color.BLUE);
        file.setFont(new Font(30));

        ////****link button save and textfield by bind ****////
        BooleanBinding b = new BooleanBinding() {
            {
                super.bind(name_file.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return name_file.getText().isEmpty();
            }
        };
        save.disableProperty().bind(b);

        m.getChildren().addAll(h, TextTimer, file, name_file, save);
        m.setTranslateX(80);
        m.setTranslateY(35);
        m.setSpacing(20);
        layout.getChildren().addAll(imageview, m);
        screen = new Scene(layout, 1000, 700);
        Windows.setScene(screen);
        //// first Thread for HumanPlayer
        thread_humanshoot();
        thread_computershoot();

        /// SecondThread for CompuerPlayer
        //        Platform.runLater(() -> {
//            thread_counter();
//        });
        thread_counter();

        save.setOnAction(e -> {
            try {
                save_load.save(b_sh, "save_game/" + name_file.getText());
            } catch (Exception ex) {
                Logger.getLogger(FirstJavaProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void Roulla(String TheWin) {
        b_sh.information.setLast_time(new Date());
        b_sh.information.setWinner(TheWin);
        try {
            save_load.save(b_sh.information, "prev_games/" + String.valueOf(b_sh.information.getId_game()));
        } catch (Exception ex) {
            Logger.getLogger(FirstJavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }

        SaveScore(TheWin);

        Scene screen;
        Pane layout = new Pane();
        ImageView imageview = new ImageView(new Image(getClass().getResource("74797.jpg").toExternalForm()));
        imageview.setFitWidth(1000);
        imageview.setFitHeight(650);
        Text t = new Text(TheWin);
        t.setFont(new Font("Times New Roman", 50));
        t.setFill(Color.BLACK);
        Button btn = new Button("T o   M a i n   M e n u ");
        btn.setFont(new Font("Times New Roman", 25));
        btn.setStyle("-fx-font: 30 ariel; -fx-base: #ff5566");
        VBox v = new VBox();
        v.getChildren().addAll(t, btn);
        v.setTranslateX(250);
        v.setTranslateY(170);
        v.setSpacing(50);
        layout.getChildren().addAll(imageview, v);

        screen = new Scene(layout, 1000, 650);
        Windows.setScene(screen);

        btn.setOnAction(e -> MAINMENU());

    }

    void thread_counter() {
        MyTimer = new Thread(new Runnable() {

            @Override
            public void run() {
                TextTimer.setText("1");
                i = 1;
                try {
                    while (true) {
                        Platform.runLater(() -> {
                            TextTimer.setText(String.valueOf(i));
                        });
                        i++;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                }
            }

        });
        MyTimer.start();

    }

    void reset_timer() {
        i = 1;
        TextTimer.setText("1");
    }

    public void thread_humanshoot() {
        Thread humanShoot = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAlive1) {
                    try {
                        int x = Roulla.getSpecial_grid().getshoot().getX();
                        int y = Roulla.getSpecial_grid().getshoot().getY();
                        Roulla.getSpecial_grid().getshoot().move();
                        SquareDisplay c = special.getCell(y, x);
                        c.set(4);
                        x = Roulla.getSpecial_grid().getshoot().getX();
                        y = Roulla.getSpecial_grid().getshoot().getY();
                        c = special.getCell(y, x);
                        c.set(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        });
        humanShoot.start();
    }

    public void thread_computershoot() {
        Thread computerShoot = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAlive2) {
                    try {
                        random_player.getSpecial_grid().getshoot().move();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        });

        computerShoot.start();
    }

    private void scoreboard() {
        Stage AlertBox;
        Scene scene;
        Pane root;
        TableView<info_score> table;
        AlertBox = new Stage();
        root = new Pane();

        table = new TableView<>();

        table.setEditable(true);
        table.setMinSize(500, 700);

        TableColumn<info_score, String> c1 = new TableColumn<>("Name Player");

        c1.setMinWidth(200);
        c1.setMaxWidth(200);
        TableColumn<info_score, String> c2 = new TableColumn<>("Win");

        c2.setMinWidth(100);
        c2.setMaxWidth(100);
        TableColumn<info_score, String> c3 = new TableColumn<>("Lose");

        c3.setMinWidth(100);
        c3.setMaxWidth(100);
        TableColumn<info_score, String> c4 = new TableColumn<>("Marks");

        c4.setMinWidth(100);
        c4.setMaxWidth(100);

        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("win"));
        c3.setCellValueFactory(new PropertyValueFactory<>("lose"));
        c4.setCellValueFactory(new PropertyValueFactory<>("mark"));
        table.getColumns().addAll(c1, c2, c3, c4);

        File folder = new File("src/scoreboard/");
        File[] listOfFiles = folder.listFiles();

        ObservableList<info_score> data = FXCollections.observableArrayList();

        for (File listOfFile : listOfFiles) {
            try {
                info_score x = (info_score) save_load.load("scoreboard/" + listOfFile.getName());
                data.add(x);
            } catch (Exception ex) {
            }

        }
        Comparator<info_score> comparator = Comparator.comparingDouble(info_score::getMark);
        comparator = comparator.reversed();

        FXCollections.sort(data, comparator);

        table.setItems(data);

        root.getChildren()
                .add(table);
        scene = new Scene(root, 500, 700);

        AlertBox.setScene(scene);

        AlertBox.showAndWait();

    }

    private void SaveScore(String TheWin) {
        File folder = new File("src/scoreboard/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                info_score x = (info_score) save_load.load("scoreboard/" + listOfFiles[i].getName());
                if (x.getName() == null ? b_sh.information.getName_Player() == null : x.getName().equals(b_sh.information.getName_Player())) {
                    listOfFiles[i].delete();
                    int w = x.getWin();
                    int l = x.getLose();
                    if (TheWin == "Win") {
                        x.setWin(w + 1);
                    } else {
                        x.setLose(l + 1);
                    }
                    x.setMark();
                    save_load.save(x, "scoreboard/" + x.getName());
                    return;
                }

            } catch (Exception ex) {
                Logger.getLogger(Battleshipgame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        int w = 0;
        int l = 0;
        if (TheWin == "Win") {
            w++;
        } else {
            l++;
        }
        info_score x = new info_score(b_sh.information.getName_Player(), w, l);
        try {
            System.out.println("|||||||||||||||| " + x.getName());
            save_load.save(x, "scoreboard/" + x.getName());
        } catch (Exception ex) {
        }

    }

}

//// from Internet
class GridDisplay extends Parent {

    private final VBox rows = new VBox();

    public GridDisplay(int w, int h, EventHandler<MouseEvent> handler1, EventHandler<MouseEvent> handler2, EventHandler<MouseEvent> handler3) {
        for (int x = 0; x < w; x++) {
            HBox row = new HBox();
            for (int y = 0; y < h; y++) {
                SquareDisplay c = new SquareDisplay(x, y, "Water");

                c.setOnMouseClicked(handler1);
                c.setOnMouseEntered(handler2);
                c.setOnMouseExited(handler3);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public GridDisplay(int w, int h) {
        for (int x = 0; x < w; x++) {
            HBox row = new HBox();
            for (int y = 0; y < h; y++) {
                SquareDisplay c = new SquareDisplay(y, x, "Water");
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);

    }

    //allocation ship for color square
    public void allocationShip(Ship newShip) {
        for (int i = newShip.getX1(); i <= newShip.getX2(); i++) {
            for (int j = newShip.getY1(); j <= newShip.getY2(); j++) {
                SquareDisplay cell = getCell(j, i);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.GREEN);
                cell.state = "part";
            }
        }
    }

    public SquareDisplay getCell(int x, int y) {
        return (SquareDisplay) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    public class SquareDisplay extends Rectangle {

        public int x, y;
        public String state;

        public SquareDisplay(int x, int y, String state) {
            super(40, 40);
            this.x = x;
            this.y = y;
            this.state = state;
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }

        public void fix() {
            setFill(Color.BLACK);
            state = "part";
        }

        public void set(int x) {
            switch (x) {
                case 1:
                    setFill(Color.YELLOW);
                    setStroke(Color.BLACK);

                    break;
                case 0:
                    setFill(Color.BROWN);
                    setStroke(Color.BLACK);
                    break;
                case 3:
                    ImagePattern imagePattern1 = new ImagePattern(new Image(getClass().getResource("shoot.PNG").toExternalForm()));
                    setFill(imagePattern1);
                    setStroke(Color.BLACK);
                    break;
                default:
                    setFill(Color.BLUE);
                    setStroke(Color.BLACK);

                    break;
            }

            state = "Part";
        }

        public void ChangeColor1() {
            if ("Water".equals(state)) {
                setFill(Color.GREEN);
                setStroke(Color.BLACK);

            }
        }

        public void ChangeColor2() {
            if ("Water".equals(state)) {
                setFill(Color.LIGHTGRAY);
                setStroke(Color.BLACK);
            }
        }

        public void Color(String s) {
            if ("water".equals(s)) {
                setFill(Color.BLUE);
            } else if ("shoot".equals(s)) {
                ImagePattern imagePattern1 = new ImagePattern(new Image(getClass().getResource("shoot.PNG").toExternalForm()));
                setFill(imagePattern1);
                setStroke(Color.BLACK);
            } else if ("destroyed".equals(s)) {
                setFill(Color.YELLOW);
            } else if ("part of destroyed ship".equals(s)) {
                setFill(Color.GREEN);
            } else {
                setFill(Color.BLACK);
            }

        }

        void Color1(String _state_sq) {

            if ("part destroyed".equals(_state_sq)) {
                setFill(Color.BROWN);
            } else if ("unknown".equals(_state_sq)) {
                setFill(Color.AQUA);
            } else if ("destroyed".equals(_state_sq) || "water".equals("water")) {
                setFill(Color.YELLOW);
            }
        }

    }

}
