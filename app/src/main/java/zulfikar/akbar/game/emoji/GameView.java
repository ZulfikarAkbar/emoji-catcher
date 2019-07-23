package zulfikar.akbar.game.emoji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Basket basket;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    int timeTaken = 300;
    int timeMax = 0;
    int targetScore = 500;
    unluckyEmoji unluckyEmoji;
    emoji enemies1, enemies2, enemies3,
          enemies4, enemies5, enemies6,
          enemies7;// unluckyEmoji;
    //a screenX holder
    int screenX;
    //to count the number of Misses
    int countMisses;
    //indicator that the enemy has just entered the game screen
    boolean flag ;
    //an indicator if the game is Over
    private boolean isGameOver, gameEnded ;
    //defining a inLoveEmoji object to display blast
    private inLoveEmoji inLoveEmoji;private explosionEmoji explosionEmoji;private luckyStarEmoji luckyStarEmoji;
    static MediaPlayer gameOnsound;
    final MediaPlayer killedEnemysound, killedFriendsound;
    final MediaPlayer gameOversound;
    //the score holder
    int score;
    //the high Scores Holder
    int highScore[] = new int[5];
    //context to be used in onTouchEvent to cause the activity transition from GameAvtivity to MainActivity.
    Context context;
    //Shared Prefernces to store the High Scores
    SharedPreferences sharedPreferences;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.context=context;
        surfaceHolder = getHolder();
        paint = new Paint();
        basket = new Basket(context, screenX, screenY);
        enemies1 = new emoji(context, screenX, screenY);
        enemies2 = new emoji(context, screenX, screenY);
        enemies3 = new emoji(context, screenX, screenY);
        enemies4 = new emoji(context, screenX, screenY);
        enemies5 = new emoji(context, screenX, screenY);
        enemies6 = new emoji(context, screenX, screenY);
        enemies7 = new emoji(context, screenX, screenY);
        unluckyEmoji = new unluckyEmoji(context, screenX, screenY);
        //initializing inLoveEmoji object
        inLoveEmoji = new inLoveEmoji(context);
        explosionEmoji = new explosionEmoji(context);
        luckyStarEmoji = new luckyStarEmoji(context);
        this.screenX = screenX;
        countMisses = 0;
        isGameOver = false;
        //setting the score to 0 initially
        score = 0;
        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);
        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);
        //highScore[4] = sharedPreferences.getInt("score5",0);
        //initializing the media baskets for the game sounds
        gameOnsound = MediaPlayer.create(context,R.raw.dingdong);
        killedEnemysound = MediaPlayer.create(context,R.raw.pickup_coin);
        killedFriendsound = MediaPlayer.create(context,R.raw.laser_shoot);
        gameOversound = MediaPlayer.create(context,R.raw.bell);
        //starting the game music as the game starts
        gameOnsound.start();
        gameEnded=false;
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        basket.update();
        //setting inLoveEmoji outside the screen
        inLoveEmoji.setX(-250);
        inLoveEmoji.setY(-250);
        explosionEmoji.setX(-250);
        explosionEmoji.setY(-250);
        luckyStarEmoji.setX(-250);
        luckyStarEmoji.setY(-250);

        //setting the flag true when the enemy just enters the screen
        if(enemies1.getX()==screenX){ flag = true; }
        if(enemies2.getX()==screenX){ flag = true; }
        if(enemies3.getX()==screenX){ flag = true; }
        if(enemies4.getX()==screenX){ flag = true; }
        if(enemies5.getX()==screenX){ flag = true; }
        if(enemies6.getX()==screenX){ flag = true; }
        if(enemies7.getX()==screenX){ flag = true; }
       // if(unluckyEmoji.getX()==screenX){ flag = true; }

        enemies1.update(basket.getSpeed());enemies2.update(basket.getSpeed());enemies3.update(basket.getSpeed());
        enemies4.update(basket.getSpeed());enemies5.update(basket.getSpeed());enemies6.update(basket.getSpeed());
        enemies7.update(basket.getSpeed());

        //if collision occurs with basket
        if (Rect.intersects(basket.getDetectCollision(), enemies1.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies1.getX());
            inLoveEmoji.setY(enemies1.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies1.setX(-200);
        }

        if (Rect.intersects(basket.getDetectCollision(), enemies2.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies2.getX());
            inLoveEmoji.setY(enemies2.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies2.setX(-200);
        }

        if (Rect.intersects(basket.getDetectCollision(), enemies3.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies3.getX());
            inLoveEmoji.setY(enemies3.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
        }

        if (Rect.intersects(basket.getDetectCollision(), enemies4.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies4.getX());
            inLoveEmoji.setY(enemies4.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies4.setX(-200);
        }

        if (Rect.intersects(basket.getDetectCollision(), enemies5.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies5.getX());
            inLoveEmoji.setY(enemies5.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies5.setX(-200);
        }

        if (Rect.intersects(basket.getDetectCollision(), enemies6.getDetectCollision())) {
            score=score+50;
            //displaying inLoveEmoji at that location
            luckyStarEmoji.setX(enemies6.getX());
            luckyStarEmoji.setY(enemies6.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies6.setX(-200);
        }


        if (Rect.intersects(basket.getDetectCollision(), enemies7.getDetectCollision())) {
            score=score+10;
            //displaying inLoveEmoji at that location
            inLoveEmoji.setX(enemies7.getX());
            inLoveEmoji.setY(enemies7.getY());
            //playing a sound at the collision between basket and the enemy
            killedEnemysound.start();
            enemies7.setX(-200);
        }

        //updating the unluckyEmoji ships coordinates
        unluckyEmoji.update(basket.getSpeed());
        //checking for a collision between basket and a unluckyEmoji
        if(Rect.intersects(basket.getDetectCollision(), unluckyEmoji.getDetectCollision())){
            score=score-100;
            //displaying the inLoveEmoji at the collision
            explosionEmoji.setX(unluckyEmoji.getX());
            explosionEmoji.setY(unluckyEmoji.getY());
            killedFriendsound.start();
            unluckyEmoji.setX(-200);
        }

        if(!isGameOver){
            timeTaken--;
        }

        if(timeTaken==timeMax){
            playing=false;
            isGameOver=true;
            gameOnsound.stop();
            gameOversound.start();

            //Assigning the scores to the highscore integer array
            for(int i=0;i<4;i++){
                if(highScore[i]<score){
                    //final int finalI = i;
                    highScore[i] = score;
                    break;
                }
            }
            //storing the scores through shared Preferences
            SharedPreferences.Editor e = sharedPreferences.edit();
            for(int i=0;i<4;i++){
                int j = i+1;
                e.putInt("score"+j,highScore[i]);
            }
            e.apply();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            //canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.p1_min),0,0,null);
            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            //drawing the score on the game screen
            paint.setTextSize(30);
            canvas.drawText("Your point: "+score,100,50,paint);
            //drawing the score on the game screen
            paint.setTextSize(30);
            canvas.drawText("Target point: "+ targetScore,1100,50,paint);
            canvas.drawBitmap(
                    basket.getBitmap(),
                    basket.getX(),
                    basket.getY(),
                    paint);
            canvas.drawBitmap(
                    enemies1.getBitmap(),
                    enemies1.getX(),
                    enemies1.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies2.getBitmap(),
                    enemies2.getX(),
                    enemies2.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies3.getBitmap(),
                    enemies3.getX(),
                    enemies3.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies4.getBitmap(),
                    enemies4.getX(),
                    enemies4.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies5.getBitmap(),
                    enemies5.getX(),
                    enemies5.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies6.getBitmap(),
                    enemies6.getX(),
                    enemies6.getY(),
                    paint
            );
            canvas.drawBitmap(
                    enemies7.getBitmap(),
                    enemies7.getX(),
                    enemies7.getY(),
                    paint
            );
            canvas.drawBitmap(
                    unluckyEmoji.getBitmap(),
                    unluckyEmoji.getX(),
                    unluckyEmoji.getY(),
                    paint
            );
            //drawing inLoveEmoji image
            canvas.drawBitmap(
                    inLoveEmoji.getBitmap(),
                    inLoveEmoji.getX(),
                    inLoveEmoji.getY(),
                    paint
            );
            canvas.drawBitmap(
                    explosionEmoji.getBitmap(),
                    explosionEmoji.getX(),
                    explosionEmoji.getY(),
                    paint
            );
            canvas.drawBitmap(
                    luckyStarEmoji.getBitmap(),
                    luckyStarEmoji.getX(),
                    luckyStarEmoji.getY(),
                    paint
            );

            if(!isGameOver){
                // paint time
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setColor(Color.argb(255,255,255,255));
                paint.setTextSize(30);
                canvas.drawText("Time: " + timeTaken/10 + " second", screenX/2,50,paint);
            }else{
                //draw game Over when the game is over
                    paint.setTextSize(150);
                    paint.setTextAlign(Paint.Align.CENTER);
                    //int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                    canvas.drawText("Level 3 Clear",screenX/2,300,paint);
                    if(score<targetScore){
                        paint.setTextSize(70);
                        canvas.drawText("You Lose!",screenX/2,400,paint);
                        //drawing the score on the game screen
                        paint.setTextSize(30);
                        canvas.drawText("Your point: "+score,screenX/2,480,paint);

                        paint.setTextSize(80);
                        canvas.drawText("Tap to replay",screenX/2,600,paint);
                    }else{
                        paint.setTextSize(70);
                        canvas.drawText("You Win!",screenX/2,400,paint);
                        //drawing the score on the game screen
                        paint.setTextSize(30);
                        canvas.drawText("Your point: "+score,screenX/2,480,paint);

                        paint.setTextSize(80);
                        canvas.drawText("Tap to next level",screenX/2,600,paint);
                    }
//                    //drawing the score on the game screen
//                    paint.setTextSize(30);
//                    canvas.drawText("Your point: "+score,screenX/2,480,paint);
//
//                    paint.setTextSize(80);
//                    canvas.drawText("Tap to play again",screenX/2,600,paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                basket.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                basket.setBoosting();
                break;
        }
        //if the game's over, tappin on game Over screen sends you to MainActivity
        if(isGameOver&&score<targetScore){
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,GameActivity.class));

            }
        } else if(isGameOver&&score>=targetScore){
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,GameActivity.class));

            }
        }
        return true;
    }
    //stop the music on exit
    public static void stopMusic(){
        gameOnsound.stop();
    }
}