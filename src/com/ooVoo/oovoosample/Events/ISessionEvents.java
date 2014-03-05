//
// ISessionEvents.java
// 
// Created by ooVoo on July 22, 2013
//
// © 2013 ooVoo, LLC.  Used under license. 
//
package com.ooVoo.oovoosample.Events;

import com.oovoo.core.IConferenceCore.ConferenceCoreError;

public interface ISessionEvents 
{		
	void OnSessionJoined(ConferenceCoreError eErrorCode);
	void OnSessionLeft(ConferenceCoreError eErrorCode);
	void OnSessionRecordingStatus(ConferenceCoreError eErrorCode, String sPath);
	void OnSessionIdGenerated(String sSessionId);	
	void OnParticipantJoinedSession(String sParticipantId);
	void OnParticipantLeftSession(String sParticipantId);
	void OnSessionError(ConferenceCoreError eErrorCode);
}
 