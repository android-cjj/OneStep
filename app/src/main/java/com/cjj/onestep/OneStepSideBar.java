package com.cjj.onestep;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cjj on 2016/10/22.
 */

public class OneStepSideBar {

    private static final String TAG = OneStepSideBar.class.getSimpleName();

    private static final int FLAG_TOUCHABLE = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

    private static final int FLAG_NOT_TOUCHABLE = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

    private Context mContext;

    private View mBarViewContainer;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams mLayoutParams;

    public OneStepSideBar(Context context) {
        this.mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void show() {
        mBarViewContainer = View.inflate(mContext, R.layout.view_side_bar, null);

        RecyclerView recyclerApp = (RecyclerView) mBarViewContainer.findViewById(R.id.rv_app);
        recyclerApp.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerApp.setAdapter(new AppAdapter(mContext.getPackageManager().getInstalledApplications(0)));

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.width = 300;
        mLayoutParams.gravity = Gravity.RIGHT;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mLayoutParams.flags = FLAG_NOT_TOUCHABLE;
        mLayoutParams.format = PixelFormat.TRANSPARENT;

        mWindowManager.addView(mBarViewContainer, mLayoutParams);

        mBarViewContainer.setOnDragListener(new View.OnDragListener() {
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
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void openApp(ApplicationInfo item) {
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);

        List<ResolveInfo> resolveInfoList = mContext.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        if (resolveInfoList != null && resolveInfoList.size() > 0) {

            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(
                    activityPackageName, className);

            intent.setComponent(componentName);

            mContext.startActivity(intent);
        }
    }

    public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

        private List<ApplicationInfo> mInfoList;

        public AppAdapter(List<ApplicationInfo> infolist) {
            mInfoList = infolist;
        }

        @Override
        public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_app_icon_txt, parent, false);
            return new AppViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AppViewHolder holder, int position) {
            ApplicationInfo item = mInfoList.get(position);
            holder.ivIcon.setImageDrawable(item.loadIcon(mContext.getPackageManager()));
        }

        @Override
        public int getItemCount() {
            return mInfoList.size();
        }

        public class AppViewHolder extends RecyclerView.ViewHolder {
            private TextView tvTitle;
            private ImageView ivIcon;

            public AppViewHolder(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_txt);
                ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            }

        }
    }

}
