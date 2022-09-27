public final class ArrayBag<T> implements BagInterface<T> {

private final T[] bag;

private int numberOfEntries;

private static final int DEFAULT_CAPACITY = 25;

private boolean initialized = false;

private static final int MAX_CAPACITY = 10000;

/** Creates an empty bag whose initial capacity is 25. */

public ArrayBag() {

this(DEFAULT_CAPACITY);

}

/**

* Creates an empty bag having a given initial capacity.

*

*/

public ArrayBag(int desiredCapacity) {

if (desiredCapacity <= MAX_CAPACITY) {

// The cast is safe because the new array contains null entries.

@SuppressWarnings("unchecked")

T[] tempBag = (T[]) new Object[desiredCapacity]; // Unchecked cast

bag = tempBag;

numberOfEntries = 0;

initialized = true;

} else {

throw new IllegalStateException("Attempt to create a bag " +

"whose capacity exceeds " +

"allowed maximum.");

}

}

/** Adds a new entry to this bag.

*/

public boolean add(T newEntry) {

checkInitialization();

boolean result = true;

if (isArrayFull()) {

result = false;

} else { // Assertion: result is true here

bag[numberOfEntries] = newEntry;

numberOfEntries++;

}

return result;

}

/** Throws an exception if this object is not initialized */

private void checkInitialization() {

if (!initialized) {

throw new SecurityException("ArrayBag object is not initialized " +

"properly.");

}

}

/**

* Retrieves all entries that are in this bag.

*/

public T[] toArray() {

// the cast is safe because the new array contains null entries

@SuppressWarnings("unchecked")

T[] result = (T[]) new Object[numberOfEntries]; // unchecked cast

for (int index = 0; index < numberOfEntries; index++) {

result[index] = bag[index];

}

return result;

}

/**

* Sees whether this bag is full.

*/

private boolean isArrayFull() {

return numberOfEntries >= bag.length;

}

/**

* Sees whether this bag is empty.

*/

public boolean isEmpty() {

return numberOfEntries == 0;

}

/**

* Gets the current number of entries in this bag.

*/

public int getCurrentSize() {

return numberOfEntries;

}

/**

* Counts the number of times a given entry appears in this bag.

*/

public int getFrequencyOf(T anEntry) {

checkInitialization();

int counter = 0;

for (int index = 0; index < numberOfEntries; index++) {

if (anEntry.equals(bag[index])) {

counter++;

}

}

return counter;

}

/**

* Tests whether this bag contains a given entry.

*/

public boolean contains(T anEntry) {

checkInitialization();

return getIndexOf(anEntry) > -1;

}

/** Removes all entries from this bag. */

public void clear() {

while (!isEmpty()) {

remove();

}

}

/**

* Removes one unspecified entry from this bag, if possible.

*/

public T remove() {

checkInitialization();

// MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG

T result = removeEntry(numberOfEntries - 1);

return result;

}

/**

* Removes one occurrence of a given entry from this bag.

*/

public boolean remove(T anEntry) {

checkInitialization();

int index = getIndexOf(anEntry);

T result = removeEntry(index);

return anEntry.equals(result);

}

// Removes and returns the entry at a given array index within the array bag.

// If no such entry exists, returns null.

// Preconditions: 0 <= givenIndex < numberOfEntries;

// checkInitialization has been called.

private T removeEntry(int givenIndex) {

T result = null;

if (!isEmpty() && (givenIndex >= 0)) {

result = bag[givenIndex]; // entry to remove

bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry

bag[numberOfEntries - 1] = null; // remove last entry

numberOfEntries--;

}

return result;

}

// Locates a given entry within the array bag.

// Returns the index of the entry, if located, or -1 otherwise.

// Precondition: checkInitialization has been called.

private int getIndexOf(T anEntry) {

int where = -1;

boolean stillLooking = true;

int index = 0;

while (stillLooking && (index < numberOfEntries)) {

if (anEntry.equals(bag[index])) {

stillLooking = false;

where = index;

}

index++;

}

// Assertion: If where > -1, anEntry is in the array bag, and it

// equals bag[where]; otherwise, anEntry is not in the array

return where;

}

/**

* Override the equals method so that we can tell if two bags contain the same items

* the contents in the bag.

*/

public String toString() {

String result = "Bag{Size:" + numberOfEntries + " ";

for (int index = 0; index < numberOfEntries; index++) {

result += "[" + bag[index] + "] ";

}

result += "}";

return result;

}

/**

* Check to see if two bags are equals.

*/

public boolean equals(ArrayBag<T> arrayBag) {

boolean isEqual = true; // result of comparison of bags

// COMPLETE THIS METHOD

if (getCurrentSize() == arrayBag.getCurrentSize()) {

for (int i = 0; i < getCurrentSize(); i++) {

if (getFrequencyOf(bag[i]) != arrayBag.getFrequencyOf(bag[i])) {

isEqual = false;

}

}

} else {

isEqual = false;

}

return isEqual;

}

/**

* Duplicate all the items in a bag.

*/

public boolean duplicateAll() {

checkInitialization();

boolean isDuplicated = true; //

// COMPLETE THIS METHOD

int currentSize = getCurrentSize();

if (currentSize * 2 <= bag.length) {

for (int i = 0; i < currentSize; i++) {

add(bag[i]);

}

} else {

isDuplicated = false;

}

return isDuplicated;

}

/** Remove all duplicate items from a bag */

public void removeDuplicates() {

checkInitialization();

// COMPLETE THIS METHOD

for (int i = 0; i < getCurrentSize(); i++) {

while (getFrequencyOf(bag[i]) > 1) {

remove(bag[i]);

}

}

}

}