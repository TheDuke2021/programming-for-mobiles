package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView squareImage = findViewById(R.id.square);
        ShapeDrawable square = new ShapeDrawable(new RectShape());
        square.setIntrinsicHeight(100);
        square.setIntrinsicWidth(100);
        square.getPaint().setColor(Color.RED);
        squareImage.setImageDrawable(square);

        ImageView circleImage = findViewById(R.id.circle);
        ShapeDrawable circle = new ShapeDrawable(new OvalShape());
        circle.setIntrinsicHeight(100);
        circle.setIntrinsicWidth(100);
        circle.getPaint().setColor(Color.GREEN);
        circleImage.setImageDrawable(circle);

        ImageView triangleImage = findViewById(R.id.triangle);
        Path path = new Path();
        path.moveTo(0, 100);
        path.lineTo(50, 0);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        ShapeDrawable triangle = new ShapeDrawable(new PathShape(path, 100 ,100));
        triangle.setIntrinsicHeight(100);
        triangle.setIntrinsicWidth(100);
        triangle.getPaint().setColor(Color.BLUE);
        triangleImage.setImageDrawable(triangle);

        Animation squareAnimation = AnimationUtils.loadAnimation(this, R.anim.square_animation);
        squareImage.startAnimation(squareAnimation);

        Animation circleAnimation = AnimationUtils.loadAnimation(this, R.anim.circle_animation);
        circleImage.startAnimation(circleAnimation);

        Animation triangleAnimation = AnimationUtils.loadAnimation(this, R.anim.triangle_animation);
        triangleImage.startAnimation(triangleAnimation);


    }
}