package me.rafkhan.textual;

import me.rafkhan.textual.data.ConversationListProvider;
import me.rafkhan.textual.data.TextMessage;
import me.rafkhan.textual.data.TextMessageProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];

			for (int i = 0; i < pdus.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}

			for (SmsMessage message : messages) {
				TextMessage tm = new TextMessage();
				tm.sender = message.getOriginatingAddress();
				tm.message = message.getMessageBody();
				tm.timestamp = message.getTimestampMillis();

				context.getContentResolver().insert(
						TextMessageProvider.URI_MESSAGES, tm.getContent());
				
				context.getContentResolver().insert(
						ConversationListProvider.URI_CONVERSATIONS, tm.getContent());
			}
		}
	}
}
