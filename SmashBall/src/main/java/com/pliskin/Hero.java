package com.pliskin;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by aleksandrpliskin on 20.05.16.
 */
public class Hero extends Pane {

    private ImageView imageView;
    private int count = 3;
    private int columns = 3;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 32;
    private int height = 32;
    HeroTransition animation;

    Hero(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new HeroTransition(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    void moveX(int x) {
        boolean right = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
    }

    void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
    }

    double getCurX() {
        return this.getTranslateX();
    }

    double getCurY() {
        return this.getTranslateY();
    }

}
