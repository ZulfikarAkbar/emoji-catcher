package zulfikar.akbar.game.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {
    Bitmap  bitmap;
    int x;
    int y;
    private int speed = 1;

    int maxX;
    int minX;

    int maxY;
    int minY;

    //creating a rect object
    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        Random random_emoji = new Random();
        int random_emojis = random_emoji.nextInt(7);

        switch (random_emojis){
            case 0: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bored); break;
            case 1: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.confused); break;
            case 2: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile); break;
            case 3: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.unhappy); break;
            case 4: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tongue_out); break;
            case 5: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.kissing); break;
            case 6: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.quiet); break;
//            case 7: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mad); break;
//            case 8: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.quiet); break;
//            case 9: bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.secret); break;
        }
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        y = 0;
        x = generator.nextInt(maxX) - bitmap.getWidth();
        //initializing rect object
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }
    public void update(int playerSpeed) {
        y += playerSpeed;
        y += speed;
        if (y > maxY - bitmap.getHeight()) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            y = minY;
            x = generator.nextInt(maxX) - bitmap.getWidth();
        }

        //Adding the top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    //adding a setter to x coordinate so that we can change it after collision
    public void setX(int x){
        this.x = x;
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    //getters
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
