package com.luxoft.logeek.example;

import com.luxoft.logeek.entity.BranchEntity;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.service.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class ContainsExample {

	private Function<SomeEntity, ChildEntity> someFucntion;
	private Service service;

	public ContainsExample(Service service) {
		this.service=service;
	}

	public void foo(Collection<SomeEntity> argument, Collection<Long> otherArgument) {
		List<ChildEntity> children = argument.stream().map(someFucntion).collect(Collectors.toList());
		
		bar(children, otherArgument);
	}

	private void bar(List<ChildEntity> children, Collection<Long> otherArgument) {
		for (ChildEntity child : children) {
			if (otherArgument.contains(child.getId())) {
				//do smth
			}
		}
	}
	
	public Long save(Collection<String> usItems, Collection<String> nonUsItems) {
		
		Predicate<BranchEntity> usPredicate = branch -> {
			return usItems.contains(branch.getExtId());
		};
		Predicate<BranchEntity> nonUsPredicate = branch -> {
			return nonUsItems.contains(branch.getExtId());
		};

		List<BranchEntity> allBranches = service.findAllBranches();

		allBranches.stream().filter(usPredicate).forEach(branch -> doSmth(branch));
		allBranches.stream().filter(nonUsPredicate).forEach(branch -> doSmth(branch));
		
		return System.nanoTime();
	}

	private void doSmth(BranchEntity branch) {
		
	}
}