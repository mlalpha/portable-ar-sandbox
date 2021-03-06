//package com.projecttango.examples.java.pointcloud.rajawali;
//
//import android.opengl.GLES20;
//import android.opengl.GLSurfaceView;
//import android.opengl.Matrix;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.FloatBuffer;
//
//import javax.microedition.khronos.egl.EGLConfig;
//import javax.microedition.khronos.opengles.GL10;
//
//public class Rerender implements GLSurfaceView.Renderer{
//
//    private static final String VERTEX_SHADER =
//            "attribute vec4 vPosition;\n"
//                    + "uniform mat4 uMVPMatrix;\n"
//                    + "void main() {\n"
//                    + "  gl_Position = uMVPMatrix * vPosition;\n"
//                    + "}";
//    private static final String FRAGMENT_SHADER =
//            "precision mediump float;\n"
//                    + "void main() {\n"
//                    + "  gl_FragColor = vec4(0.5, 0, 0, 1);\n"
//                    + "}";
//    private static final float[] VERTEX = {   // in counterclockwise order:
//            0, 1, 0,  // top
//            -0.5f, -1, 0,  // bottom left
//            1, -1, 0,  // bottom right
//    };
//
//    private final FloatBuffer mVertexBuffer;
//    private final float[] mMVPMatrix = new float[16];
//
//    private int mProgram;
//    private int mPositionHandle;
//    private int mMatrixHandle;
//
//    Rerender() {
//        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
//                .order(ByteOrder.nativeOrder())
//                .asFloatBuffer()
//                .put(VERTEX);
//        mVertexBuffer.position(0);
//    }
//
//    static int loadShader(int type, String shaderCode) {
//        int shader = GLES20.glCreateShader(type);
//        GLES20.glShaderSource(shader, shaderCode);
//        GLES20.glCompileShader(shader);
//        return shader;
//    }
//
//    @Override
//    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig){
//        GLES20.glClearColor(1f,0,0,1f);
//
//        mProgram = GLES20.glCreateProgram();
//        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER);
//        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
//        GLES20.glAttachShader(mProgram, vertexShader);
//        GLES20.glAttachShader(mProgram, fragmentShader);
//        GLES20.glLinkProgram(mProgram);
//
//        GLES20.glUseProgram(mProgram);
//
//        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
//        mMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
//
//        GLES20.glEnableVertexAttribArray(mPositionHandle);
//        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false,
//                12, mVertexBuffer);
//    }
//
//    @Override
//    public void onSurfaceChanged(GL10 gl10, int width, int height){
//        GLES20.glViewport(0, 0, width, height);
//
//        Matrix.perspectiveM(mMVPMatrix, 0, 45, (float) width / height, 0.1f, 100f);
//        Matrix.translateM(mMVPMatrix, 0, 0f, 0f, -2.5f);
//    }
//
//    @Override
//    public void onDrawFrame(GL10 gl10) {
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//        GLES20.glUniformMatrix4fv(mMatrixHandle, 1, false, mMVPMatrix, 0);
//
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
//    }
//}
