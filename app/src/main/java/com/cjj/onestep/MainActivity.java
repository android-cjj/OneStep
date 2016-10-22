package com.cjj.onestep;

import android.content.ClipData;
import android.content.ClipDescription;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import com.cjj.onestep.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnDragListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<OneStepEntity> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recycler.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerSide.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerSide.addItemDecoration(new DividerItemDecoration(this, 1));
        binding.recycler.addItemDecoration(new DividerGridItemDecoration(
                1,
                3,
                getResources().getColor(R.color.colorPrimary)));

        mLists = getData();

        OneStepAdapter oneStepAdapter = new OneStepAdapter(mLists);
        binding.recycler.setAdapter(oneStepAdapter);
        oneStepAdapter.setItemLongClickListener(new OneStepAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, OneStepEntity entity) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

                v.startDrag(
                        data,
                        shadowBuilder,
                        null,
                        0);

            }
        });

        binding.recyclerSide.setAdapter(oneStepAdapter);
        binding.recyclerSide.setOnDragListener(this);
    }

    private List<OneStepEntity> getData() {

        List<OneStepEntity> list = new ArrayList<>();

        OneStepEntity model = new OneStepEntity("锤子", IconUrls.icon0);
        OneStepEntity model1 = new OneStepEntity("邮件", IconUrls.icon1);
        OneStepEntity model2 = new OneStepEntity("笔记", IconUrls.icon2);
        OneStepEntity model3 = new OneStepEntity("优酷", IconUrls.icon3);
        OneStepEntity model4 = new OneStepEntity("UC", IconUrls.icon4);
        OneStepEntity model5 = new OneStepEntity("QQ音乐", IconUrls.icon5);
        OneStepEntity model6 = new OneStepEntity("手Q", IconUrls.icon6);
        OneStepEntity model7 = new OneStepEntity("微信", IconUrls.icon7);
        OneStepEntity model8 = new OneStepEntity("搜狗", IconUrls.icon8);
        OneStepEntity model9 = new OneStepEntity("新浪微博", IconUrls.icon9);
        OneStepEntity model10 = new OneStepEntity("爱奇艺", IconUrls.icon10);
        OneStepEntity model11 = new OneStepEntity("网易新闻", IconUrls.icon11);
        OneStepEntity model12 = new OneStepEntity("美图秀秀", IconUrls.icon12);
        OneStepEntity model13 = new OneStepEntity("酷狗", IconUrls.icon13);
        OneStepEntity model14 = new OneStepEntity("京东", IconUrls.icon14);
        OneStepEntity model15 = new OneStepEntity("cjj", IconUrls.icon15);
        OneStepEntity model16 = new OneStepEntity("cjj", IconUrls.icon16);

        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);
        list.add(model6);
        list.add(model7);
        list.add(model8);
        list.add(model9);
        list.add(model10);
        list.add(model11);
        list.add(model12);
        list.add(model13);
        list.add(model14);
        list.add(model15);
        list.add(model16);

        return list;
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;

            case DragEvent.ACTION_DROP:
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                CharSequence dragData = item.getText();

                Toast.makeText(MainActivity.this,"你要分享"+dragData,Toast.LENGTH_SHORT).show();

                break;

            case DragEvent.ACTION_DRAG_ENDED:
                break;

            default:
                break;
        }
        return true;
    }
}
