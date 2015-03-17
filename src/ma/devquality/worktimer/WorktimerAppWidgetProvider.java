package ma.devquality.worktimer;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WorktimerAppWidgetProvider extends AppWidgetProvider {

	final static String START_STOP = "startStop";
	final static String FINAL_STOP = "finalStop";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.worktimer_appwidget);

		if (action.equals(START_STOP)) {

			SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");

			views.setTextViewText(R.id.timerC, df.format(new Date()));

		} else if (action.equals(FINAL_STOP)) {

			views.setTextViewText(R.id.timerC, "00:00:00");

		}

		ComponentName thisName = new ComponentName(context,
				WorktimerAppWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(thisName, views);

		super.onReceive(context, intent);

	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.worktimer_appwidget);

			Intent intentStartStop = new Intent();
			intentStartStop.setAction(START_STOP);
			PendingIntent pendingIntentStartStop = PendingIntent.getBroadcast(
					context, 0, intentStartStop, 0);

			views.setOnClickPendingIntent(R.id.startStopB,
					pendingIntentStartStop);

			Intent intentFinalStop = new Intent();
			intentFinalStop.setAction(FINAL_STOP);
			PendingIntent pendingIntentFinalStop = PendingIntent.getBroadcast(
					context, 0, intentFinalStop, 0);

			views.setOnClickPendingIntent(R.id.finalStopB,
					pendingIntentFinalStop);

			appWidgetManager.updateAppWidget(appWidgetId, views);

		}
	}

}