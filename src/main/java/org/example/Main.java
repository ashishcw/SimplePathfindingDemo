package org.example;

import org.example.constant.Constants;
import org.example.display.Window;
import org.example.handlers.BaseHandler;
import org.example.handlers.input.KeyInputHandler;
import org.example.handlers.input.MouseInputHandler;
import org.example.objects.TempObject;
import org.example.objects.grid.Node;

import java.awt.*;

public class Main extends Canvas implements Runnable {

    //Window
    private Window window;

    //Thread
    private Thread thread;
    private boolean isRunning = false;

    //Handler
    private BaseHandler handlerBase;

    //Node
    private Node node;

    //Input
    private KeyInputHandler keyInputHandler;
    private MouseInputHandler mouseInputHandler;




    public Main(){
        init();
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");

        new Main();
    }

    private void init(){
        //Window instantiation
        if(this.window == null){
            this.window = new Window(this);
        }

        //Thread instantiation
        if(this.thread == null){
            this.thread = new Thread(this, "Addition_Thread_1");
        }

        //Handler instantiation
        if(this.handlerBase == null){
            this.handlerBase = new BaseHandler();
        }
        //this.handlerBase.allGameObjectsList.add(new TempObject(100, 100));

        //Node instantiation
        Node.createNodeGrid();
        if(this.node == null){
            this.node = Node.nodes[0][0];
        }
        for (int i = 0; i < Node.nodes.length; i++){
            for(int j = 0; j < Node.nodes.length; j++){
                this.handlerBase.addObjectToList(Node.nodes[i][j]);
            }
        }

        //Input instantiation
        //Keyboard input
        if(this.keyInputHandler == null){
            this.keyInputHandler = new KeyInputHandler();
        }
        this.addKeyListener(this.keyInputHandler);

        //Mouse input
        if(this.mouseInputHandler == null){
            this.mouseInputHandler = new MouseInputHandler();
        }
        this.addMouseListener(this.mouseInputHandler);

        start();
    }

    private synchronized void start(){
        if(this.isRunning){
            return;
        }

        if(this.thread != null){
            this.thread.start();
        }

        this.isRunning = true;
    }

    private synchronized void stop(){
        if(!this.isRunning){
            return;
        }

        if(this.thread != null){
            try{
                this.thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        this.isRunning = false;
    }

    @Override
    public void run(){
        mainLoop();
    }

    private void mainLoop(){
        long lastLoopTime = System.nanoTime();
        long lastFPSTime = 0;

        while(this.isRunning){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            double delta = updateLength / ((double) Constants.OPTIMAL_TIME);

            lastFPSTime += updateLength;
            if (lastFPSTime >= 1000000000) {
                lastFPSTime = 0;
            }

            // Update game logic based on delta
            tick();

            // Repaint or render the game
            render();



            try {
                // Sleep to maintain frame rate
                long gameTime = (lastLoopTime - System.nanoTime() + Constants.OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {
                // Handle exceptions
                this.isRunning = false;
            }
        }
        stop();
    }

    private void tick(){

        //handler tick method call
        if(this.handlerBase != null){
            this.handlerBase.tick();
        }
    }

    private void render(){
        var bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        //additional render calls go here

        //handler render call
        if(this.handlerBase != null){
            this.handlerBase.render(g);
        }

        bs.show();
        g.dispose();
    }


}