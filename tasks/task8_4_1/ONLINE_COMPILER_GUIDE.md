# Using Online Kotlin Compiler for Task 8.4.1

## Step 1: Go to Online Compiler
Visit: https://play.kotlinlang.org/

## Step 2: Copy and Paste Code
Copy the entire content of `SequenceSimple.kt` and paste it into the online editor.

## Step 3: Run the Code
Click the "Run" button to execute the code.

## Expected Output:
=== Regular Collection Operations ===
Filtering: 1
Filtering: 4
Filtering: 7
Filtering: 2
Filtering: 9
Filtering: 3
Filtering: 8
Mapping: 4
Mapping: 7
Mapping: 9
Mapping: 8
Collection result: [8, 14, 18]

=== Sequence Operations ===
Sequence filtering: 1
Sequence filtering: 4
Sequence mapping: 4
Sequence filtering: 7
Sequence mapping: 7
Sequence filtering: 2
Sequence filtering: 9
Sequence mapping: 9
Sequence result: [8, 14, 18]

text

## What This Shows:

### Collections (Eager Evaluation):
- Processes ALL elements through filter first
- Then processes ALL filtered elements through map
- Finally takes the first 3 results
- Total operations: 7 filters + 4 maps = 11 operations

### Sequences (Lazy Evaluation):
- Processes elements one by one through the entire pipeline
- Stops as soon as it gets 3 results
- Total operations: 4 filters + 3 maps = 7 operations

## Key Difference:
Sequences are more efficient because they don't process unnecessary elements!
