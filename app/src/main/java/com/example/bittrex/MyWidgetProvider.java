package com.example.bittrex;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.bittrex.Database.BittrexDatabase;
import com.example.bittrex.Model.Result;

public class MyWidgetProvider extends AppWidgetProvider {
    private BittrexDatabase bittrexDatabase;
    private static final String ACTION_UPDATE_CLICK_NEXT = "action.UPDATE_CLICK_NEXT";
    private static final String ACTION_UPDATE_CLICK_PREVIOUS = "action.UPDATE_CLICK_PREVIOUS";
    public static int mCounter = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        bittrexDatabase = BittrexDatabase.getInstance(context);
        Result[] results = bittrexDatabase.bittrexDAO().fetchListData().toArray(new Result[0]);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_layout);
            views.setOnClickPendingIntent(R.id.prevBtn, getPendingSelfIntent(context, ACTION_UPDATE_CLICK_PREVIOUS));
            views.setOnClickPendingIntent(R.id.nextBtn, getPendingSelfIntent(context, ACTION_UPDATE_CLICK_NEXT));
            if(results[0]!=null){
                views.setTextViewText(R.id.currencyTv, results[0].getCurrency());
                views.setTextViewText(R.id.currencyLongTv, results[0].getCurrencyLong());
                views.setTextViewText(R.id.txFeeTv, String.valueOf(results[0].getTxFee()));
            }
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        bittrexDatabase = BittrexDatabase.getInstance(context);
        Result[] results = bittrexDatabase.bittrexDAO().fetchListData().toArray(new Result[0]);
        if (ACTION_UPDATE_CLICK_NEXT.equals(intent.getAction())) {
            if(mCounter<results.length-1){
                mCounter++;
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_layout);
            ComponentName watchWidget = new ComponentName(context, MyWidgetProvider.class);
            views.setTextViewText(R.id.currencyTv, results[mCounter].getCurrency());
            views.setTextViewText(R.id.currencyLongTv, results[mCounter].getCurrencyLong());
            views.setTextViewText(R.id.txFeeTv, String.valueOf(results[mCounter].getTxFee()));
            appWidgetManager.updateAppWidget(watchWidget, views);
        } else if (ACTION_UPDATE_CLICK_PREVIOUS.equals(intent.getAction())) {
            mCounter--;
            if (mCounter < 0) {
                mCounter = 0;
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_layout);
            ComponentName watchWidget = new ComponentName(context, MyWidgetProvider.class);
            views.setTextViewText(R.id.currencyTv, results[mCounter].getCurrency());
            views.setTextViewText(R.id.currencyLongTv, results[mCounter].getCurrencyLong());
            views.setTextViewText(R.id.txFeeTv, String.valueOf(results[mCounter].getTxFee()));
            appWidgetManager.updateAppWidget(watchWidget, views);
        }
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, MyWidgetProvider.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
