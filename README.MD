#Список бенчмарков с краткими пояснениями

К сожалению, бенчмарки, связанные с enterprise-технологиями (Spring, Hibernate) пока реализованы только для "восьмёрки".

###ArrayVsAsListIterationBenchmark
Показывает разницу между проходом по "голому" массиву и массиву, обёрнутому в список.

См. [IDEA-184240 Unnecessary array-to-collection wrapping should be detected](https://youtrack.jetbrains.com/v2/issue/IDEA-184240)


###SubListToArrayBenchmark
Показывает разницу между 

```java
ArrayList.toArray(new T[0])
``` 
и
```java
ArrayList.subList(0, size()).toArray(new T[0])
```

Несмотря на равный объём перемещаемых данных, производительность второго способа значительно хуже.

См. [JDK-8196207 Inefficient ArrayList.subList().toArray()](https://bugs.openjdk.java.net/browse/JDK-8196207)

###BulkOperationBenchmark
Показывает разницу между тремя способами заполнения коллекции:

1\.
```java
Collection<Integer> newCollection = new ArrayList<>();
for (Integer item : items) {
    newCollection.add(item);
}
```
2\.
```java
Collection<Integer> newCollection = new ArrayList<>();
newCollection.addAll(items);
```
3\.
```java
Collection<Integer> newCollection = new ArrayList(items);
```

См. [IDEA-165942 Inspection to replace method call in a loop with bulk operation](https://youtrack.jetbrains.com/issue/IDEA-165942)

###SubArrayBenchmark
Показывает разницу между 

```java
Arrays.asList(array).subList(0, to).toArray(new Integer[0])
```
и
```java
Arrays.copyOf(array, to);
```

См. [IDEA-182206 Simplification for Arrays.asList().sublist().toArray()](https://youtrack.jetbrains.com/issue/IDEA-182206)

###LazyTransactionBenchmark
Показывает разницу между

```java
public long createCashFlow(CashFlowDto dto) {
  boolean isInvalid = dto.getNumber() % 2 != 0;
  if (isInvalid) {
    return -1L;
  }

  return cashFlowServiceLocal.createCashFlow(dto);
}
```

и

```java
@Transactional(readOnly = true)
public long createCashFlow(CashFlowDto dto) {
  boolean isInvalid = dto.getNumber() % 2 != 0;
  if (isInvalid) {
    return -1L;
  }

  return cashFlowServiceLocal.createCashFlow(dto);
}
```

Иными словами, показывает стоимость "холостой" транзакции.