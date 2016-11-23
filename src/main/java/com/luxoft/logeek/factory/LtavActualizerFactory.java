package com.luxoft.logeek.factory;

import com.luxoft.logeek.utils.DatasetStatus;
import com.luxoft.logeek.utils.LtavActualizer;

public interface LtavActualizerFactory {
	LtavActualizer forDataset(String extId, DatasetStatus datasetStatus);
}
