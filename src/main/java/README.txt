Problem:

- 16gb list provided
- 4gb system memory
- OS 64-bit

- Large list of words, there can be 1 or more instances of a word on the list
- Determine the Top 5 occurring words

Ideas:

- Read in the list (in pieces) from file
- Sort the list pieces (Merge Sort)
- Resulting in > 1 lists
- Use pointers to traverse each of the lists, summing frequency of each word
- Add sums to Priority Queue as you go, storing the Top 5 frequencies
- Pop smallest value out of queue when larger number found