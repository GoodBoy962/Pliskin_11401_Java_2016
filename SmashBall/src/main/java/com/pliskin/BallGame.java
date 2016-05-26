package com.pliskin;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by aleksandrpliskin on 20.05.16.
 */
public class BallGame extends Application {

    Group group;
    private Scene scene;
    private int sceneLength = 800;
    private int sceneWidth = 1200;
    private Image image = new Image(getClass().getResourceAsStream("/1.png"));
    private ImageView imageView = new ImageView(image);
    private Hero hero;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private static List<Ball> balls = new ArrayList<>();
    private static final int RADIUS = 20;
    private int targetSize = 40;
    private Stage primaryStage;
    int score = 0;
    Rectangle target;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        hero = new Hero(imageView, this);
        group = new Group();
        scene = new Scene(group, sceneWidth, sceneLength);
        balls = new ArrayList<>();
        initElements();
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

        group.getChildren().addAll(hero);
        primaryStage.setTitle("Smash ball");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private class Ball {

        private int dx;
        private int dy;
        private Timeline timeline;
        Circle circle;

        Ball(Scene scene, Group group) {
            circle = new Circle(createStartX(), createStartY(), RADIUS, Color.RED);
            balls.add(this);
            group.getChildren().addAll(circle);
            createDirection();

            timeline = new Timeline();
            timeline.setCycleCount(Animation.INDEFINITE);
            KeyFrame moveBall = new KeyFrame(Duration.seconds(.01),
                    event -> {
                        checkHeroTouched();

                        double xMin = circle.getBoundsInParent().getMinX();
                        double yMin = circle.getBoundsInParent().getMinY();
                        double xMax = circle.getBoundsInParent().getMaxX();
                        double yMax = circle.getBoundsInParent().getMaxY();

                        if (xMin < 0 || xMax > scene.getWidth()) {
                            dx *= -1;
                        }
                        if (yMin < 0 || yMax > scene.getHeight()) {
                            dy *= -1;
                        }
                        circle.setTranslateX(circle.getTranslateX() + dx);
                        circle.setTranslateY(circle.getTranslateY() + dy);
                    });
            timeline.getKeyFrames().add(moveBall);
            timeline.play();
        }

        private void createDirection() {
            if (Math.random() > 0.5) {
                dx = 1;
            } else {
                dx = -1;
            }
            if (Math.random() > 0.5) {
                dy = 1;
            } else {
                dy = -1;
            }
        }

        private int createStartX() {
            boolean unique = true;
            boolean res = false;
            int x = createNumber(sceneWidth);
            while (!res) {
                int i = 0;
                while (unique && i < balls.size()) {
                    for (Ball ball : balls) {
                        if (ball.circle.getCenterX() == x) {
                            unique = false;
                        }
                        i++;
                    }
                }
                if (unique) {
                    res = true;
                } else {
                    x = createNumber(sceneWidth);
                    unique = true;
                }
            }
            return x;
        }

        private int createStartY() {
            boolean unique = true;
            boolean res = false;
            int y = createNumber(sceneLength);
            while (!res) {
                int i = 0;
                while (unique && i < balls.size()) {
                    for (Ball ball : balls) {
                        if (ball.circle.getCenterY() == y) {
                            unique = false;
                        }
                        i++;
                    }
                }
                if (unique) {
                    res = true;
                } else {
                    y = createNumber(sceneLength);
                    unique = true;
                }
            }
            return y;
        }

        private int createNumber(int param) {
            return new Random().nextInt((param - 100) / 100) * 100 + 20;
        }

        private void checkHeroTouched() {
            if (heroTouchCheck(circle)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You score is " + score);
                alert.show();
                alert.setOnCloseRequest(event -> primaryStage.close());
            }
        }
    }

    private void initElements() {
        target();
        createBalls(7);
    }

    void target() {
        target = new Rectangle(targetSize, targetSize, Color.GREEN);
        targetSize--;
        if (targetSize < 5) {
            targetSize = 5;
        }
        int x = (int) Math.floor(Math.random() * sceneWidth);
        int y = (int) Math.floor(Math.random() * sceneLength);
        target.setX(x);
        target.setY(y);
        group.getChildren().addAll(target);
    }

    private void createBalls(int n) {
        for (int i = 0; i < n; i++) {
            balls.add(new Ball(scene, group));
        }
    }

    private void update() {
        if (isPressed(KeyCode.UP)) {
            hero.animation.play();
            hero.animation.setOffsetY(96);
            hero.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)) {
            hero.animation.play();
            hero.animation.setOffsetY(0);
            hero.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            hero.animation.play();
            hero.animation.setOffsetY(64);
            hero.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            hero.animation.play();
            hero.animation.setOffsetY(32);
            hero.moveX(-2);
        } else {
            hero.animation.stop();
        }
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private boolean heroTouchCheck(Shape shape) {

        double xMin = shape.getBoundsInParent().getMinX();
        double yMin = shape.getBoundsInParent().getMinY();
        double xMax = shape.getBoundsInParent().getMaxX();
        double yMax = shape.getBoundsInParent().getMaxY();

        double heroXMin = hero.getCurX();
        double heroYMin = hero.getCurY();
        double heroXMax = hero.getCurX();
        double heroYMax = hero.getCurY();

        return ((heroXMin >= xMin && heroXMin <= xMax) || (heroXMax >= xMin && heroXMax <= xMax)) && ((heroYMin >= yMin && heroYMin <= yMax) || (heroYMax >= yMin && heroYMax <= yMax));
    }

    public static void main(String[] args) {
        launch(args);
    }
}