package com.zxy.study.transition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zxy.study.R;

import butterknife.BindView;

/**
 *
 */
public class TransitionActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_change)
    Button btnChange;
    /**
     * TransitionManager.go()
     * beginDelayedTransition()
     * setEnterTransition()/setSharedElementEnterTransition()
     */

    private Scene scene1;
    private Scene scene2;
    // Content View Elements

    private LinearLayout mScene_root;
    private Button mBtn_change;
    private boolean isScene2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        bindViews();
        initScene();
    }

    private void initScene() {
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        //两个layout
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);
        TransitionManager.go(scene1);
    }

    // End Of Content View Elements

    private void bindViews() {

        mScene_root = (LinearLayout) findViewById(R.id.scene_root);
        mBtn_change = (Button) findViewById(R.id.btn_change);

        mBtn_change.setOnClickListener(this);
    }

    /**
     * scene1和scene2相互切换，播放动画 * @param view
     */
    public void change(View view) {
        TransitionManager.go(isScene2 ? scene1 : scene2, new ChangeBounds());
        isScene2 = !isScene2;
    }

    @Override
    public void onClick(View v) {
        change(btnChange);
    }
}
