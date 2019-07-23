package zulfikar.akbar.game.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Basket {
    private Bitmap bitmap;
    private int x,y,speed = 0,maxY,minY, maxX, minX;
    private boolean boosting;
    private final int GRAVITY = 25;

    private final int MIN_SPEED = 0;
    private final int MAX_SPEED = 60;

    private Rect detectCollision;

    public Basket(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bucket1);
        x = 200;
        y = screenY - bitmap.getHeight();
        speed = 25;

        maxY = screenY - bitmap.getHeight();
        minY = 0;

        maxX = screenX - bitmap.getHeight();
        minX = 0;

        boosting = false;

        //initializing rect object
        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        if (boosting) {
            speed += 10;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

//        y -= speed + GRAVITY;
        x -= speed - GRAVITY;

//        if (y < minY) {
//            y = minY;
//        }
//        if (y > maxY) {
//            y = maxY;
//        }

        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




    public int getSpeed() {
        return speed;
    }
}
