package com.github.javacodekata.lambda.stream;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListTransformer {

	private final List<String> values;

	private ListTransformer(List<String> values) {
		this.values = values;
	}

	public static ListTransformer of(List<String> values) {
		checkNotNull(values);
		return new ListTransformer(ImmutableList.copyOf(values));
	}

	/**
	 * This method should take the String List and sort all the String elements in ascending (ASCII) order.
	 * @return The sorted values in ascending ASCII order.
	 */
	public List<String> getSortedStrings() {
		return values.stream().sorted().collect(Collectors.toList());
	}

	/**
	 * This method should take the String List and:
	 * <ol>
	 *     <li>filter the elements that contains one or more digits;</li>
	 *     <li>transform (map) the remaining Strings into Integers;</li>
	 *     <li>sort the Integers in ascending order.</li>
	 * </ol>
	 * @return
	 */
	public List<Integer> getSortedIntegers() {
		return getNumericElements().sorted().collect(Collectors.toList());
	}

	private Stream<Integer> getNumericElements() {
		return values.stream().filter(element -> element.matches("[-+]?\\d*\\.?\\d+")).map(Integer::valueOf);
	}

	/**
	 * This method should take the String List and:
	 * <ol>
	 *     <li>filter the elements that contains one or more digits;</li>
	 *     <li>transform (map) the remaining Strings into Integers;</li>
	 *     <li>sort the Integers in descending order.</li>
	 * </ol>
	 * @return
	 */
	public List<Integer> getSortedDescendingIntegers() {
		return getNumericElements().sorted((firstElement, secondElement) -> Integer.compare(firstElement, secondElement))
				.sorted()
				.collect(Collectors.collectingAndThen(Collectors.toList(), (list) -> { Collections.reverse(list); return list;}));
	}

}
