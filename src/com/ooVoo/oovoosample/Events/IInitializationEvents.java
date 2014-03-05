//
// IInitializationEvents.java
// 
// Created by ooVoo on July 22, 2013
//
// © 2013 ooVoo, LLC.  Used under license. 
//
package com.ooVoo.oovoosample.Events;

import com.oovoo.core.IConferenceCore.ConferenceCoreError;

public interface IInitializationEvents 
{
	// Initialization
	void OnInitialized(ConferenceCoreError eErrorCode);
	void OnUninitialized(ConferenceCoreError eErrorCode);
}
