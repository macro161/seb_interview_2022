package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");

        GamePanel gamePanel = new GamePanel();
        frame.setContentPane(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        Timer timer = new Timer(1000 / 60, e -> {
            gamePanel.update();
            gamePanel.repaint();
        });
        timer.start();
    }
}

class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Paddle paddle1, paddle2;
    private Ball ball;
    private int winner = 0;
    private JButton restartButton;



    // Add score variables for both players
    private int player1Score = 0;
    private int player2Score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        paddle1 = new Paddle(20, HEIGHT / 2 - 50, KeyEvent.VK_W, KeyEvent.VK_S);
        paddle2 = new Paddle(WIDTH - 40, HEIGHT / 2 - 50, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ball = new Ball(WIDTH / 2 - 10, HEIGHT / 2 - 10);

        // Initialize the restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        restartButton.setBounds(WIDTH / 2 - 100, HEIGHT - 100, 200, 50); // Updated position
        restartButton.setVisible(false);
        add(restartButton);

    }

    private void resetGame() {
        player1Score = 0;
        player2Score = 0;
        winner = 0;
        ball.reset(WIDTH / 2 - 10, HEIGHT / 2 - 10);
        restartButton.setVisible(false);
        requestFocusInWindow();
    }

    public void update() {

        if (winner != 0) {
            return;
        }

        paddle1.update();
        paddle2.update();
        ball.update();

        // Collision with paddles
        if (ball.intersects(paddle1) || ball.intersects(paddle2)) {
            ball.reverseXDirection();
            ball.increaseSpeed();
        }

        // Collision with top or bottom
        if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= HEIGHT) {
            ball.reverseYDirection();
        }

        // Scoring
        if (ball.x < 0) {
            player2Score++;
            ball.reset(WIDTH / 2 - ball.width / 2, HEIGHT / 2 - ball.height / 2);
        }
        if (ball.x > WIDTH) {
            player1Score++;
            ball.reset(WIDTH / 2 - ball.width / 2, HEIGHT / 2 - ball.height / 2);
        }

        // Check for winner
        if (player1Score >= 10) {
            winner = 1;
        } else if (player2Score >= 10) {
            winner = 2;
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Call the superclass's paintComponent method
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Draw the gradient background
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, 0, HEIGHT, Color.BLACK);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString(Integer.toString(player1Score), WIDTH / 2 - 60, 40);
        g.drawString(Integer.toString(player2Score), WIDTH / 2 + 40, 40);

        // Draw the paddles and ball
        paddle1.paint(g);
        paddle2.paint(g);
        ball.paint(g);

        // Display the winner
        if (winner != 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Player " + winner + " wins!", WIDTH / 2 - 150, HEIGHT / 2);
        }

        if (winner != 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Player " + winner + " wins!", WIDTH / 2 - 150, HEIGHT / 2);
            restartButton.setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        paddle1.handleKeyPress(e.getKeyCode());
        paddle2.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle1.handleKeyRelease(e.getKeyCode());
        paddle2.handleKeyRelease(e.getKeyCode());
    }
}

class Paddle extends Rectangle {
    private int upKey, downKey;
    private boolean up, down;

    public Paddle(int x, int y, int upKey, int downKey) {
        super(x, y, 20, 100);
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update() {
        if (up) {
            y -= 5;
        }
        if (down) {
            y += 5;
        }

        // Prevent paddles from going off the screen
        if (y < 0) {
            y = 0;
        }
        if (y + height > GamePanel.HEIGHT) {
            y = GamePanel.HEIGHT - height;
        }


    }

    public void handleKeyPress(int keyCode) {
        if (keyCode == upKey) {
            up = true;
        }
        if (keyCode == downKey) {
            down = true;
        }
    }

    public void handleKeyRelease(int keyCode) {
        if (keyCode == upKey) {
            up = false;
        }
        if (keyCode == downKey) {
            down = false;
        }
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}

class Ball extends Rectangle {
    private int xSpeed, ySpeed;
    private int initialXSpeed = 5;
    private int initialYSpeed = 5;


    public Ball(int x, int y) {
        super(x, y, 20, 20);
        xSpeed = initialXSpeed;
        ySpeed = initialYSpeed;
    }

    public void increaseSpeed() {
        // Increase the speed by a small factor (e.g., 10%)
        xSpeed += Math.signum(xSpeed) * Math.abs(xSpeed) * 0.5;
        ySpeed += Math.signum(ySpeed) * Math.abs(ySpeed) * 0.5;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
    }

    public void reverseXDirection() {
        xSpeed = -xSpeed;
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }


    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        reverseXDirection();
        // Reset the ball speed to the initial speed
        xSpeed = initialXSpeed;
        ySpeed = initialYSpeed;
    }

    public void paint(Graphics g) {
        // Draw a round ball instead of a square
        g.fillOval(x, y, width, height);
    }


}