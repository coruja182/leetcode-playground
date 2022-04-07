# Leet Code - Shuffle-String exercise solution

Javascript (nodejs) implementation of the shuffle-string leet code exercise.

<https://leetcode.com/problems/shuffle-string/>

## Notes about my solution

Since I was not sure whether the `Constraints` are something I should validate or not I ended up validating it all.

## Running the unit tests

```bash
npm install

npm test
```

## Problem Description

You are given a string `s` and an integer array `indices` of the same length.
The string `s` will be shuffled such that the character at the ith position moves to `indices[i]` in the shuffled string.

Return the shuffled string.

![alt text for screen readers](./docs/assets/shuffle-string-example.jpg)

### Example 1

```text
Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
```

### Example 2

```text
Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.
```

### Constraints

```text
s.length == indices.length == n
1 <= n <= 100
s consists of only lowercase English letters.
0 <= indices[i] < n
All values of indices are unique.
```
