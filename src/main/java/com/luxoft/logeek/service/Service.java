package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.BranchEntity;

import java.util.List;

public interface Service {
	List<BranchEntity> findAllBranches();
}
