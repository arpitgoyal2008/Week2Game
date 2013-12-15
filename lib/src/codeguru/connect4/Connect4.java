package codeguru.connect4;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class Connect4 implements ApplicationListener {
    private static float RADIUS = 1.0f;
    private static int SEGS = 100;

    private ShapeRenderer renderer = null;
    private Board board = null;

    public void create() {
        if (this.renderer == null) {
            this.renderer = new ShapeRenderer();
        }

        if (this.board == null) {
            this.board = new Board();
        }

        Gdx.gl.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeType.Filled);
        for (int row = 0; row < Board.ROW_COUNT; ++row) {
            for (int col = 0; col < Board.COL_COUNT; ++col) {
                switch (this.board.getState(row, col)) {
                case EMPTY:
                    renderer.setColor(Color.WHITE);
                    break;
                case PLAYER1:
                    renderer.setColor(Color.RED);
                    break;
                case PLAYER2:
                    renderer.setColor(Color.BLACK);
                    break;
                }

                float x = 2 * col + 1;
                float y = 2 * row + 1;
                renderer.circle(x, y, RADIUS, SEGS);
            }
        }
        renderer.end();
    }

    public void resize(int width, int height) {
        float radius = Math.min((float) width / (float) Board.COL_COUNT,
                (float) height / (float) Board.ROW_COUNT) / 2.0f;
        int xMargin = (int) ((width - 2 * radius * Board.COL_COUNT) / 2);
        int yMargin = (int) ((height - 2 * radius * Board.ROW_COUNT) / 2);
        Gdx.gl.glViewport(xMargin, yMargin, width - 2 * xMargin, height - 2
                * yMargin);

        Matrix4 ortho = new Matrix4().setToOrtho2D(0.0f, 0.0f,
                Board.COL_COUNT * 2.0f, Board.ROW_COUNT * 2.0f);
        this.renderer.setProjectionMatrix(ortho);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}