package com.ooVoo.oovoosample.Messenger;

import java.util.ArrayList;

import com.oovoo.core.ConferenceCore;

public class MessengerController {

	private static MessengerController mInstance = null;
	
	private ArrayList<Message> mMessages = null;
	
	private IMessengerListener mListener = null;
	
	public static MessengerController getInstance()
	{
		if (mInstance == null) 
		{ 
			mInstance = new MessengerController();
		}
		return mInstance;
	}
	
	public MessengerController() {
	
		mMessages = new ArrayList<Message>();
	}
	
	public ArrayList<Message> getMessages() {
		return mMessages;
	}
	
	public void setListener(IMessengerListener listener) {
		mListener = listener;
	}
	
	public void sendText(byte[] buffer, String participantId) {
		ConferenceCore.instance().inCallMessage(buffer, participantId);
	}
	
	public void receiveText(byte[] buffer, String participantName) {
		if (mListener != null) {
			mListener.onTextReceived(buffer, participantName);
		}
	}
	
	public void clear() {
		mMessages.clear();
	}
}
