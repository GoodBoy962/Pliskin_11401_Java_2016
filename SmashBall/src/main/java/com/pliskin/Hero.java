package com.pliskin;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by aleksandrpliskin on 20.05.16.
 */
class Hero extends Pane {

    private static final int count = 3;
    private static final int columns = 3;
    private static final int offsetX = 0;
    private static final int offsetY = 0;
    private static final int width = 32;
    private static final int height = 32;
    HeroTransition animation;
    private BallGame ballGame;

    Hero(ImageView imageView, BallGame ballGame) {
        this.ballGame = ballGame;
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new HeroTransition(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
            isTargetReached();
        }
    }

    void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
            isTargetReached();
        }
    }

    double getCurX() {
        return this.getTranslateX();
    }

    double getCurY() {
        return this.getTranslateY();
    }

    private void isTargetReached() {
        if (this.getBoundsInParent().intersects(ballGame.target.getBoundsInParent())) {
            ballGame.score++;
            System.out.println(ballGame.score);
            ballGame.group.getChildren().remove(ballGame.target);
            ballGame.target();
        }
    }

}
