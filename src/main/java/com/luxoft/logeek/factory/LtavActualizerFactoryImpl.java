package com.luxoft.logeek.factory;

import com.luxoft.logeek.utils.DatasetStatus;
import com.luxoft.logeek.utils.LtavActualizer;
import org.springframework.stereotype.Component;

public class LtavActualizerFactoryImpl implements LtavActualizerFactory{
	@Override
	public LtavActualizer forDataset(String extId, DatasetStatus datasetStatus) {
		return null;
	}
}
